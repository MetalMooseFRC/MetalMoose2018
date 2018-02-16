package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
	double distance, angle;
	
	public DriveAutonomous(double distance, double angle) {
		this.distance = RobotMap.encoderCoefficient*distance;
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.myDriveTrain.myEncoder.reset();
		Robot.myDriveTrain.myAHRS.reset();

		Robot.myDriveTrain.gyroController.setSetpoint(distance);
		Robot.myDriveTrain.encoderController.enable();
		
		Robot.myDriveTrain.gyroController.setSetpoint(angle);
		Robot.myDriveTrain.gyroController.enable();
	}
	
	protected void execute() {
		Robot.myDriveTrain.arcadeDrive(Robot.myDriveTrain.encoderOutput.getOutput()/1.5, Robot.myDriveTrain.gyroOutput.getOutput()/1.5);
		System.out.println(Robot.myDriveTrain.gyroOutput.getOutput() + " " + Robot.myDriveTrain.myAHRS.getAngle());
	}

	protected boolean isFinished() {
		if (Robot.myDriveTrain.gyroController.onTarget() && Robot.myDriveTrain.gyroController.onTarget()) return true;	
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
