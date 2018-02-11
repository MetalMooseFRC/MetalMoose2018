package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * Controls the elevator.
 */
public class ElevatorMovement extends Command {

    public ElevatorMovement() {
    	requires(Robot.myElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Repeatedly adjust the speed of the elevator train from the reading of the joystick axes
    protected void execute() {
    	Robot.myElevator.moveElevator(OI.operatorStick.getY());
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
