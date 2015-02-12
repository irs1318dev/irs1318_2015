package org.usfirst.frc.team1318.SensorTest;

import org.usfirst.frc.team1318.robot.ElectronicsConstants;
import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 * 
 * @author grclaeys
 * sensors that will be on the robot and need to be tested
 * 
 * One through beam sensor
 * Two Ultrasonic sensors
 * 3 Dip switches
 * 3 encoders
 * void Pressure Sensor
 * Current Pressure Sensors
 * 
 *
 */
public class SensorTestComponent implements IController
{
    //Stores digital input from sensors
    private final DigitalInput throughBeamInput;
    private final DigitalInput ultrasonicInput0;
    private final DigitalInput ultrasonicInput1;
    private final DigitalInput dipSwitchInput0;
    private final DigitalInput dipSwitchInput1;
    private final DigitalInput dipSwitchInput2;
    private final Encoder encoderInput0;
    private final Encoder encoderInput1;
    private final Encoder encoderInput2;
    private final DigitalInput pressureInputState;
    private final DigitalInput pressureInputPSI;

    // keys declarations   
    //ts is short for test
    private static final String THROUGH_BEAM_SENSOR_KEY = "ts.throughBeamSensor";
    private static final String ULTRASONIC_SENSOR_0_KEY = "ts.ultrasonicSensor0";
    private static final String ULTRASONIC_SENSOR_1_KEY = "ts.ultrasonicSensor1";
    private static final String DIP_SWITCH_0_KEY = "ts.dipSwitch0";
    private static final String DIP_SWITCH_1_KEY = "ts.dipSwitch1";
    private static final String DIP_SWITCH_2_KEY = "ts.dipswitch2";
    private static final String ENCODER_0_KEY = "ts.encoder0";
    private static final String ENCODER_1_KEY = "ts.encoder1";
    private static final String ENCODER_2_KEY = "ts.encoder2";
    private static final String BOOLEAN_PRESSURE_SENSOR = "ts.booleanPressureSensor";
    private static final String CURRENT_PRESSURE_SENSOR = "ts.currentPressureSensor";

    public SensorTestComponent()
    {
        // TODO replace with sensor.getDigitalInput or equivalent operation
        throughBeamInput = new DigitalInput(ElectronicsConstants.THROGUH_BEAM_SENSOR_PORT);
        ultrasonicInput0 = new DigitalInput(ElectronicsConstants.ULTRASONIC_SENSOR_PORT_1);
        ultrasonicInput1 = new DigitalInput(ElectronicsConstants.ULTRASONIC_SENSOR_PORT_2);
        dipSwitchInput0 = new DigitalInput(ElectronicsConstants.DIP_SWITCH_PORT_1);
        dipSwitchInput1 = new DigitalInput(ElectronicsConstants.DIP_SWITCH_PORT_2);
        dipSwitchInput2 = new DigitalInput(ElectronicsConstants.DIP_SWITCH_PORT_3);
        encoderInput0 = new Encoder(ElectronicsConstants.ENCODER_PORT_1A, ElectronicsConstants.ENCODER_PORT_1B);
        encoderInput1 = new Encoder(ElectronicsConstants.ENCODER_PORT_2A, ElectronicsConstants.ENCODER_PORT_2B);
        encoderInput2 = new Encoder(ElectronicsConstants.ENCODER_PORT_3A, ElectronicsConstants.ENCODER_PORT_3B);
        pressureInputState = new DigitalInput(ElectronicsConstants.PRESSURE_STATE_SENSOR_PORT);
        pressureInputPSI = new DigitalInput(ElectronicsConstants.CURRENT_PRESSURE_SENSOR_PORT);
    }

    // Checks the status of the through beam sensor and puts it in the SmartDashboard
    public void checkThroughBeamSensor()
    {
        boolean value = throughBeamInput.get();
        SmartDashboardLogger.putBoolean(THROUGH_BEAM_SENSOR_KEY, value);
    }

    // Checks the status of ultrasonic sensor 0 and puts it in the SmartDashboard
    public void checkUltrasonicSensor0()
    {
        boolean value = ultrasonicInput0.get();
        SmartDashboardLogger.putBoolean(ULTRASONIC_SENSOR_0_KEY, value);
    }

    // Checks the status of ultrasonic sensor 1 and puts it in the SmartDashboard
    public void checkUltrasonicSensor1()
    {
        boolean value = ultrasonicInput1.get();
        SmartDashboardLogger.putBoolean(ULTRASONIC_SENSOR_1_KEY, value);
    }

    // Checks the status of dipswitch 0 and puts it in the SmartDashboard
    public void checkDipSwitch0()
    {
        boolean value = dipSwitchInput0.get();
        SmartDashboardLogger.putBoolean(DIP_SWITCH_0_KEY, value);
    }

    // Checks the status of dipswitch 1 and puts it in the SmartDashboard
    public void checkDipSwitch1()
    {
        boolean value = dipSwitchInput1.get();
        SmartDashboardLogger.putBoolean(DIP_SWITCH_1_KEY, value);
    }

    // Checks the status of dipswitch 2 and puts it in the SmartDashboard
    public void checkDipSwitch2()
    {
        boolean value = dipSwitchInput2.get();
        SmartDashboardLogger.putBoolean(DIP_SWITCH_2_KEY, value);
    }

    // Checks the status of encoder 0 and puts it in the SmartDashboard
    public void checkEncoder0()
    {
        double value = encoderInput0.get();
        SmartDashboardLogger.putNumber(ENCODER_0_KEY, value);
    }

    // Checks the status of encoder 1 and puts it in the SmartDashboard
    public void checkEncoder1()
    {
        double value = encoderInput1.get();
        SmartDashboardLogger.putNumber(ENCODER_1_KEY, value);
    }

    // Checks the status of encoder 2 and puts it in the SmartDashboard
    public void checkEncoder2()
    {
        double value = encoderInput2.get();
        SmartDashboardLogger.putNumber(ENCODER_2_KEY, value);
    }

    // Checks the direction of the pressure sensor and puts it in the SmartDashboard
    public void checkBooleanPressureSensor()
    {
        boolean value = pressureInputState.get();
        SmartDashboardLogger.putBoolean(BOOLEAN_PRESSURE_SENSOR, value);
    }

    // Checks the psi -> boolean value of the pressure sensor and puts it in the SmartDashboard
    public void checkPressureSensor()
    {
        boolean value = pressureInputPSI.get();
        SmartDashboardLogger.putBoolean(CURRENT_PRESSURE_SENSOR, value);
    }

    public void update()
    {
        // TODO Auto-generated method stub

    }

    public void stop()
    {
        // TODO Auto-generated method stub

    }

}
