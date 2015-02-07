package org.usfirst.frc.team1318.SensorTest;

import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

import edu.wpi.first.wpilibj.DigitalInput;

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
 * Current Pressure Sensor
 * 
 *
 */
public class SensorTest implements IController
{
    //Stores digital input from sensors
    private final DigitalInput throughBeamInput;
    private final DigitalInput ultrasonicInput0;
    private final DigitalInput ultrasonicInput1;
    private final DigitalInput dipSwitchInput0;
    private final DigitalInput dipSwitchInput1;
    private final DigitalInput dipSwitchInput2;
    private final DigitalInput encoderInput0;
    private final DigitalInput encoderInput1;
    private final DigitalInput encoderInput2;
    private final DigitalInput pressureInputState;
    private final DigitalInput pressureInputPSI;

    // keys declarations   
    private static final String THROUGH_BEAM_SENSOR_KEY = "";
    private static final String ULTRASONIC_SENSOR_0_KEY = "";
    private static final String ULTRASONIC_SENSOR_1_KEY = "";
    private static final String DIP_SWITCH_0_KEY = "";
    private static final String DIP_SWITCH_1_KEY = "";
    private static final String DIP_SWITCH_2_KEY = "";
    private static final String ENCODER_0_KEY = "";
    private static final String ENCODER_1_KEY = "";
    private static final String ENCODER_2_KEY = "";
    private static final String BOOLEAN_PRESSURE_SENSOR = "";
    private static final String CURRENT_PRESSURE_SENSOR = "";

    public SensorTest()
    {
        //to be replaced by sensor.getDigitalInput
        throughBeamInput = null;
        ultrasonicInput0 = null;
        ultrasonicInput1 = null;
        dipSwitchInput0 = null;
        dipSwitchInput1 = null;
        dipSwitchInput2 = null;
        encoderInput0 = null;
        encoderInput1 = null;
        encoderInput2 = null;
        pressureInputState = null;
        pressureInputPSI = null;
    }

    //Checks the status of the through beam sensor
    public void checkThroughBeamSensor()
    {
        //        ITable = throughBeamInput.getTable();
        boolean value = throughBeamInput.get();
        SmartDashboardLogger.putBoolean(THROUGH_BEAM_SENSOR_KEY, value);
    }

    //    //Checks the status of the ultrasonic sensor

    public void checkUltrasonicSensor0()
    {
        boolean value = ultrasonicInput0.get();
        SmartDashboardLogger.putBoolean(ULTRASONIC_SENSOR_0_KEY, value);
    }

    public void checkUltrasonicSensor1()
    {
        boolean value = ultrasonicInput1.get();
        SmartDashboardLogger.putBoolean(ULTRASONIC_SENSOR_1_KEY, value);
    }

    //    //Checks the status of the dipswitches
    public void checkDipSwitch0()
    {
        boolean value = dipSwitchInput0.get();
        SmartDashboardLogger.putBoolean(DIP_SWITCH_0_KEY, value);
    }

    //
    public void checkDipSwitch1()
    {
        boolean value = dipSwitchInput1.get();
        SmartDashboardLogger.putBoolean(DIP_SWITCH_1_KEY, value);
    }

    //
    public void checkDipSwitch2()
    {
        boolean value = dipSwitchInput2.get();
        SmartDashboardLogger.putBoolean(DIP_SWITCH_2_KEY, value);
    }

    //    //Checks the status of the encoders
    public void checkEncoder0()
    {
        boolean value = encoderInput0.get();
        SmartDashboardLogger.putBoolean(ENCODER_0_KEY, value);
    }

    public void checkEncoder1()
    {
        boolean value = encoderInput1.get();
        SmartDashboardLogger.putBoolean(ENCODER_1_KEY, value);
    }

    public void checkEncoder2()
    {
        boolean value = encoderInput2.get();
        SmartDashboardLogger.putBoolean(ENCODER_2_KEY, value);
    }

    //    //Checks the status of the boolean pressure sensor    
    public void checkBooleanPressureSensor()
    {
        boolean value = pressureInputState.get();
        SmartDashboardLogger.putBoolean(BOOLEAN_PRESSURE_SENSOR, value);
    }

    //    //Checks the status of the regular pressure sensor
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
