package org.usfirst.frc.team1318.robot.Lifter;

import org.usfirst.frc.team1318.robot.ElectronicsConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class LifterComponent implements ILifterComponent
{
    DoubleSolenoid soleniod1;
    DoubleSolenoid solenoid2;

    /**
     * Creates two new solenoids 
     */
    public LifterComponent()
    {
        soleniod1 = new DoubleSolenoid(ElectronicsConstants.LIFTER_1_EXTENDER, ElectronicsConstants.LIFTER_1_RETRACTER);
        solenoid2 = new DoubleSolenoid(ElectronicsConstants.LIFTER_2_EXTENDER, ElectronicsConstants.LIFTER_2_RETRACTER);
    }

    /**
     * Sets both solenoids together 
     * @param state true for extend, false for retract
     */
    public void setLifterState(boolean state)
    {
        if (state)
        {
            soleniod1.set(Value.kForward);
            solenoid2.set(Value.kForward);
        }
        else
        {
            soleniod1.set(Value.kReverse);
            solenoid2.set(Value.kReverse);
        }
    }

}
