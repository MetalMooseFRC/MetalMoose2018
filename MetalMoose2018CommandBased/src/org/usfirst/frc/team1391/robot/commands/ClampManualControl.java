package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the clamp.
 */
public class ClampManualControl extends Command {

    public ClampManualControl() {
        requires(Robot.myClamp);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (RobotMap.intakeWithCollector && !RobotMap.clamped) {
    		RobotMap.clamped = true;
    		new ClampIn().start();
    	}
    	else if (!RobotMap.intakeWithCollector && RobotMap.clamped) {
    		RobotMap.clamped = false;
    		new ClampOut().start();
    	}
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}