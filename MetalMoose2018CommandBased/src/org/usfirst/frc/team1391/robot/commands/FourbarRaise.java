package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Raises the fourbar.
 */
public class FourbarRaise extends Command {

    public FourbarRaise() {
        requires(Robot.myFourbar);
    }

    /**
     * Sets timeout and starts holding the fourbar.
     */
    protected void initialize() {
		setTimeout(RobotMap.fourbarRaiseLength);
		RobotMap.holdFourbar = true;
    }

    /**
     * Keeps raising the fourbar.
     */
    protected void execute() {
    	Robot.myFourbar.setSpeed(RobotMap.fourbarRaiseSpeed);
    }

    /**
     * Returns true when the command times out.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
