package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
	double distance, angle;

	int onTargetCounter = 0;
	int onTargetCounterGoal = 10;

	// Drive the robot to distance (in inches), or angle (in degrees)
	// Take note that for this to work properly, only one of the two inputs can be
	// non-zero (due to the poition of the encoder on the robot).
	public DriveAutonomous(double distance, double angle) {
		this.distance = distance;
		if (distance == 0) {
			// This adds the angle error from the previous DriveAutonomous command
			this.angle = angle + RobotMap.angleError;
			RobotMap.angleError = 0;
		}
	}

	// Reset encoders, set goals for PID, enable PID
	protected void initialize() {
		Robot.myDriveTrain.myEncoder.reset();
		Robot.myDriveTrain.myAHRS.reset();

		// If the distance is 0, we don't want to interfere with the gyro.
		if (distance != 0) {
			Robot.myDriveTrain.encoderController.setSetpoint(distance);
			Robot.myDriveTrain.encoderController.enable();
		}

		Robot.myDriveTrain.gyroController.setSetpoint(angle);
		Robot.myDriveTrain.gyroController.enable();
	}

	protected void execute() {
		double pidEncoderOutput = Robot.myDriveTrain.encoderOutput.getOutput();
		double pidGyroOutput = Robot.myDriveTrain.gyroOutput.getOutput();

		System.out.println(Robot.myDriveTrain.encoderController.getError() + " " + Robot.myDriveTrain.encoderController.isEnabled());

		Robot.myDriveTrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
	}

	protected boolean isFinished() {
		if (Robot.myDriveTrain.gyroController.onTarget() && (Robot.myDriveTrain.encoderController.onTarget() || distance == 0)) onTargetCounter++;
		else onTargetCounter = 0;

		if (onTargetCounter == onTargetCounterGoal) {
			RobotMap.angleError += Robot.myDriveTrain.gyroController.getError();

			Robot.myDriveTrain.encoderController.disable();
			Robot.myDriveTrain.gyroController.disable();

			return true;
		} else
			return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
