package org.usfirst.frc.team1318.robot.UserInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.usfirst.frc.team1318.robot.JoystickButtonConstants;
import org.usfirst.frc.team1318.robot.Common.Getter;
import org.usfirst.frc.team1318.robot.Common.IDriver;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;
import org.usfirst.frc.team1318.robot.Common.ToggleButtons.SimpleToggleButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Driver for teleop mode.  User driver translates current toggle state and joystick state into
 * the specific actions that should be taken by the robot.
 * 
 * @author Will
 *
 */
public class UserDriver implements IDriver
{
    private Map<String, Double> doubles;

    private Joystick joystick;
    private Map<String, SimpleToggleButton> buttons;
    private Map<String, Getter> getters;

    public String getLogKey(String key)
    {
        return "u." + key;
    }

    /**
     * Initializes a new UserDriver
     */
    public UserDriver()
    {
        this.joystick = new Joystick(JoystickButtonConstants.JOYSTICK_PORT);
        this.buttons = new HashMap<String, SimpleToggleButton>();
        this.getters = new HashMap<String, Getter>();

        //===================== Drive Train =====================
        getters.put(IDriver.DRIVETRAIN_X_VELOCITY, Getter.CreateJoystickAxisGetter(joystick, 1));
        getters.put(IDriver.DRIVETRAIN_Y_VELOCITY, Getter.CreateJoystickAxisGetter(joystick, 2, true));
        getters.put(IDriver.DRIVETRAIN_SIMPLE_MODE, Getter.CreateSimpleToggleButtonGetter(joystick, buttons, "simpleDriveModeButton"));

        //====================== Elevator =======================
        getters.put(IDriver.ELEVATOR_MACRO_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_MACRO_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_0_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_0_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_1_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_1_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_2_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_2_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_3_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_3_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_4_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_4_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_5_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_5_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_6_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_6_BUTTON));
        getters.put(IDriver.ELEVATOR_HEIGHT_7_STATE, Getter.CreateJoystickButtonGetter(joystick,
            JoystickButtonConstants.ELEVATOR_HEIGHT_7_BUTTON));
        getters.put(IDriver.ELEVATOR_OVERRIDE_STATE, Getter.CreateJoystickAxisGetter(joystick, 3));

        //Arm
        this.buttons.put("armMacroToggleButton", new SimpleToggleButton(this.joystick, JoystickButtonConstants.ARM_MACRO_BUTTON));
        this.buttons.put("armExtenderToggleOverride", new SimpleToggleButton(this.joystick, JoystickButtonConstants.ARM_EXTENDER_BUTTON));
        this.buttons.put("armTiltToggleOverride", new SimpleToggleButton(this.joystick, JoystickButtonConstants.ARM_TILT_BUTTON));
        this.buttons.put("armTromboneToggleOverride", new SimpleToggleButton(this.joystick, JoystickButtonConstants.ARM_TROMBONE_BUTTON));
    }

    /**
     * Tell the driver component that some time has passed
     */
    public void update()
    {
        for (Entry<String, SimpleToggleButton> entry : this.buttons.entrySet())
        {
            entry.getValue().update();
        }
    }

    /**
     * Tell the driver that operation is stopping
     */
    public void stop()
    {
    }

    public double getDouble(String key)
    {
        double var = this.getters.get(key).GetDouble();
        SmartDashboardLogger.putNumber(this.getLogKey(key), var);
        return var;
    }

    public boolean getBoolean(String key)
    {
        boolean var = this.getters.get(key).GetBoolean();
        SmartDashboardLogger.putBoolean(this.getLogKey(key), var);
        return var;
    }

    //===================================================== Arm =================================================================

    @Override
    public boolean getArmMacroToggle()
    {
        boolean mode = this.buttons.get("armMacroToggleButton").isToggled();
        SmartDashboardLogger.putBoolean(UserDriver.ARM_MACRO_STATE_LOG_KEY, mode);
        return mode;
    }

    @Override
    public boolean getArmExtenderToggleOverride()
    {
        boolean mode = this.buttons.get("armExtenderToggleOverride").isToggled();
        SmartDashboardLogger.putBoolean(UserDriver.ARM_EXTENDER_OVERRIDE_LOG_KEY, mode);
        return mode;
    }

    @Override
    public boolean getArmTiltToggleOverride()
    {
        boolean mode = this.buttons.get("armTiltToggleOverride").isToggled();
        SmartDashboardLogger.putBoolean(UserDriver.ARM_TILT_OVERRIDE_LOG_KEY, mode);
        return mode;
    }

    @Override
    public boolean getArmTromboneToggleOverride()
    {
        boolean mode = this.buttons.get("armTromboneToggleOverride").isToggled();
        SmartDashboardLogger.putBoolean(UserDriver.ARM_TROMBONE_OVERRIDE_LOG_KEY, mode);
        return mode;
    }

    //=================================================== Intake ================================================================

    @Override
    public boolean getIntakeUpButton()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getIntakeDownButton()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getIntakeRightToggleOverride()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getIntakeLeftToggleOverride()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getIntakeForwardButton()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getIntakeBackwardButton()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
