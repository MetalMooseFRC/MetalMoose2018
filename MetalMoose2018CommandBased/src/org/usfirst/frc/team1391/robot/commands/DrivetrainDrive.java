package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous (by certain amount of inches).
 */
public class DrivetrainDrive extends Command {
    // The goals for the PID.
    private double distance;

    /**
     * Drive the robot to distance (in inches).
     *
     * @param distance Distance to be driven (in inches).
     */
    DrivetrainDrive(double distance) {
        this.distance = distance;
    }

    /**
     * Resets encoder, set goals for PID, enables PID.
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
