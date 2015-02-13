package org.usfirst.frc.team1318.robot.Arm;

import org.usfirst.frc.team1318.robot.ElectronicsConstants;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ArmComponent
{
    private final DoubleSolenoid trombone;
    private final DoubleSolenoid tiltLinkage;
    private final DoubleSolenoid extendLinkage;

    // logging constants
    private static final String TROMBONE_STATE_LOG_KEY = "ar.TromboneState";
    private static final String EXTEND_LINKAGE_STATE_LOG_KEY = "ar.ExtendLinkageState";
    private static final String TILT_LINKAGE_STATE_LOG_KEY = "ar.TiltLinkageState";

    // constructor uses constants for extension and retraction of 3 solenoids that make up the arm 
    public ArmComponent()
    {
        this.trombone = new DoubleSolenoid(
            ElectronicsConstants.PCM_A_MODULE,
            ElectronicsConstants.ARM_TROMBONE_SOLANOID_EXTEND,
            ElectronicsConstants.ARM_TROMBONE_SOLANOID_RETRACT);

        this.tiltLinkage = new DoubleSolenoid(
            ElectronicsConstants.PCM_A_MODULE,
            ElectronicsConstants.ARM_TILT_LINK_SOLANOID_EXTEND,
            ElectronicsConstants.ARM_TILT_LINK_SOLANOID_RETRACT);

        this.extendLinkage = new DoubleSolenoid(
            ElectronicsConstants.PCM_A_MODULE,
            ElectronicsConstants.ARM_EXTEND_LINK_SOLANOID_EXTEND,
            ElectronicsConstants.ARM_EXTEND_LINK_SOLANOID_RETRACT);
    }

    /**
     * sets state of Trombone_Solenoid
     * @param true for extended state; false for retracted state
     */
    public void setTromboneSolenoidState(boolean state)
    {
        if (state)
        {
            this.trombone.set(Value.kForward);
        }
        else
        {
            this.trombone.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(ArmComponent.TROMBONE_STATE_LOG_KEY, state);
    }

    /**
     * sets state of TiltLinkage_Solenoid
     * @param true for extended state; false for retracted state
     */
    public void setTiltLinkageSolenoidState(boolean state)
    {
        if (state)
        {
            this.tiltLinkage.set(Value.kForward);
        }
        else
        {
            this.tiltLinkage.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(ArmComponent.TILT_LINKAGE_STATE_LOG_KEY, state);
    }

    /**
     * sets state of Extend_Linkage_Solenoid
     * @param true for extended state; false for retracted state
     */
    public void setExtendLinkageSolenoidState(boolean state)
    {
        if (state)
        {
            this.extendLinkage.set(Value.kForward);
        }
        else
        {
            this.extendLinkage.set(Value.kReverse);
        }

        SmartDashboardLogger.putBoolean(ArmComponent.EXTEND_LINKAGE_STATE_LOG_KEY, state);
    }
}
