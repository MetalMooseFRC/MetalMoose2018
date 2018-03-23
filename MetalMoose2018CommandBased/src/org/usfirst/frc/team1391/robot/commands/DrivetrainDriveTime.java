package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous (for a certain period of time).
 */
public class DrivetrainDriveTime extends Command {
    // The goal for the timeout
    private double time;

    // Optional speed (if set to anything but zero)
    private double speed = 0;

    /**
     * Drive the robot for a certain amount of time (in seconds).
     *
     * @param time Time for the robot to drive.
     */
    DrivetrainDriveTime(double time) {
        this.time = time;
    }
    
    /**
     * Drive the robot for a certain amount of time (in seconds) at a certain speed.
     *
     * @param time Time for the robot to drive.
     * @param speed Speed at which to drive for the certain amount of time.
     */
    DrivetrainDriveTime(double time, double speed) {
        this.time = time;
        this.speed = speed;
    }

    /**
     * Resets gyro, set goals for PID, enables PID.
     */
    protected void initialize() {
        // Reset the sensors
        Robot.myDrivetrain.myAHRS.reset();

        // The Setpoint is 0, because we want the robot to keep driving straight
        Robot.myDrivetrain.gyroPID.setSetpoint(0);
        Robot.myDrivetrain.gyroPID.reset();
        Robot.myDrivetrain.gyroPID.enable();

        setTimeout(time);
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double xSpeed = Robot.myDrivetrain.gyroPID.get();
        double ySpeed = RobotMap.autonomousDefaultDrivingSpeed;

        // The weird divisions are there because both of the PIDs' output range is set as the default autonomous driving and turning speed
        // The division resets them to the normal scale (0-1), multiplying by speed then adjusts correctly to the new speed
        if (speed != 0 ) {
        	xSpeed = (xSpeed / RobotMap.autonomousDefaultTurningSpeed) * speed;
        	ySpeed = speed;
        }
        
        Robot.myDrivetrain.arcadeDrive(ySpeed, xSpeed);        
    }

    /**
     * Finished when isTimedOut() is true.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {}

    protected void interrupted() {}
}
