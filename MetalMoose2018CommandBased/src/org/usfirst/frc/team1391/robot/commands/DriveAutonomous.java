package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.Robot;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
	public int distanceToDrive;

	public DriveAutonomous(int distanceToDrive) {
		requires(Robot.myDriveTrain);
		this.distanceToDrive = distanceToDrive;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.myEncoder.reset();
	}

	// Drives until stopped by isFinished
	protected void execute() {
		Robot.myDriveTrain.tankDrive(0.0, 0.0);
	}

	// Returns true when myEncoder's measured distance exceeds the distance that the
	// robot has to drive
	protected boolean isFinished() {
		if (distanceToDrive < Robot.myEncoder.getDistance())
			return true;
		else
			return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
