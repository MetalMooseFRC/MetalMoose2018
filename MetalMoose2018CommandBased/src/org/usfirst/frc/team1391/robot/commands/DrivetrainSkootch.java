package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * A command that shifts the robot ever so slightly to the left or the right.
 *
 * The command has three parts: go back with one side, go back with the other side, and drive backward (to hit the wall again).
 */
public class DrivetrainSkootch extends Command {
    // The direction of the skootch
    private int direction;

    // These variables are the timing for the individual parts of the skootch command.
    private double firstTurnStop = 0.15;
    private double secondTurnStop = 0.3;
    private double driveForwardStop = 0.7;

    // The speeds of the turning and driving backward
    private double turnSpeed = 0.6;
    private double driveForwardSpeed = -0.45;

    /**
     * Default constructor with skootch direction.
     *
     * @param direction Left is 0, right is 1.
     */
    public DrivetrainSkootch(int direction) {
        requires(Robot.myDrivetrain);
        this.direction = direction;
    }

    protected void initialize() {
        setTimeout(driveForwardStop);
    }

    /**
     * Turns the robot, depending on the phase that the command is in.
     */
    protected void execute() {
    	if (timeSinceInitialized() < firstTurnStop) {
    	    // if we are going left, move the
    		if (direction == 0) Robot.myDrivetrain.tankDrive(turnSpeed, 0);
    		else Robot.myDrivetrain.tankDrive(0, turnSpeed);
    	}
    	else if (timeSinceInitialized() < secondTurnStop) {
    		if (direction == 1) Robot.myDrivetrain.tankDrive(0, turnSpeed);
    		else Robot.myDrivetrain.tankDrive(turnSpeed, 0);
    	} else Robot.myDrivetrain.arcadeDrive(0, driveForwardSpeed);
    }

    /**
     * Return when the command is timed out.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {}

    protected void interrupted() {}
}
