package org.usfirst.frc.team1391.robot.commands;

import org.usfirst.frc.team1391.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Makes collector/fidget spinner intake power cubes by running motors inwards
 */

public class Collect extends Command {
	
	public Collect() {
		requires(Robot.kFidgetSpinner);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    protected void execute() {
    	
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
