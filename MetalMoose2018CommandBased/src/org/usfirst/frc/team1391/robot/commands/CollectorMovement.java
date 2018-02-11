package org.usfirst.frc.team1391.robot.commands;

import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Makes collector/fidget spinner intake power cubes by running motors inwards
 */

public class CollectorMovement extends Command {

	public CollectorMovement() {
		requires(Robot.myCollector);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	protected void execute() {
		if (OI.operatorStick.getRawButtonPressed(RobotMap.intakeButtonPort))
			Robot.myCollector.intake();
		else if (OI.operatorStick.getRawButtonPressed(RobotMap.intakeButtonPort))
			Robot.myCollector.outtake();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
