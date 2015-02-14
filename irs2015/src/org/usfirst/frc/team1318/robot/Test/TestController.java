package org.usfirst.frc.team1318.robot.Test;

import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.IDriver;

import edu.wpi.first.wpilibj.Timer;

public class TestController implements IController
{
    private final IDriver driver;
    private final TestComponent component;
    private final Timer timer;

    public TestController(IDriver driver, TestComponent component)
    {
        this.driver = driver;
        this.component = component;

        this.timer = new Timer();
        this.timer.start();
    }

    @Override
    public void update()
    {
        //        double currentTime = this.timer.get();
        //        boolean go = this.driver.getDriveTrainSimpleMode();
        //        if (currentTime % 10 > 5)
        //        {
        //            this.component.setComponent(go, 0.2);
        //        }
        //        else
        //        {
        //            this.component.setComponent(go, -0.2);
        //        }
        //
        //        this.component.getAnalogSensor();
        //        this.component.getDigitalSensor();
        component.getThroughBeamSensor();
        component.getProximitySensor();
        component.getProximitySensor2();
    }

    @Override
    public void stop()
    {
        this.component.setComponent(false, 0.0);
    }
}
