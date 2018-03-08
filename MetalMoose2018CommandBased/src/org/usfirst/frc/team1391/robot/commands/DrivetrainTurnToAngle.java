package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (to an angle).
 */
public class DrivetrainTurnToAngle extends Command {
    // The goals for the PID.
    private double angle;

    // The angle for the robot to turn to (0 being looking forward (from the driver station), going left is - and going right is +)
    private double angleToTurnTo;

    /**
     * Turn the robot to a certain angle.
     *
     * @param angleToTurnTo Angle to turn to (in degrees).
     */
    DrivetrainTurnToAngle(double angleToTurnTo) {
        this.angleToTurnTo = angleToTurnTo;
    }

    /**
     * Reset encoder, set goals for PID, enables PID.
     *
     * Calculates the angle from the robot % 360. If is not optimal (>180 or <-180), find optimal (that is smaller than 180)
     */
    protected void initialize() {
        // We have to calculate the angle from the position of the robot (and the robot can be in any angle)
        double absoluteAngle = (angleToTurnTo - RobotMap.absoluteAngle) % 360;

        // To create the optimal turning, we have to optimize any angle above + or - 180 degrees
        if (absoluteAngle > 180) absoluteAngle = -180 + (absoluteAngle % 180);
        else if (absoluteAngle < -180) absoluteAngle = 180 - (absoluteAngle % 180);

        angle = absoluteAngle;

        // Reset the sensors
        Robot.myDrivetrain.myAHRS.reset();

        // Set point, enable gyro PID
        Robot.myDrivetrain.gyroPID.setSetpoint(angle);
        Robot.myDrivetrain.gyroPID.reset();
        Robot.myDrivetrain.gyroPID.enable();
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double pidGyroOutput = Robot.myDrivetrain.gyroPID.get();

        Robot.myDrivetrain.arcadeDrive(0, pidGyroOutput);
    }

    protected boolean isFinished() {
    	if (Double.isNaN(angle)) return true;

        // If we are under gyroStopAtError
        if (Robot.myDrivetrain.gyroPID.onTarget()) {
            // Update the absolute angle of the robot
            RobotMap.absoluteAngle += Robot.myDrivetrain.myAHRS.getAngle();

            return true;
        } else return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
