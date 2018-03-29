package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the clamp.
 */
public class ClampIn extends Command {

    public ClampIn() {
        requires(Robot.myClamp);
    }

    protected void initialize() {
    	setTimeout(RobotMap.clampInLength);
    }

    /**
     * Repeatedly set the clamp motor to the clamp in speed
     */
    protected void execute() {
    	Robot.myClamp.setSpeed(RobotMap.clampInSpeed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
