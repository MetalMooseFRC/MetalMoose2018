package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.BlankPIDOutput;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
	
	PIDController encoderController;
	PIDController gyroController;

	BlankPIDOutput encoderOutput;
	BlankPIDOutput gyroOutput;
	
	double distance, angle;
	
	public DriveAutonomous(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.myEncoder.reset();
		Robot.myAHRS.reset();
		
		encoderOutput = new BlankPIDOutput();
		gyroOutput = new BlankPIDOutput();
		
		encoderController = new PIDController(0, 0, 0, 0, Robot.myEncoder, encoderOutput);
		encoderController.setOutputRange(-1.0, 1.0);
		encoderController.setAbsoluteTolerance(0.0);
		encoderController.setContinuous(true);
		encoderController.setSetpoint(distance);
		encoderController.enable();
		
		gyroController = new PIDController(0, 0, 0, 0, Robot.myAHRS, gyroOutput);
		gyroController.setInputRange(-180.0, +180.0);
		gyroController.setOutputRange(-1.0, 1.0);
		gyroController.setAbsoluteTolerance(0.0);
		gyroController.setContinuous(true);
		gyroController.setSetpoint(angle);
		gyroController.enable();
	}
	
	protected void execute() {
		Robot.myDriveTrain.arcadeDrive(gyroOutput.output, encoderOutput.output);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
