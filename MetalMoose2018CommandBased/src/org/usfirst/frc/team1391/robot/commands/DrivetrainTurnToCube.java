package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (by an angle).
 */
public class DrivetrainTurnToCube extends Command {
    // Optional speed (if set to anything but zero)
    private double speed = 0;

    // Variables to average the vision reading over a period of time
    private double sum;
    private int counter;

    // Controls, whether the sensors were configured and PID set in the execute method
    private boolean wasInitialized = false;

    /**
     * Turn the robot to the nearest cube.
     */
    DrivetrainTurnToCube() {
        requires(Robot.myDrivetrain);
        requires(Robot.myVisionSystemClient);
    }

    /**
     * Turn the robot to the nearest cube at a certain speed.
     *
     * @param speed The speed at which to turn.
     */
    DrivetrainTurnToCube(double speed) {
        requires(Robot.myDrivetrain);
        requires(Robot.myVisionSystemClient);

        this.speed = speed;
    }

    protected void initialize() {
    }

    /**
     * Collects vision data, then sets PID and starts to turn depending on PID output.
     */
    protected void execute() {
    	// Collect angle data for the valueCollectionLength
        if (timeSinceInitialized() < RobotMap.visionValueCollectionLength) {
            // Update vision
    		Robot.myVisionSystemClient.updateVision();

    		// Get data
            sum += Robot.myVisionSystemClient.getVisionAngle();
            counter++;

            // Update DifferentialDrive
            Robot.myDrivetrain.arcadeDrive(0, 0);
    	}

    	// Reset and initialize the encoder after collecting the data
    	else if (!wasInitialized) {
            Robot.myDrivetrain.myAHRS.reset();
            
            // Set point, enable gyro PID
            Robot.myDrivetrain.gyroPID.setSetpoint((sum/counter) * RobotMap.visionCoefficient);
            Robot.myDrivetrain.gyroPID.reset();
            Robot.myDrivetrain.gyroPID.enable();

            // The values were initialized (so this code is not called again)
            wasInitialized = true;
        } else {
            double xSpeed = Robot.myDrivetrain.gyroPID.get();

            if (speed != 0) xSpeed = (xSpeed / RobotMap.autonomousDefaultTurningSpeed) * speed;

            Robot.myDrivetrain.arcadeDrive(0, xSpeed);
        }
    }

    /**
     * Finished when it hits the gyroPID target after it was initialized.
     */
    protected boolean isFinished() {
        return (Robot.myDrivetrain.gyroPID.onTarget() && wasInitialized;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
