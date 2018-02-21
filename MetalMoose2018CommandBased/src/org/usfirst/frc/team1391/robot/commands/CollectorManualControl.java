package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the collector using axes.
 */
public class CollectorManualControl extends Command {

	public CollectorManualControl() {
		requires(Robot.myCollector);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	protected void execute() {
		if (OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort) != 0)
			Robot.myCollector.setSpeed(OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort));
		else if (OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort) != 0)
			Robot.myCollector.setSpeed(-OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort));
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
