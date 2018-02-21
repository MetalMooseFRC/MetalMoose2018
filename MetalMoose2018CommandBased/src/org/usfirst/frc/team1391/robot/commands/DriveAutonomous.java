package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
    private double distance, angle;

    // Counts repetitions of the goal value.
    private int onTargetCounter = 0;
    private int onTargetCounterGoal = 15;

    // Counts repetitions of the same value.
    private int repeatCounter = 0;
    private int repeatCounterGoal = 5;
    private double previousReading = 0;

    // Drive the robot to distance (in inches), or angle (in degrees).
    // Either of the values has to equal zero for the move to be executed properly.
    DriveAutonomous(double distance, double angle) {
        this.distance = distance;
        this.angle = angle;
    }

    /**
     * Reset encoder, set goals for PID, enables PID
     */
    protected void initialize() {
        // Reset the sensors
        Robot.myDriveTrain.myEncoder.reset();
        Robot.myDriveTrain.myAHRS.reset();

        // Set point, reset and enable encoder PID
        Robot.myDriveTrain.encoderPID.setSetpoint(distance);
        Robot.myDriveTrain.encoderPID.reset();
        Robot.myDriveTrain.encoderPID.enable();

        // Set point, enable gyro PID
        Robot.myDriveTrain.gyroPID.setSetpoint(angle);
        Robot.myDriveTrain.gyroPID.reset();
        Robot.myDriveTrain.gyroPID.enable();
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID
     */
    protected void execute() {
        double pidEncoderOutput = Robot.myDriveTrain.encoderOutput.getOutput();
        double pidGyroOutput = Robot.myDriveTrain.gyroOutput.getOutput();

        // If we are turning, disregard the encoder output
        if (distance == 0) pidEncoderOutput = 0;

        // For tuning PID and debugging
        System.out.println("Encoder error " + Robot.myDriveTrain.encoderPID.getError());
        System.out.println("Gyro error " + Robot.myDriveTrain.gyroPID.getError());

        Robot.myDriveTrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
    }

    protected boolean isFinished() {
        // Evaluating the angle
        if (distance == 0) {
            // If we are on target, add one
            if (Robot.myDriveTrain.gyroPID.onTarget()) onTargetCounter++;
            else onTargetCounter = 0;

            // If we are reading the same PID value as before,
            if (previousReading == Robot.myDriveTrain.myAHRS.getAngle()) repeatCounter++;
            else repeatCounter = 0;

            // Reading the angle as previous reading (distance is 0)
            previousReading = Robot.myDriveTrain.myAHRS.getAngle();
        }

        // Evaluating the distance
        else {
            if (Robot.myDriveTrain.encoderPID.onTarget()) onTargetCounter++;
            else onTargetCounter = 0;

            if (previousReading == Robot.myDriveTrain.myEncoder.getDistance()) repeatCounter++;
            else repeatCounter = 0;

            // Reading the distance as previous reading (angle is 0)
            previousReading = Robot.myDriveTrain.myEncoder.getDistance();
        }

        if (onTargetCounter == onTargetCounterGoal || repeatCounter == repeatCounterGoal) {

            // If we just turned, adjust the absolute angle of the robot
            if (distance == 0) RobotMap.absoluteAngle += angle;

                // If we drove, adjust the X and Y coordinates accordingly
            else {
                RobotMap.robotPositionX += Math.sin(Math.toRadians(RobotMap.absoluteAngle)) * distance;
                RobotMap.robotPositionY += Math.cos(Math.toRadians(RobotMap.absoluteAngle)) * distance;
            }
            return true;
        } else return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
