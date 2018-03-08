package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous (by a distance to a pair of coordinates).
 */
public class DrivetrainDriveToCoordinates extends Command {
    // The distance is calculated at the beginning of the initialization command.
    private double x, y;
    private double stopFromGoal = 0;

    /**
     * Saves the absolute values of where the robot should be and does the calculation when this command is initialized.
     *
     * @param x The goal x coordinate of the robot.
     * @param y The goal y coordinate of the robot.
     */
    DrivetrainDriveToCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sometimes, we want to stop a certain distance in front of the goal (when we go pick up a cube, for example)
     *
     * @param x The goal x coordinate of the robot.
     * @param y The goal y coordinate of the robot.
     */
    DrivetrainDriveToCoordinates(double x, double y, double stopFromGoal) {
        this.x = x;
        this.y = y;

        this.stopFromGoal = stopFromGoal;
    }

    /**
     * Resets encoder, set goals for PID, enables PID.
     *
     * Calculates the distance from robot position to x and y using the Pythagorean theorem.
     */
    protected void initialize() {
        // Calculate the actual distance form the robot to the desired coordinate pair
        double relativeX = x - RobotMap.robotPositionX;
        double relativeY = y - RobotMap.robotPositionY;

        // Pythagorean!
        double distance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);

        // Stop a distance before reaching the goal
        if (stopFromGoal != 0) distance -= stopFromGoal;

        // Reset the sensors
        Robot.myDrivetrain.myEncoder.reset();
        Robot.myDrivetrain.myAHRS.reset();

        // Set point, reset and enable encoder PID
        Robot.myDrivetrain.encoderPID.setSetpoint(distance);
        Robot.myDrivetrain.encoderPID.reset();
        Robot.myDrivetrain.encoderPID.enable();

        // Set point, enable gyro PID
        Robot.myDrivetrain.gyroPID.setSetpoint(0);
        Robot.myDrivetrain.gyroPID.reset();
        Robot.myDrivetrain.gyroPID.enable();
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double pidEncoderOutput = Robot.myDrivetrain.encoderPID.get();
        double pidGyroOutput = Robot.myDrivetrain.gyroPID.get();

        Robot.myDrivetrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
    }

    protected boolean isFinished() {
        // If we are under encoderStopAtError
        if (Robot.myDrivetrain.encoderPID.onTarget()) {
            // Adjust the robot X and Y coordinates accordingly
            RobotMap.robotPositionX += Math.sin(Math.toRadians(RobotMap.absoluteAngle)) * Robot.myDrivetrain.myEncoder.getDistance();
            RobotMap.robotPositionY += Math.cos(Math.toRadians(RobotMap.absoluteAngle)) * Robot.myDrivetrain.myEncoder.getDistance();

            return true;
        } else return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
