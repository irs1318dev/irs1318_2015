package org.usfirst.frc.team1318.robot.Common;

/**
 * The "driver" describes the currently requested actions that the robot should be performing.  The driver could be either
 * a user controlling the robot with a joystick, or autonomous mode controlling the robot based on a queue of predetermined
 * tasks which each have their own lifecycle.
 * 
 * @author Will
 *
 */
public interface IDriver
{
    //Drive Train 
    static final String DRIVETRAIN_X_VELOCITY = "driveXVelocity";
    static final String DRIVETRAIN_Y_VELOCITY = "driveYVelocity";
    static final String DRIVETRAIN_SIMPLE_MODE = "driveSimpleMode";
    static final String DRIVETRAIN_LEFT_POSITION = "driveLeftPosition";
    static final String DRIVETRAIN_RIGHT_POSITION = "driveRightPosition";
    static final String DRIVETRAIN_POSITION_MODE = "drivePositionMode";

    //Elevator 
    static final String ELEVATOR_MACRO_STATE = "elevatorMacroState";
    static final String ELEVATOR_HEIGHT_0_STATE = "elevatorHeight0";
    static final String ELEVATOR_HEIGHT_1_STATE = "elevatorHeight1";
    static final String ELEVATOR_HEIGHT_2_STATE = "elevatorHeight2";
    static final String ELEVATOR_HEIGHT_3_STATE = "elevatorHeight3";
    static final String ELEVATOR_HEIGHT_4_STATE = "elevatorHeight4";
    static final String ELEVATOR_HEIGHT_5_STATE = "elevatorHeight5";
    static final String ELEVATOR_HEIGHT_6_STATE = "elevatorHeight6";
    static final String ELEVATOR_HEIGHT_7_STATE = "elevatorHeight7";
    static final String ELEVATOR_OVERRIDE_STATE = "elevatorOverride";

    //Arm 
    static final String ARM_MACRO_STATE = "armMacroState";
    static final String ARM_EXTENDER_STATE = "armExtenderOverride";
    static final String ARM_TILT_STATE = "armTiltOverride";
    static final String ARM_TROMBONE_STATE = "armTromboneOverride";

    public String getLogKey(String key);

    /**
     * Tell the driver that some time has passed
     */
    public void update();

    /**
     * Tell the driver that operation is stopping
     */
    public void stop();

    public double getDouble(String key);

    public boolean getBoolean(String key);
}
