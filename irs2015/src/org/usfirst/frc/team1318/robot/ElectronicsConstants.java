package org.usfirst.frc.team1318.robot;

/**
 * All constants describing how the electronics are plugged together.
 * 
 * @author Will
 * 
 */
public class ElectronicsConstants
{
    public static final double MAX_POWER_LEVEL = 1.0;

    public static final int PCM_A_MODULE = 0;
    public static final int PCM_B_MODULE = 1;

    //================================================== DriveTrain ==============================================================

    public static final int DRIVETRAIN_LEFT_TALON_CHANNEL = 2; // kitbot - 9
    public static final int DRIVETRAIN_RIGHT_TALON_CHANNEL = 1; // kitbot - 8

    public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 2; // kitbot - 6
    public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 3; // kitbot - 7

    public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 4; // kitbot - 8
    public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 5; // kitbot - 9

    public static final int DRIVETRAIN_PROXIMITY_SENSOR_BACK_PORT = 1;
    public static final int DRIVETRAIN_PROXIMITY_SENSOR_FRONT_PORT = 2;

    //================================================== Elevator ==============================================================

    public static final int ELEVATOR_TALON_CHANNEL = 0;
    public static final int ELEVATOR_ENCODER_CHANNEL_A = 1;
    public static final int ELEVATOR_ENCODER_CHANNEL_B = 0;

    public static final int ELEVATOR_CAN_STABILIZER_EXTEND = 7;
    public static final int ELEVATOR_CAN_STABILIZER_RETRACT = 0;

    public static final int ELEVATOR_BOTTOM_LIMIT_SWITCH_CHANNEL = 6;
    public static final int ELEVATOR_TOP_LIMIT_SWITCH_CHANNEL = 7;

    public static final int ELEVATOR_THROUGH_BEAM_SENSOR_CHANNEL = 0;

    public static final int ELEVATOR_LIMIT_SWITCH_LIGHT_CHANNEL = 2;
    public static final int ELEVATOR_THROUGH_BEAM_LIGHT_CHANNEL_UPPER = 1;
    public static final int ELEVATOR_THROUGH_BEAM_LIGHT_CHANNEL_RIGHT = 6;
    public static final int ELEVATOR_THROUGH_BEAM_LIGHT_CHANNEL_LEFT = 5;

    //================================================== Intake ==============================================================

    public static final int INTAKE_LEFT_TALON_CHANNEL = 3;
    public static final int INTAKE_RIGHT_TALON_CHANNEL = 4;

    public static final int INTAKE_LEFT_ARM_CHANNEL_A = 0;
    public static final int INTAKE_LEFT_ARM_CHANNEL_B = 7;

    public static final int INTAKE_BOOT_EXTEND_CHANNEL = -1;
    public static final int INTAKE_BOOT_RETRACT_CHANNEL = -1;

    //    public static final int INTAKE_RIGHT_ARM_CHANNEL_A = 7;
    //    public static final int INTAKE_RIGHT_ARM_CHANNEL_B = 0;

    //================================================== Arm ==============================================================

    public static final int ARM_TROMBONE_SOLANOID_EXTEND = 1;
    public static final int ARM_TROMBONE_SOLANOID_RETRACT = 6;

    public static final int ARM_TILT_LINK_SOLANOID_EXTEND = 2;
    public static final int ARM_TILT_LINK_SOLANOID_RETRACT = 5;

    public static final int ARM_EXTEND_LINK_SOLANOID_EXTEND = 4;
    public static final int ARM_EXTEND_LINK_SOLANOID_RETRACT = 3;

    //================================================== Autonomous ==============================================================

    public static final int AUTONOMOUS_DIP_SWITCH_A = 8; // ??
    public static final int AUTONOMOUS_DIP_SWITCH_B = 9; // ??
}
