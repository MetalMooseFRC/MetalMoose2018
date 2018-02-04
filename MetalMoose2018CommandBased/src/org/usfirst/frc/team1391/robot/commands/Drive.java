package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * Drives the robot (using either arcade or tank).
 */
public class Drive extends Command {

    public Drive() {
    	requires(Robot.kDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//The reading is from the "primary" joystick of the connected controller
    	//The '-' is because pulling the joystick forward is -1 and we want it to be +1 (and vice versa)
    	//The X axis is fine, since the rotation is clockwise and rightmost value of the x axis is +1
		Robot.kDriveTrain.arcadeDrive(-OI.stick.getY(), OI.stick.getX());
    	
    	//1 is the Y axis of the left joystick, 5 is the Y axis of the right joystick
		//The '-' is for the same reason as the '-' on the arcade drive
    	//Robot.kDriveTrain.tankDrive(-OI.stick.getRawAxis(1), -OI.stick.getRawAxis(5));
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
