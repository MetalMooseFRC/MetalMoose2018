package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (by an angle).
 */
public class DrivetrainTurnByAngle extends Command {
    // The goals for the PID.
    private double angle;

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
        // If we are under gyroStopAtError
        if (Robot.myDrivetrain.gyroPID.onTarget()) {
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
