package org.usfirst.frc.team1318.robot.Test;

import org.usfirst.frc.team1318.robot.ElectronicsConstants;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

import edu.wpi.first.wpilibj.AnalogInput;

public class TestComponent
{
    private static final String THROUHG_BEAM_SENSOR_ANALOG_LOG_KEY = "t.throughBeamAnalog";
    private static final String THROUGH_BEAM_SENSOR_BOOLEAN_LOG_KEY = "t.throughBeamSensorBoolean";

    //    private Talon talon;
    //    private DoubleSolenoid solenoid;
    //
    //    private Encoder encoder;
    //    private DigitalInput input;
    private AnalogInput throughBeamSensor;

    public TestComponent()
    {
        //this.talon = new Talon(ElectronicsConstants.TEST_TALON_CHANNEL);
        //
        //        this.solenoid = new DoubleSolenoid(
        //            ElectronicsConstants.TEST_SOLENOID_EXTEND_CHANNEL,
        //            ElectronicsConstants.TEST_SOLENOID_RETRACT_CHANNEL);

        //this.encoder = new Encoder(ElectronicsConstants.TEST_ENCODER_CHANNEL_A, ElectronicsConstants.TEST_ENCODER_CHANNEL_B);
        //this.input = new DigitalInput(ElectronicsConstants.TEST_DIGITAL_INPUT_CHANNEL);
        throughBeamSensor = new AnalogInput(ElectronicsConstants.TEST_THROUHG_BEAM_SENSOR_PORT);
    }

    public void setComponent(boolean solenoidState, double talonPower)
    {
        //this.talon.set(talonPower);
        //        this.solenoid.set(solenoidState ? Value.kForward : Value.kReverse);
        //
        //        SmartDashboardLogger.putBoolean("TestComponent.Solenoid", solenoidState);
        //        SmartDashboardLogger.putNumber("TestComponent.Talon", talonPower);
    }

    public void getThroughBeamSensor()
    {
        double value = throughBeamSensor.getVoltage();
        SmartDashboardLogger.putNumber(THROUHG_BEAM_SENSOR_ANALOG_LOG_KEY, value);
        boolean valueBool = (value < 2.5);
        SmartDashboardLogger.putBoolean(THROUGH_BEAM_SENSOR_BOOLEAN_LOG_KEY, valueBool);
    }

    public double getAnalogSensor()
    {
        double analogSensorValue = 0.0; //this.encoder.getDistance();

        SmartDashboardLogger.putNumber("TestComponent.AnalogSensor", analogSensorValue);

        return analogSensorValue;
    }

    public boolean getDigitalSensor()
    {
        boolean digitalSensorValue = false; //this.input.get();

        SmartDashboardLogger.putBoolean("TestComponent.DigitalSensor", digitalSensorValue);

        return digitalSensorValue;
    }
}
