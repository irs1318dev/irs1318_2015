package org.usfirst.frc.team1318.robot.Autonomous.Tasks;

import org.usfirst.frc.team1318.robot.Autonomous.AutonomousControlData;
import org.usfirst.frc.team1318.robot.Autonomous.IAutonomousTask;

public class DriveForwardTask implements IAutonomousTask
{

    public DriveForwardTask()
    {

    }

    @Override
    public void begin()
    {

    }

    @Override
    public void update(AutonomousControlData data)
    {
        data.setDriveTrainYVelocity(.5);
    }

    @Override
    public void cancel(AutonomousControlData data)
    {
        data.setDriveTrainYVelocity(0);
    }

    @Override
    public void end(AutonomousControlData data)
    {
        data.setDriveTrainYVelocity(0);
    }

    @Override
    public boolean shouldContinue()
    {
        return true;
    }

}
