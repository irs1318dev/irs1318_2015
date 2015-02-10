package org.usfirst.frc.team1318.robot.Elevator;

import org.usfirst.frc.team1318.robot.HardwareConstants;
import org.usfirst.frc.team1318.robot.TuningConstants;
import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.IDriver;
import org.usfirst.frc.team1318.robot.Common.PIDHandler;

import edu.wpi.first.wpilibj.Preferences;

public class ElevatorController implements IController
{
    private final ElevatorComponent component;
    private final IDriver driver;

    private double baseLevel;
    private double position;
    private double encoderZeroOffset;

    private boolean useVelocityPID;
    private boolean usePID;
    private PIDHandler pidHandler;

    public ElevatorController(IDriver driver, ElevatorComponent component)
    {
        this.component = component;
        this.driver = driver;

        this.useVelocityPID = false;
        this.usePID = false;//true;

        this.baseLevel = HardwareConstants.ELEVATOR_FLOOR_HEIGHT;
        this.position = 0;
        this.encoderZeroOffset = 0;
    }

    @Override
    public void update()
    {
        // check offset - if we hit the bottom or top, adjust the encoder zero offset
        if (this.component.getBottomHallEffectSensorValue())
        {
            //this.encoderZeroOffset = HardwareConstants.ELEVATOR_MIN_HEIGHT - this.component.getEncoderDistance();
        }
        else if (this.component.getTopHallEffectSensorValue())
        {
            //this.encoderZeroOffset = HardwareConstants.ELEVATOR_MAX_HEIGHT - this.component.getEncoderDistance();
        }

        // set elevator state here
        if (this.driver.getElevatorSetStateToFloorButton())
        {
            this.baseLevel = HardwareConstants.ELEVATOR_FLOOR_HEIGHT;
        }
        else if (this.driver.getElevatorSetStateToPlatformButton())
        {
            this.baseLevel = HardwareConstants.ELEVATOR_PLATFORM_HEIGHT;
        }
        else if (this.driver.getElevatorSetStateToStepButton())
        {
            this.baseLevel = HardwareConstants.ELEVATOR_STEP_HEIGHT;
        }

        // handle enabling/disabling PID (enabling PID takes precedence)
        if (this.driver.getElevatorPIDOn())
        {
            // create PID handler if we are changing modes...
            if (!this.usePID)
            {
                this.usePID = true;
                this.createPIDHandler();
                this.position = component.getEncoderDistance() + this.encoderZeroOffset;

            }
        }
        else if (this.driver.getElevatorPIDOff())
        {
            // remove PID handler if we are changing modes...
            if (this.usePID)
            {
                this.usePID = false;
                this.createPIDHandler();
            }
        }

        double powerLevel;

        // if elevator up or down button is pushed, do not deal with positional elevator buttons
        // down override button takes precedence over the up override button.
        if (this.driver.getElevatorDownButton())
        {
            // if usePID is true, calculate velocity using PID.
            if (this.usePID)
            {
                // recreate PID handler if we are changing modes...
                if (!this.useVelocityPID)
                {
                    this.useVelocityPID = true;
                    this.createPIDHandler();
                }

                powerLevel = this.calculateVelocityModePowerSetting(-TuningConstants.ELEVATOR_OVERRIDE_POWER_LEVEL);
            }
            else
            {
                powerLevel = -TuningConstants.ELEVATOR_OVERRIDE_POWER_LEVEL;
            }
        }
        else if (this.driver.getElevatorUpButton())
        {
            // if usePID is true, calculate velocity using PID.
            if (this.usePID)
            {
                // recreate PID handler if we are changing modes...
                if (!this.useVelocityPID)
                {
                    this.useVelocityPID = true;
                    this.createPIDHandler();
                }

                powerLevel = this.calculateVelocityModePowerSetting(TuningConstants.ELEVATOR_OVERRIDE_POWER_LEVEL);
            }
            else
            {
                powerLevel = TuningConstants.ELEVATOR_OVERRIDE_POWER_LEVEL;
            }
        }
        else
        {
            // if usePID is true, calculate power-level using PID
            if (this.usePID)
            {
                if (this.useVelocityPID)
                {
                    this.useVelocityPID = false;
                    this.createPIDHandler();
                    this.position = this.component.getEncoderDistance() + this.encoderZeroOffset;
                }

                // calculate position to set elevator
                this.position = this.getPositionShift();

                // reset in case position is less than minimum, or more then maximum
                this.position = Math.max(this.position, HardwareConstants.ELEVATOR_MIN_HEIGHT);
                this.position = Math.min(this.position, HardwareConstants.ELEVATOR_MAX_HEIGHT);

                powerLevel = this.calculatePositionModePowerSetting(this.position);
            }
            else
            {
                // if we are in non-PID mode, pressing neither the up nor down override buttons means we should stop applying power to the motor
                powerLevel = 0.0;
            }
        }

        this.component.setMotorPowerLevel(powerLevel);
    }

    /**
     * changes the desired state in response to new user input, otherwise returns current desired position 
     * @return desired position 
     */
    private double getPositionShift()
    {
        if (this.driver.getElevatorMoveTo0TotesButton())
        {
            return HardwareConstants.ELEVATOR_0_TOTE_HEIGHT + this.baseLevel;
        }
        else if (this.driver.getElevatorMoveTo1ToteButton())
        {
            return HardwareConstants.ELEVATOR_1_TOTE_HEIGHT + this.baseLevel;
        }
        else if (this.driver.getElevatorMoveTo2TotesButton())
        {
            return HardwareConstants.ELEVATOR_2_TOTE_HEIGHT + this.baseLevel;
        }
        else if (this.driver.getElevatorMoveTo3TotesButton())
        {
            return HardwareConstants.ELEVATOR_3_TOTE_HEIGHT + this.baseLevel;
        }
        else
        {
            // Note: we only adjust position if we change the totes level.
            // If we change only the base level, we just stay at the current position
            // until we change the totes level as well.
            return this.position;
        }
    }

    @Override
    public void stop()
    {
        // stop the elevator's motor
        this.component.setMotorPowerLevel(0.0);
    }

    private void createPIDHandler()
    {
        if (!this.usePID)
        {
            this.pidHandler = null;
        }
        else
        {
            Preferences prefs = Preferences.getInstance();
            if (!this.useVelocityPID)
            {
                this.pidHandler = new PIDHandler(
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_POSITION_PID_KP_KEY,
                        TuningConstants.ELEVATOR_POSITION_PID_KP_DEFAULT),
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_POSITION_PID_KI_KEY,
                        TuningConstants.ELEVATOR_POSITION_PID_KI_DEFAULT),
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_POSITION_PID_KD_KEY,
                        TuningConstants.ELEVATOR_POSITION_PID_KD_DEFAULT),
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_POSITION_PID_KF_KEY,
                        TuningConstants.ELEVATOR_POSITION_PID_KF_DEFAULT),
                    -TuningConstants.ELEVATOR_MAX_POWER_LEVEL,
                    TuningConstants.ELEVATOR_MAX_POWER_LEVEL);
            }
            else
            {
                this.pidHandler = new PIDHandler(
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_VELOCITY_PID_KP_KEY,
                        TuningConstants.ELEVATOR_VELOCITY_PID_KP_DEFAULT),
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_VELOCITY_PID_KI_KEY,
                        TuningConstants.ELEVATOR_VELOCITY_PID_KI_DEFAULT),
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_VELOCITY_PID_KD_KEY,
                        TuningConstants.ELEVATOR_VELOCITY_PID_KD_DEFAULT),
                    prefs.getDouble(
                        TuningConstants.ELEVATOR_VELOCITY_PID_KF_KEY,
                        TuningConstants.ELEVATOR_VELOCITY_PID_KF_DEFAULT),
                    -TuningConstants.ELEVATOR_MAX_POWER_LEVEL,
                    TuningConstants.ELEVATOR_MAX_POWER_LEVEL);
            }
        }
    }

    private double calculatePositionModePowerSetting(double desired)
    {
        double current = this.component.getEncoderDistance() - this.encoderZeroOffset;
        return this.pidHandler.calculate(desired, current);
    }

    private double calculateVelocityModePowerSetting(double desired)
    {
        double current = this.component.getEncoderVelocity();
        return this.pidHandler.calculate(desired, current);
    }
}