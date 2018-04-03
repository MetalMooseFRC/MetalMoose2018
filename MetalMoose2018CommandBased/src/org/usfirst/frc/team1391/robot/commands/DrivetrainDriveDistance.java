package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous (by certain amount of inches).
 */
public class DrivetrainDriveDistance extends Command {
    // The goal for the PID
    private double distance;

    // Optional speed (if set to anything but zero)
    private double speed = 0;

    /**
     * Drive the robot to a distance (in inches).
     *
     * @param distance Distance to be driven (in inches).
     */
    DrivetrainDriveDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Drive the robot to a distance (in inches) at a certain speed.
     *
     * @param distance Distance to be driven (in inches).
     * @param speed    The speed at which to drive the distance.
     */
    DrivetrainDriveDistance(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
    }

    /**
     * Resets encoder and gyro, set goals for PID, enables PID.
     */
    protected void initialize() {
        // Reset the sensors
        Robot.myDrivetrain.myEncoder.reset();
        Robot.myDrivetrain.myAHRS.reset();

        // Set point, reset and enable encoder PID
        Robot.myDrivetrain.encoderPID.setSetpoint(distance);
        Robot.myDrivetrain.encoderPID.reset();
        Robot.myDrivetrain.encoderPID.enable();

        // Set point, enable gyro PID
        // The Setpoint is 0, because we want the robot to keep driving straight
        Robot.myDrivetrain.gyroPID.setSetpoint(0);
        Robot.myDrivetrain.gyroPID.reset();
        Robot.myDrivetrain.gyroPID.enable();
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double ySpeed = Robot.myDrivetrain.encoderPID.get();
        double xSpeed = Robot.myDrivetrain.gyroPID.get();

        // The weird divisions are there because both of the PIDs' output range is set as the default autonomous driving and turning speed
        // The division resets them to the normal scale (0-1), multiplying by speed then adjusts correctly to the new speed
        if (speed != 0) {
            ySpeed = (ySpeed / RobotMap.autonomousDefaultDrivingSpeed) * speed;
            xSpeed = (xSpeed / RobotMap.autonomousDefaultTurningSpeed) * speed;
        }

        Robot.myDrivetrain.arcadeDrive(ySpeed, xSpeed);
    }

    /**
     * Finished when it hits the encoderPID target.
     */
    protected boolean isFinished() {
        return Robot.myDrivetrain.encoderPID.onTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
