package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous.
 */
public class DrivetrainDriveToCoordinates extends Command {
    // The goals for the PID.
    private double distance;

    // The distance is calculated at the beginning of the initialization command.
    private double x, y;
    private double stopFromGoal = 0;

    // Counts repetitions of the same value (to see if we are not stuck).
    private int repeatCounter = 0;
    private double previousReading = 0;

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
     * Reset encoder, set goals for PID, enables PID.
     *
     * If the doGotoCalculation is true, calculate the distance from the absolute x and y coordinates.
     */
    protected void initialize() {
        double relativeX = x - RobotMap.robotPositionX;
        double relativeY = y - RobotMap.robotPositionY;

        distance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);

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
        if (previousReading == Robot.myDrivetrain.myEncoder.getDistance()) repeatCounter++;
        else repeatCounter = 0;

        previousReading = Robot.myDrivetrain.myEncoder.getDistance();

        // If we are either on-target or stuck in the position
        if (repeatCounter == RobotMap.repeatCounterGoal) {
            // If we drove, adjust the robot X and Y coordinates accordingly
            RobotMap.robotPositionX += Math.sin(Math.toRadians(RobotMap.absoluteAngle)) * Robot.myDrivetrain.myEncoder.getDistance();
            RobotMap.robotPositionY += Math.cos(Math.toRadians(RobotMap.absoluteAngle)) * Robot.myDrivetrain.myEncoder.getDistance();

            return true;
        } else return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
