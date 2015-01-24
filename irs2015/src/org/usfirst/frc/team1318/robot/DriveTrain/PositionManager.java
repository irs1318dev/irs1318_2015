package org.usfirst.frc.team1318.robot.DriveTrain;

import org.usfirst.frc.team1318.robot.HardwareConstants;
import org.usfirst.frc.team1318.robot.Common.IController;
import org.usfirst.frc.team1318.robot.Common.SmartDashboardLogger;

/**
 * Position manager
 * 
 * This class maintains the approximate current location and orientation of the robot relative to its starting point.
 * This uses Jim's differential odometry algorithm. In the future we can consider adding other sensors to help correct for error.
 * 
 * @author Will
 */
public class PositionManager implements IController
{
    // logging constants
    public static final String ANGLE_LOG_KEY = "pos.angle";
    public static final String X_POSITION_LOG_KEY = "pos.x";
    public static final String Y_POSITION_LOG_KEY = "pos.y";

    // drivetrain component
    private final DriveTrainComponent driveTrainComponent;

    // Position coordinates
    private double x;
    private double y;

    // Orientation
    private double angle;

    // previous data (from which we will calculate changes)
    private double prevLeftDistance;
    private double prevRightDistance;

    /**
     * Initializes a new PositionManager
     * @param driveTrainComponent to use to determine position changes
     */
    public PositionManager(DriveTrainComponent driveTrainComponent)
    {
        this.x = 0.0;
        this.y = 0.0;

        this.angle = 0.0;

        this.prevLeftDistance = 0.0;
        this.prevRightDistance = 0.0;

        this.driveTrainComponent = driveTrainComponent;
    }

    /**
     * calculate the various outputs to use based on the inputs and apply them to the outputs for the relevant component
     */
    public void update()
    {
        // check the current distance recorded by the encoders
        double leftDistance = this.driveTrainComponent.getLeftEncoderDistance();
        double rightDistance = this.driveTrainComponent.getRightEncoderDistance();

        // calculate the angle (in radians) based on the total distance traveled
        this.angle = ((rightDistance - leftDistance) / HardwareConstants.DRIVETRAIN_WHEEL_DISTANCE) % (2 * Math.PI);

        // calculate the average distance traveled
        double averagePositionChange = ((leftDistance - this.prevLeftDistance) + (rightDistance - this.prevRightDistance)) / 2;

        // calculate the change since last time, and update our relative position
        this.x += averagePositionChange * Math.cos(this.angle);
        this.y += averagePositionChange * Math.sin(this.angle);

        // record distance for next time
        this.prevLeftDistance = leftDistance;
        this.prevRightDistance = rightDistance;

        // log the current position and orientation
        SmartDashboardLogger.putNumber(PositionManager.ANGLE_LOG_KEY, this.angle);
        SmartDashboardLogger.putNumber(PositionManager.X_POSITION_LOG_KEY, this.x);
        SmartDashboardLogger.putNumber(PositionManager.Y_POSITION_LOG_KEY, this.y);
    }

    /**
     * stop the relevant component
     */
    public void stop()
    {
    }

    /**
     * Retrieve the current angle in degrees
     * @return the current angle in degrees
     */
    public double getAngle()
    {
        return this.angle * 180 / Math.PI;
    }

    /**
     * Retrieve the current x position
     * @return the current x position
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * Retrieve the current y position
     * @return the current y position
     */
    public double getY()
    {
        return this.y;
    }
}
