package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Clamps in using the collector motor.
 */
public class ClampIn extends Command {

    public ClampIn() {
        requires(Robot.myClamp);
    }

    /**
     * Sets timeout for clamp in length.
     */
    protected void initialize() {
        setTimeout(RobotMap.clampInLength);
    }

    /**
     * Repeatedly set the clamp motor to the clamp in speed.
     */
    protected void execute() {
        Robot.myClamp.setSpeed(RobotMap.clampInSpeed);
    }

    /**
     * Returns true when the command times out.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    /**
     * Sets clamped to true (since the robot just clamped).
     */
    protected void end() {
    	RobotMap.clamped = true;
    }

    /**
     * Sets clamped to true (the robot tried to clamp).
     */
    protected void interrupted() {
    	RobotMap.clamped = true;
    }
}
