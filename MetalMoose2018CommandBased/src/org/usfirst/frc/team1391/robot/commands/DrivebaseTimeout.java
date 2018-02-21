package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * A simple class to time-out the drivebase.
 */
public class DrivebaseTimeout extends Command {

    // The time for the robot timeout
    private double time;

    // Constructor with time - for autonomous
    DrivebaseTimeout(double time) {
        requires(Robot.myCollector);

        this.time = time;
    }

    protected void initialize() {
        setTimeout(time);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
