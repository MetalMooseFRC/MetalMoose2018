package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous.
 */
public class DrivetrainTurnToAngle extends Command {
    // The goals for the PID.
    private double angle;

    // The angle for the robot to turn to (0 being looking forward, going left is - and going right is +)
    private double angleToTurnTo;

    // Counts repetitions of the same value (to see if we are not stuck).
    private int repeatCounter = 0;
    private double previousReading = 0;

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
     * If the doGotoCalculation is true, calculate the distance from the absolute x and y coordinates.
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

        if (previousReading == Robot.myDrivetrain.myAHRS.getAngle()) repeatCounter++;
        else repeatCounter = 0;

        previousReading = Robot.myDrivetrain.myAHRS.getAngle();

        // If we are stuck in the position
        if (repeatCounter == RobotMap.repeatCounterGoal) {
            // If we just turned, update the absolute angle of the robot
            RobotMap.absoluteAngle += Robot.myDrivetrain.myAHRS.getAngle();

            return true;
        } else return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
