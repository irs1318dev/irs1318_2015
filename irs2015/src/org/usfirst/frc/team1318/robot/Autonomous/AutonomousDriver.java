package org.usfirst.frc.team1318.robot.Autonomous;

import java.util.Queue;

import org.usfirst.frc.team1318.robot.Common.IDriver;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

/**
 * Driver for autonomous mode.  Autonomous driver acts as the operator of the robot,
 * telling it what actions to perform as determined by the current task and tasks that have
 * come before it that intentionally don't reset their state.
 * 
 * @author Will
 *
 */
public class AutonomousDriver implements IDriver
{
    // logging constants
    //Drive Train 
    private static final String DRIVETRAIN_X_VELOCITY_LOG_KEY = "a.driveXVelocity";
    private static final String DRIVETRAIN_Y_VELOCITY_LOG_KEY = "a.driveYVelocity";
    private static final String DRIVETRAIN_SIMPLE_MODE_LOG_KEY = "a.driveSimpleMode";
    private static final String DRIVETRAIN_LEFT_POSITION_LOG_KEY = "a.driveLeftPosition";
    private static final String DRIVETRAIN_RIGHT_POSITION_LOG_KEY = "a.driveRightPosition";
    private static final String DRIVETRAIN_POSITION_MODE_LOG_KEY = "a.drivePositionMode";

    //Elevator 
    private static final String ELEVATOR_CONTAINER_MACRO_STATE_LOG_KEY = "a.elevatorContainerMacroState";
    private static final String ELEVATOR_SET_STATE_TO_FLOOR_LOG_KEY = "a.elevatorSetStateToFloor";
    private static final String ELEVATOR_SET_STATE_TO_PLATFORM_LOG_KEY = "a.elevatorSetStateToPlatform";
    private static final String ELEVATOR_SET_STATE_TO_STEP_LOG_KEY = "a.elevatorSetStateToStep";
    private static final String ELEVATOR_MOVE_TO_0_TOTES_LOG_KEY = "a.elevatorHeight3";
    private static final String ELEVATOR_MOVE_TO_1_TOTE_LOG_KEY = "a.elevatorHeight4";
    private static final String ELEVATOR_MOVE_TO_2_TOTES_LOG_KEY = "a.elevatorHeight5";
    private static final String ELEVATOR_MOVE_TO_3_TOTES_LOG_KEY = "a.elevatorHeight6";
    private static final String ELEVATOR_PID_TOGGLE_STATE_LOG_KEY = "a.elevatorPIDToggle";
    private static final String ELEVATOR_STOP_STATE_LOG_KEY = "a.elevatorStop";
    private static final String ELEVATOR_UP_STATE_LOG_KEY = "a.elevatorUp";
    private static final String ELEVATOR_DOWN_STATE_LOG_KEY = "a.elevatorDown";

    //Arm 
    private static final String ARM_MACRO_EXTEND_STATE_LOG_KEY = "a.armMacroExtendState";
    private static final String ARM_MACRO_RETRACT_STATE_LOG_KEY = "a.armMacroRetractState";
    private static final String ARM_EXTENDER_STATE_LOG_KEY = "a.armExtenderOverride";
    private static final String ARM_TILT_STATE_LOG_KEY = "a.armTiltOverride";
    private static final String ARM_TROMBONE_STATE_LOG_KEY = "a.armTromboneOverride";

    //Intake 
    private static final String INTAKE_UP_STATE_KEY = "a.intakeUpState";
    private static final String INTAKE_DOWN_STATE_KEY = "a.intakeDownState";
    private static final String INTAKE_RIGHT_TOGGLE_OVERRIDE_STATE_KEY = "a.intakeRightToggleOverrideState";
    private static final String INTAKE_LEFT_TOGGLE_OVERRIDE_STATE_KEY = "a.intakeLeftToggleOverrideState";
    private static final String INTAKE_FORWARD_STATE_KEY = "a.intakeForwardStateKey";
    private static final String INTAKE_BACKWARD_STATE_KEY = "a.intakeBackwardStateKey";

    private final Queue<IAutonomousTask> autonomousTasks;
    private IAutonomousTask currentTask;
    private final AutonomousControlData controlData;

    /**
     * Initializes a new AutonomousDriver
     * @param autonomousTasks to execute as a part of this driver
     */
    public AutonomousDriver(Queue<IAutonomousTask> autonomousTasks)
    {
        this.autonomousTasks = autonomousTasks;
        this.currentTask = null;
        this.controlData = new AutonomousControlData();
    }

    /**
     * Tell the driver that some time has passed
     */
    public void update()
    {
        // check whether we should continue with the current task
        if (this.currentTask != null)
        {
            if (!this.currentTask.shouldContinue())
            {
                this.currentTask.end(this.controlData);
                this.currentTask = null;
            }
        }

        // if there's no current task, find the next one and start it (if any)
        if (this.currentTask == null)
        {
            this.currentTask = this.autonomousTasks.poll();

            // if there's no next task to run, then we are done
            if (this.currentTask == null)
            {
                return;
            }

            this.currentTask.begin();
        }

        // run the current task and apply the result to the control data
        this.currentTask.update(this.controlData);
    }

    /**
     * Tell the operator component that operation is stopping
     */
    public void stop()
    {
        if (this.currentTask != null)
        {
            this.currentTask.cancel(this.controlData);
            this.currentTask = null;
        }
    }

    //================================================== DriveTrain ==============================================================

    /**
     * Get a value indicating the desired drive train X Velocity 
     * @return value between -1.0 and 1.0 (percentage of max right turn velocity)
     */
    public double getDriveTrainXVelocity()
    {
        double xVelocity = this.controlData.getDriveTrainXVelocity();

        SmartDashboardLogger.putNumber(AutonomousDriver.DRIVETRAIN_X_VELOCITY_LOG_KEY, xVelocity);

        return xVelocity;
    }

    /**
     * Get a value indicating the desired drive train Y velocity (turn amount) 
     * @return value between -1.0 and 1.0 (percentage of max forward velocity)
     */
    public double getDriveTrainYVelocity()
    {
        double yVelocity = this.controlData.getDriveTrainYVelocity();

        SmartDashboardLogger.putNumber(AutonomousDriver.DRIVETRAIN_Y_VELOCITY_LOG_KEY, yVelocity);

        return yVelocity;
    }

    /**
     * Get a value indicating whether we should be using the drive train in simple mode 
     * @return true if we should be in simple mode, otherwise false
     */
    public boolean getDriveTrainSimpleMode()
    {
        boolean simpleMode = this.controlData.getDriveTrainSimpleMode();

        SmartDashboardLogger.putBoolean(AutonomousDriver.DRIVETRAIN_SIMPLE_MODE_LOG_KEY, simpleMode);

        return simpleMode;
    }

    /**
     * Get a value indicating the desired drive train left position for positional mode
     * @return position
     */
    public double getDriveTrainLeftPosition()
    {
        double leftPosition = this.controlData.getDriveTrainLeftPosition();

        SmartDashboardLogger.putNumber(AutonomousDriver.DRIVETRAIN_LEFT_POSITION_LOG_KEY, leftPosition);

        return leftPosition;
    }

    /**
     * Get a value indicating the desired drive train right position for positional mode
     * @return position
     */
    public double getDriveTrainRightPosition()
    {
        double rightPosition = this.controlData.getDriveTrainRightPosition();

        SmartDashboardLogger.putNumber(AutonomousDriver.DRIVETRAIN_RIGHT_POSITION_LOG_KEY, rightPosition);

        return rightPosition;
    }

    /**
     * Get a value indicating whether the drive train should be in position or velocity mode
     * @return true if position mode, false if velocity mode
     */
    public boolean getDriveTrainPositionMode()
    {
        boolean positionMode = this.controlData.getDriveTrainPositionMode();

        SmartDashboardLogger.putBoolean(AutonomousDriver.DRIVETRAIN_POSITION_MODE_LOG_KEY, positionMode);

        return positionMode;
    }

    //=================================================== Elevator ===============================================================

    @Override
    public boolean getElevatorContainerMacroButton()
    {
        boolean mode = this.controlData.getElevatorContainerMacroState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_CONTAINER_MACRO_STATE_LOG_KEY, mode);
        return mode;
    }

    @Override
    public boolean getElevatorSetStateToFloorButton()
    {
        boolean state = this.controlData.getElevatorSetStateToFloor();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_SET_STATE_TO_FLOOR_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorSetStateToPlatformButton()
    {
        boolean state = this.controlData.getElevatorSetStateToPlatform();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_SET_STATE_TO_PLATFORM_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorSetStateToStepButton()
    {
        boolean state = this.controlData.getElevatorSetStateToStep();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_SET_STATE_TO_STEP_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorMoveTo0TotesButton()
    {
        boolean state = this.controlData.getElevatorMoveTo0Totes();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_MOVE_TO_0_TOTES_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorMoveTo1ToteButton()
    {
        boolean state = this.controlData.getElevatorMoveTo1Tote();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_MOVE_TO_1_TOTE_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorMoveTo2TotesButton()
    {
        boolean state = this.controlData.getElevatorMoveTo2Totes();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_MOVE_TO_2_TOTES_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorMoveTo3TotesButton()
    {
        boolean state = this.controlData.getElevatorMoveTo3Totes();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_MOVE_TO_3_TOTES_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getElevatorPIDToggle()
    {
        boolean state = this.controlData.getElevatorPIDToggleState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_PID_TOGGLE_STATE_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getStopElevatorButton()
    {
        boolean mode = this.controlData.getElevatorStopState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_STOP_STATE_LOG_KEY, mode);
        return mode;
    }

    @Override
    public boolean getElevatorUpButton()
    {
        boolean mode = this.controlData.getElevatorUpState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_UP_STATE_LOG_KEY, mode);
        return mode;
    }

    @Override
    public boolean getElevatorDownButton()
    {
        boolean mode = this.controlData.getElevatorDownState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ELEVATOR_DOWN_STATE_LOG_KEY, mode);
        return mode;
    }

    //===================================================== Arm =================================================================

    @Override
    public boolean getArmMacroExtendButton()
    {
        boolean state = this.controlData.getArmMacroExtendState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ARM_MACRO_EXTEND_STATE_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getArmMacroRetractButton()
    {
        boolean state = this.controlData.getArmMacroRetractState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ARM_MACRO_RETRACT_STATE_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getArmExtenderToggleOverride()
    {
        boolean state = this.controlData.getArmExtenderOverrideState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ARM_EXTENDER_STATE_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getArmTiltToggleOverride()
    {
        boolean state = this.controlData.getArmTiltOverrideState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ARM_TILT_STATE_LOG_KEY, state);
        return state;
    }

    @Override
    public boolean getArmTromboneToggleOverride()
    {
        boolean state = this.controlData.getArmTromboneOverrideState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.ARM_TROMBONE_STATE_LOG_KEY, state);
        return state;
    }

    //=================================================== Intake ================================================================

    @Override
    public boolean getIntakeUpButton()
    {
        boolean state = this.controlData.getIntakeUpState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.INTAKE_UP_STATE_KEY, state);
        return state;
    }

    @Override
    public boolean getIntakeDownButton()
    {
        boolean state = this.controlData.getIntakeDownState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.INTAKE_DOWN_STATE_KEY, state);
        return state;
    }

    @Override
    public boolean getIntakeRightToggleOverride()
    {
        boolean state = this.controlData.getIntakeRightToggleOverrideState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.INTAKE_RIGHT_TOGGLE_OVERRIDE_STATE_KEY, state);
        return state;
    }

    @Override
    public boolean getIntakeLeftToggleOverride()
    {
        boolean state = this.controlData.getIntakeLeftToggleOverrideState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.INTAKE_LEFT_TOGGLE_OVERRIDE_STATE_KEY, state);
        return state;
    }

    @Override
    public boolean getIntakeForwardButton()
    {
        boolean state = this.controlData.getIntakeForwardState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.INTAKE_FORWARD_STATE_KEY, state);
        return state;
    }

    @Override
    public boolean getIntakeBackwardButton()
    {
        boolean state = this.controlData.getIntakeBackwardState();
        SmartDashboardLogger.putBoolean(AutonomousDriver.INTAKE_BACKWARD_STATE_KEY, state);
        return state;
    }
}
