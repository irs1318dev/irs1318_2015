package org.usfirst.frc.team1318.robot.Test;

import org.usfirst.frc.team1318.robot.ElectronicsConstants;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class TestComponent
{
    private final Talon talon;
    private final DoubleSolenoid solenoid;

    private final Encoder encoder;
    private final DigitalInput input;

    public TestComponent()
    {
        this.talon = new Talon(ElectronicsConstants.TEST_TALON_CHANNEL);

        this.solenoid = new DoubleSolenoid(
            ElectronicsConstants.TEST_SOLENOID_EXTEND_CHANNEL,
            ElectronicsConstants.TEST_SOLENOID_RETRACT_CHANNEL);

        this.encoder = new Encoder(ElectronicsConstants.TEST_ENCODER_CHANNEL_A, ElectronicsConstants.TEST_ENCODER_CHANNEL_B);
        this.input = new DigitalInput(ElectronicsConstants.TEST_DIGITAL_INPUT_CHANNEL);
    }

    public void setComponent(boolean solenoidState, double talonPower)
    {
        this.talon.set(talonPower);
        this.solenoid.set(solenoidState ? Value.kForward : Value.kReverse);

        SmartDashboardLogger.putBoolean("TestComponent.Solenoid", solenoidState);
        SmartDashboardLogger.putNumber("TestComponent.Talon", talonPower);
    }

    public double getAnalogSensor()
    {
        double analogSensorValue = this.encoder.getDistance();

        SmartDashboardLogger.putNumber("TestComponent.AnalogSensor", analogSensorValue);

        return analogSensorValue;
    }

    public boolean getDigitalSensor()
    {
        boolean digitalSensorValue = this.input.get();

        SmartDashboardLogger.putBoolean("TestComponent.DigitalSensor", digitalSensorValue);

        return digitalSensorValue;
    }
}
