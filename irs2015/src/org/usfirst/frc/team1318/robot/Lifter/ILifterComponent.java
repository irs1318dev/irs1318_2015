package org.usfirst.frc.team1318.robot.Lifter;

public interface ILifterComponent
{
    /**
     * Sets both solenoids together 
     * @param state true for extend, false for retract
     */
    public void setLifterState(boolean state);
}
