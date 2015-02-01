package org.usfirst.frc.team1318.robot;

/**
 * All constants describing how the electronics are plugged together.
 * 
 * @author Will
 * 
 */
public class ElectronicsConstants
{
    public static final int DRIVETRAIN_LEFT_TALON_CHANNEL = 9;
    public static final int DRIVETRAIN_RIGHT_TALON_CHANNEL = 8;

    public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 6;
    public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 7;

    public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 8;
    public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 9;

    public static final int ARM_EXTEND_MODE_EXTENDER_PORT = -1;
    public static final int ARM_EXTEND_MODE_RETRACTER_PORT = -1;

    public static final int ARM_TILT_MODE_EXTENDER_PORT = -1;
    public static final int ARM_TILT_MODE_RETRACTER_PORT = -1;

    public static final int INTAKE_SOLENOID_MODE_EXTENDER_PORT = -1;
    public static final int INTAKE_SOLENOID_MODE_RETRACTER_PORT = -1;

    public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL = 6;
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;

    public static final int COMPRESSOR_ANALOG_PRESSURE_SENSOR = 4;

    public static final double COMPRESSOR_MAX_PSI = 150.0;
    public static final double COMPRESSOR_MAX_VOLTAGE = 10.0;

    // For random testing only!
    public static final int TEST_TALON_CHANNEL = -1;
    public static final int TEST_SOLENOID_EXTEND_CHANNEL = 3;
    public static final int TEST_SOLENOID_RETRACT_CHANNEL = 2;
    public static final int TEST_ENCODER_CHANNEL_A = -1;
    public static final int TEST_ENCODER_CHANNEL_B = -1;
    public static final int TEST_DIGITAL_INPUT_CHANNEL = -1;
}
