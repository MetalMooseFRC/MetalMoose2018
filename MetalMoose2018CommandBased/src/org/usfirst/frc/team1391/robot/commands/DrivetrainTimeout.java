package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * A command to time-out the drivebase.
 */
public class DrivetrainTimeout extends Command {
    // The length for the robot timeout
    private double time;

    /**
     * Constructor for autonomous.
     *
     * @param time How long should the drivebase timeout for?
     */
    DrivetrainTimeout(double time) {
        requires(Robot.myDrivetrain);

        this.time = time;
    }

    /**
     * Sets timeout.
     */
    protected void initialize() {
        setTimeout(time);
    }

    protected void execute() {
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
