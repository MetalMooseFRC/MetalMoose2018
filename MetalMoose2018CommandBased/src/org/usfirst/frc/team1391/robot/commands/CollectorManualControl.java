package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class CollectorManualControl extends Command {

	public CollectorManualControl() {
		requires(Robot.myCollector);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	protected void execute() {
		if (OI.operatorController.getRawAxis(OI.operatorLeftTrigger) != 0)
			Robot.myCollector.setSpeed(OI.operatorController.getRawAxis(OI.operatorLeftTrigger));
		else if (OI.operatorController.getRawAxis(OI.operatorRightTrigger) != 0)
			Robot.myCollector.setSpeed(-OI.operatorController.getRawAxis(OI.operatorRightTrigger));
		else
			Robot.myCollector.setSpeed(-RobotMap.collectorHoldSpeed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
