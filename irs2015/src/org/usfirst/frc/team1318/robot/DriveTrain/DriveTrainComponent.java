package org.usfirst.frc.team1318.robot.DriveTrain;

import org.usfirst.frc.team1318.robot.ElectronicsConstants;
import org.usfirst.frc.team1318.robot.HardwareConstants;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 * The drivetrain component class describes the electronics of the drivetrain and defines the abstract way to control it.
 * The drivetrain electronics include two talons (left and right), and two encoders (left and right). 
 *  
 * @author Will
 *
 */
public class DriveTrainComponent implements IDriveTrainComponent
{
    // logging constants
    public static final String LEFT_TALON_POWER_LOG_KEY = "dt.leftPower";
    public static final String RIGHT_TALON_POWER_LOG_KEY = "dt.rightPower";
    public static final String LEFT_ENCODER_VELOCITY_LOG_KEY = "dt.leftEncoderVelocity";
    public static final String RIGHT_ENCODER_VELOCITY_LOG_KEY = "dt.rightEncoderVelocity";
    public static final String LEFT_ENCODER_DISTANCE_LOG_KEY = "dt.leftEncoderDistance";
    public static final String RIGHT_ENCODER_DISTANCE_LOG_KEY = "dt.rightEncoderDistance";
    public static final String SHIFTER_STATE_LOG_KEY = "dt.shifterState";
    public static final String ARM_EXTENDER_LOG_KEY = "ar.extender";
    public static final String ARM_TILT_LOG_KEY = "ar.tilt";
    public static final String INTAKE_SOLENOID_KEY = "in.solenoid";

    private Talon leftTalon;
    private Talon rightTalon;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private DoubleSolenoid trombone;
    private DoubleSolenoid armExtender;
    private DoubleSolenoid armTilt;
    private DoubleSolenoid intakeSolenoid;

    private Talon intakeRight;
    private Talon intakeLeft;

    /**
     * Initializes a new DriveTrainComponent
     */
    public DriveTrainComponent()
    {
        this.leftTalon = new Talon(
            ElectronicsConstants.DRIVETRAIN_LEFT_TALON_CHANNEL);

        this.rightTalon = new Talon(
            ElectronicsConstants.DRIVETRAIN_RIGHT_TALON_CHANNEL);

        this.leftEncoder = new Encoder(
            ElectronicsConstants.DRIVETRAIN_LEFT_ENCODER_CHANNEL_A,
            ElectronicsConstants.DRIVETRAIN_LEFT_ENCODER_CHANNEL_B);

        this.rightEncoder = new Encoder(
            ElectronicsConstants.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A,
            ElectronicsConstants.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B);

        this.leftEncoder.setDistancePerPulse(HardwareConstants.DRIVETRAIN_LEFT_PULSE_DISTANCE);
        this.rightEncoder.setDistancePerPulse(HardwareConstants.DRIVETRAIN_RIGHT_PULSE_DISTANCE);

        this.trombone = new DoubleSolenoid(
            ElectronicsConstants.DRIVETRAIN_SHIFTER_MODE_EXTENDER_PORT,
            ElectronicsConstants.DRIVETRAIN_SHIFTER_MODE_RETRACTER_PORT);

        this.armExtender = new DoubleSolenoid(
            ElectronicsConstants.ARM_EXTEND_MODE_EXTENDER_PORT,
            ElectronicsConstants.ARM_EXTEND_MODE_RETRACTER_PORT);

        this.armTilt = new DoubleSolenoid(
            ElectronicsConstants.ARM_TILT_MODE_EXTENDER_PORT,
            ElectronicsConstants.ARM_TILT_MODE_RETRACTER_PORT);

        this.intakeSolenoid = new DoubleSolenoid(
            ElectronicsConstants.INTAKE_SOLENOID_MODE_EXTENDER_PORT,
            ElectronicsConstants.INTAKE_SOLENOID_MODE_RETRACTER_PORT);

        intakeRight = new Talon(3);
        intakeLeft = new Talon(4);
    }

    /**
     * set the power levels to the drive train
     * @param leftPower level to apply to the left motor
     * @param rightPower level to apply to the right motor
     */
    public void setDriveTrainPower(double leftPower, double rightPower)
    {
        this.leftTalon.set(-leftPower); // note: left motors are oriented facing "backwards"
        this.rightTalon.set(rightPower);

        SmartDashboardLogger.putNumber(DriveTrainComponent.LEFT_TALON_POWER_LOG_KEY, leftPower);
        SmartDashboardLogger.putNumber(DriveTrainComponent.RIGHT_TALON_POWER_LOG_KEY, rightPower);
    }

    public void setIntakeIn()
    {
        intakeLeft.set(-0.8);
        intakeRight.set(0.8);
    }

    public void setIntakeOut()
    {
        intakeLeft.set(0.8);
        intakeRight.set(-0.8);
    }

    /**
     * set the state of the shifter
     * @param state to set the solenoid
     */
    public void setTromboneState(boolean state)
    {
        if (state)
        {
            this.trombone.set(Value.kForward);
        }
        else
        {
            this.trombone.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(DriveTrainComponent.SHIFTER_STATE_LOG_KEY, state);
    }

    public void setArmExtenderState(boolean state)
    {
        if (state)
        {
            this.armExtender.set(Value.kForward);
        }
        else
        {
            this.armExtender.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(DriveTrainComponent.ARM_EXTENDER_LOG_KEY, state);
    }

    public void setArmTiltState(boolean state)
    {
        if (state)
        {
            this.armTilt.set(Value.kForward);
        }
        else
        {
            this.armTilt.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(DriveTrainComponent.ARM_TILT_LOG_KEY, state);
    }

    public void setIntakeSolenoidState(boolean state)
    {
        if (state)
        {
            this.intakeSolenoid.set(Value.kForward);
        }
        else
        {
            this.intakeSolenoid.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(DriveTrainComponent.INTAKE_SOLENOID_KEY, state);
    }

    /**
     * get the velocity from the left encoder
     * @return a value indicating the velocity
     */
    public double getLeftEncoderVelocity()
    {
        double leftRate = this.leftEncoder.getRate();

        SmartDashboardLogger.putNumber(DriveTrainComponent.LEFT_ENCODER_VELOCITY_LOG_KEY, leftRate);

        return leftRate;
    }

    /**
     * get the velocity from the right encoder
     * @return a value indicating the velocity
     */
    public double getRightEncoderVelocity()
    {
        double rightRate = this.rightEncoder.getRate();

        SmartDashboardLogger.putNumber(DriveTrainComponent.RIGHT_ENCODER_VELOCITY_LOG_KEY, rightRate);

        return rightRate;
    }

    /**
     * get the distance from the left encoder
     * @return a value indicating the distance
     */
    public double getLeftEncoderDistance()
    {
        double leftDistance = this.leftEncoder.getDistance();

        SmartDashboardLogger.putNumber(DriveTrainComponent.LEFT_ENCODER_DISTANCE_LOG_KEY, leftDistance);

        return leftDistance;
    }

    /**
     * get the distance from the right encoder
     * @return a value indicating the distance
     */
    public double getRightEncoderDistance()
    {
        double rightDistance = this.rightEncoder.getDistance();

        SmartDashboardLogger.putNumber(DriveTrainComponent.RIGHT_ENCODER_DISTANCE_LOG_KEY, rightDistance);

        return rightDistance;
    }
}
