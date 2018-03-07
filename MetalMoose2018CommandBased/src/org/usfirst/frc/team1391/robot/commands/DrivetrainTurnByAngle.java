package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous.
 */
public class DrivetrainTurnByAngle extends Command {
    // The goals for the PID.
    private double angle;

    // Counts repetitions of the same value (to see if we are not stuck).
    private int repeatCounter = 0;
    private double previousReading = 0;

    /**
     * Turn the robot by angle (in degrees).
     *
     * @param angle Angle to be turned (in degrees).
     */
    DrivetrainTurnByAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Reset encoder, set goals for PID, enables PID.
     */
    protected void initialize() {
        // Reset the gyro
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
        if (previousReading == Robot.myDrivetrain.myAHRS.getAngle()) repeatCounter++;
        else repeatCounter = 0;

        previousReading = Robot.myDrivetrain.myAHRS.getAngle();

        // If we are stuck in the position
        if (repeatCounter == RobotMap.repeatCounterGoal) {
            // Update the absolute angle of the robot
            RobotMap.absoluteAngle += Robot.myDrivetrain.myAHRS.getAngle();

            return true;
        } else return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
