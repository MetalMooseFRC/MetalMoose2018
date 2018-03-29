package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the clamp.
 */
public class ClampOut extends Command {

    public ClampOut() {
        requires(Robot.myClamp);
    }

    protected void initialize() {
    	setTimeout(RobotMap.clampOutLength);
    }

    /**
     * Repeatedly set the clamp motor to the clamp out speed
     */    
    protected void execute() {
    	Robot.myClamp.setSpeed(RobotMap.clampOutSpeed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
