package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (by an angle).
 */
public class DrivetrainTurnByAngle extends Command {
    // The goal for the PID.
    private double angle;

    // Optional speed (if set to anything but zero)
    private double speed = 0;

    /**
     * Turn the robot by angle (in degrees).
     *
     * @param angle Angle to be turned by (in degrees).
     */
    DrivetrainTurnByAngle(double angle) {
        requires(Robot.myDrivetrain);
        this.angle = angle;
    }

    /**
     * Turn the robot by angle (in degrees) at a certain speed.
     *
     * @param angle Angle to be turned by (in degrees).
     * @param speed The speed at which to turn.
     */
    DrivetrainTurnByAngle(double angle, double speed) {
        requires(Robot.myDrivetrain);
        this.angle = angle;
        this.speed = speed;
    }

    /**
     * Resets encoder and gyro, set goals for PID, enables PID.
     */
    protected void initialize() {
        Robot.myDrivetrain.myAHRS.reset();

        // Set point, enable gyro PID
        Robot.myDrivetrain.gyroPID.setSetpoint(angle);
        Robot.myDrivetrain.gyroPID.reset();
        Robot.myDrivetrain.gyroPID.enable();

        // If we are turning for more than 4 seconds, go to the next command
        setTimeout(4);
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double xSpeed = Robot.myDrivetrain.gyroPID.get();

        if (speed != 0) xSpeed = (xSpeed / RobotMap.autonomousDefaultTurningSpeed) * speed;

        Robot.myDrivetrain.arcadeDrive(0, xSpeed);
    }

    /**
     * Finished when it hits the gyroPID target
     */
    protected boolean isFinished() {
        return Robot.myDrivetrain.gyroPID.onTarget() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
