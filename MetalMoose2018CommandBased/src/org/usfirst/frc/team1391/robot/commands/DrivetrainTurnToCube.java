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

    private double sum;
    private int counter;

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

    /**
     * Resets encoder and gyro, set goals for PID, enables PID.
     */
    protected void initialize() {
        // If we are turning for more than 4 seconds, go to the next command
        setTimeout(4);
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
    	if (timeSinceInitialized() < 0.5) {
            Robot.myDrivetrain.arcadeDrive(0, 0);
    	} else if (timeSinceInitialized() < 0.8) {
    		Robot.myVisionSystemClient.updateVision();

            sum += Robot.myVisionSystemClient.getVisionAngle();
            counter++;
            Robot.myDrivetrain.arcadeDrive(0, 0);
    	} else if (!wasInitialized) {
            Robot.myDrivetrain.myAHRS.reset();
            
            // Set point, enable gyro PID
            Robot.myDrivetrain.gyroPID.setSetpoint(sum/counter * 0.50);
            Robot.myDrivetrain.gyroPID.reset();
            Robot.myDrivetrain.gyroPID.enable();
            
            Robot.myDrivetrain.arcadeDrive(0, 0);

            wasInitialized = true;
        } else {
            double xSpeed = Robot.myDrivetrain.gyroPID.get();

            if (speed != 0) xSpeed = (xSpeed / RobotMap.autonomousDefaultTurningSpeed) * speed;

            Robot.myDrivetrain.arcadeDrive(0, xSpeed);
        }
    }

    /**
     * Finished when it hits the gyroPID target
     */
    protected boolean isFinished() {
        return (Robot.myDrivetrain.gyroPID.onTarget() || isTimedOut()) && wasInitialized;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
