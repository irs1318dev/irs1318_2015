package org.usfirst.frc.team1318.robot.Lifter;

import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.IDriver;

public class LifterController implements IController
{

    private IDriver operator;
    private ILifterComponent component;

    public LifterController(IDriver operator, ILifterComponent component)
    {
        this.operator = operator;
        this.component = component;
    }

    @Override
    public void update()
    {
        this.component.setLifterState(this.operator.getLifterButtonMode());
    }

    @Override
    public void stop()
    {

    }

}
