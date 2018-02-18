package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.Robot;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
	double distance, angle;
	
	int onTargetCounter = 0;
	int onTargetCounterGoal = 3;
	
	public DriveAutonomous(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.myDriveTrain.myEncoder.reset();
		Robot.myDriveTrain.myAHRS.reset();

		Robot.myDriveTrain.encoderController.setSetpoint(distance);
		Robot.myDriveTrain.encoderController.enable();
		
		Robot.myDriveTrain.gyroController.setSetpoint(angle);
		Robot.myDriveTrain.gyroController.enable();
	}
	
	protected void execute() {
		System.out.println(Robot.myDriveTrain.encoderOutput.getOutput() + " " + Robot.myDriveTrain.myEncoder.getDistance());
		double pidEncoderOutput = Robot.myDriveTrain.encoderOutput.getOutput();
		double pidGyroOutput = Robot.myDriveTrain.gyroOutput.getOutput();
		
		if (distance == 0) pidEncoderOutput = 0;
		
		Robot.myDriveTrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
	}

	protected boolean isFinished() {
		if (Robot.myDriveTrain.gyroController.onTarget() && (Robot.myDriveTrain.encoderController.onTarget() || distance == 0)) onTargetCounter++;
		else onTargetCounter = 0;
		
		if (onTargetCounter == onTargetCounterGoal) return true;
		else {
			RobotMap.angleError = Robot.myDriveTrain.gyroController.getError();
			if (distance != 0) RobotMap.distanceError = Robot.myDriveTrain.encoderController.getError();
			
			return false;
		}
	}

	protected void end() {

	}

	protected void interrupted() {
		
	}
}
