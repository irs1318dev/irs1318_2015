package org.usfirst.frc.team1318.SensorTest;

import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.IDriver;

public class SensorTestController implements IController
{
    SensorTestComponent sensorTest;
    IDriver driver;

    public SensorTestController(IDriver driver, SensorTestComponent sensorTest)
    {
        this.sensorTest = sensorTest;
        this.driver = driver;
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        if (driver.getElevatorPIDOn())
        {
            sensorTest.checkBooleanPressureSensor();
            sensorTest.checkUltrasonicSensor0();
            sensorTest.checkUltrasonicSensor1();
            sensorTest.checkDipSwitch0();
            sensorTest.checkDipSwitch1();
            sensorTest.checkDipSwitch2();
            sensorTest.checkEncoder0();
            sensorTest.checkEncoder1();
            sensorTest.checkEncoder2();
            sensorTest.checkPressureSensor();
            sensorTest.checkThroughBeamSensor();
        }

    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

    }

}
