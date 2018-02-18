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
	int onTargetCounterGoal = 15;

	public DriveAutonomous(double distance, double angle) {
		// We can't do both the distance and the angle correction at the same time, due
		// to the position of the encoder.
		if (angle == 0) this.distance = distance + RobotMap.distanceError;
		else this.angle = angle + RobotMap.angleError;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.myDriveTrain.myEncoder.reset();
		Robot.myDriveTrain.myAHRS.reset();

		// If the distance is 0, we don't want to interfere with the gyro.
		Robot.myDriveTrain.encoderController.setSetpoint(distance);
		if (distance != 0) Robot.myDriveTrain.encoderController.enable();

		Robot.myDriveTrain.gyroController.setSetpoint(angle);
		Robot.myDriveTrain.gyroController.enable();
	}

	protected void execute() {
		double pidEncoderOutput = Robot.myDriveTrain.encoderOutput.getOutput();
		double pidGyroOutput = Robot.myDriveTrain.gyroOutput.getOutput();

		Robot.myDriveTrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
	}

	protected boolean isFinished() {
		if (Robot.myDriveTrain.gyroController.onTarget() && Robot.myDriveTrain.encoderController.onTarget()) onTargetCounter++;
		else onTargetCounter = 0;

		System.out.println(Robot.myDriveTrain.gyroController.getError());

		if (onTargetCounter == onTargetCounterGoal) {
			if (distance == 0) RobotMap.angleError += Robot.myDriveTrain.gyroController.getError();
			if (angle == 0) RobotMap.distanceError += Robot.myDriveTrain.encoderController.getError();
			
			return true;
		}
		else return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
