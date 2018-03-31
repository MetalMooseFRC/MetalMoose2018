package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Lower the fourbar.
 */
public class FourbarLower extends Command {

    public FourbarLower() {
        requires(Robot.myFourbar);
    }

    /**
     * Initializes the command only when the elevator is down.
     */
    protected void initialize() {
		setTimeout(RobotMap.fourbarLowerLength);
		RobotMap.holdFourbar = false;
    	
    }

    /**
     * Keeps lowering the fourbar.
     */
    protected void execute() {
    	Robot.myFourbar.setSpeed(RobotMap.fourbarLowerSpeed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
