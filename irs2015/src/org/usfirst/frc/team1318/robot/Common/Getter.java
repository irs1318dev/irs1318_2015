package org.usfirst.frc.team1318.robot.Common;

import java.util.Map;

import org.usfirst.frc.team1318.robot.JoystickButtonConstants;
import org.usfirst.frc.team1318.robot.Common.ToggleButtons.SimpleToggleButton;

import edu.wpi.first.wpilibj.Joystick;

public class Getter
{
    private enum GetterType
    {
        SimpleToggleButton,
        JoystickButton,
        JoystickAxis
    }

    private Joystick joystick;
    private int number;
    private SimpleToggleButton toggleButton;
    private GetterType getterType;
    private boolean invertAxis;

    private Getter()
    {

    }

    public double GetDouble()
    {
        switch (getterType)
        {
            case SimpleToggleButton:
                return toggleButton.isToggled() ? 1 : 0;
            case JoystickButton:
                return joystick.getRawButton(number) ? 1 : 0;
            case JoystickAxis:
                if (invertAxis)
                    return -joystick.getRawAxis(number);
                return joystick.getRawAxis(number);
            default:
                return 0;
        }
    }

    public boolean GetBoolean()
    {
        switch (getterType)
        {
            case SimpleToggleButton:
                return toggleButton.isToggled();
            case JoystickButton:
                return joystick.getRawButton(number);
            case JoystickAxis:
                return (joystick.getRawAxis(number) != 0);
            default:
                return false;
        }
    }

    public static Getter CreateSimpleToggleButtonGetter(Joystick joystick, Map<String, SimpleToggleButton> buttons, String buttonKey)
    {
        SimpleToggleButton b = new SimpleToggleButton(joystick, JoystickButtonConstants.DRIVETRAIN_SIMPLE_BUTTON);
        buttons.put(buttonKey, b);
        Getter getter = new Getter();
        getter.getterType = GetterType.SimpleToggleButton;
        getter.toggleButton = b;
        return getter;
    }

    public static Getter CreateJoystickButtonGetter(Joystick joystick, int buttonNum)
    {
        Getter getter = new Getter();
        getter.getterType = GetterType.JoystickButton;
        getter.number = buttonNum;
        return getter;
    }

    public static Getter CreateJoystickAxisGetter(Joystick joystick, int axisNum, boolean invertAxis)
    {
        Getter getter = new Getter();
        getter.getterType = GetterType.JoystickAxis;
        getter.number = axisNum;
        getter.invertAxis = invertAxis;
        return getter;
    }

    public static Getter CreateJoystickAxisGetter(Joystick joystick, int axisNum)
    {
        return CreateJoystickAxisGetter(joystick, axisNum, false);
    }
}
