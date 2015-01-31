package org.usfirst.frc.team1318.robot.Common.ToggleButtons;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Defines a simple toggle that switches between true and false.
 * 
 * Toggle on press behavior:
 * 
 *     button pressed:        _________________
 *                           |                 |
 * button not pressed: ______|                 |________
 *                           ^ takes effect when first pressed
 * 
 * Toggle on release behavior:
 * 
 *     button pressed:        _________________
 *                           |                 |
 * button not pressed: ______|                 |________
 *                                             ^ takes effect when first released
 * 
 * 
 * @author Will
 *
 */
public class SimpleToggleButton implements ISimpleToggle
{
    private final boolean toggleOnPress;
    private Joystick joystick;
    private int button;

    private boolean currentState;
    private boolean prevButtonState;

    /**
     * Initializes a new SimpleToggleButton
     */
    public SimpleToggleButton(Joystick joystick, int button)
    {
        this(joystick, button, true);
    }

    /**
     * Initializes a new SimpleToggleButton
     * @param toggleOnPress indicates whether we should toggle when the button is first pressed or when released
     */
    public SimpleToggleButton(Joystick joystick, int button, boolean toggleOnPress)
    {
        this.currentState = false;
        this.prevButtonState = false;

        this.joystick = joystick;
        this.button = button;

        this.toggleOnPress = toggleOnPress;
    }

    /**
     * Attempt to change the current state using the information provided in the constructor)
     */
    public void update()
    {
        this.updateState(this.joystick.getRawButton(this.button));
    }

    /**
     * Attempt to change the current state
     * @param buttonState the current position of the button (whether it is currently pressed)
     */
    public void updateState(boolean buttonState)
    {
        // if button has switched state, check if we want to toggle
        if (this.prevButtonState != buttonState &&
            (this.toggleOnPress && buttonState || !this.toggleOnPress && !buttonState))
        {
            this.currentState = !this.currentState;
        }

        this.prevButtonState = buttonState;
    }

    /**
     * Gets a value indicating whether this is currently toggled
     * @return true if toggled, otherwise false
     */
    public boolean isToggled()
    {
        return this.currentState;
    }
}
