package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class CollectorIntake extends Command {

	// The time for the robot timeout
	double time = -1;
	
	// Default constructor - for teleop
	public CollectorIntake() {
		requires(Robot.myCollector);
	}
	
	// Constructor with time - for autonomous
	public CollectorIntake(double time) {
		requires(Robot.myCollector);

		this.time = time;
	}

	protected void initialize() {
		// Set a timeout only if the time was initialized to something
		if (time > 0) setTimeout(time);
	}

	protected void execute() {
		Robot.myCollector.setSpeed(RobotMap.collectorIntakeSpeed);
	}

	protected boolean isFinished() {
		if (time > 0) return isTimedOut();
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
