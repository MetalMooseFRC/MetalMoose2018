package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class CollectorIntake extends Command {

	// The time for the robot timeout
	private double time = -1;
	
	// Default constructor - for teleop
	public CollectorIntake() {
		requires(Robot.myCollector);
	}
	
	// Constructor with time - for autonomous
	CollectorIntake(double time) {
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
		return time > 0 && isTimedOut();
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
