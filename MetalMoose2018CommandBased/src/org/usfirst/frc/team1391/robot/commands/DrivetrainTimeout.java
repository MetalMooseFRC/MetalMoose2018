package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;

/**
 * A command to time-out the drivebase.
 */
public class DrivetrainTimeout extends Command {

    // The time for the robot timeout
    private double time;

    // Constructor with time - for autonomous
    DrivetrainTimeout(double time) {
        requires(Robot.myCollector);

        this.time = time;
    }

    protected void initialize() {
        setTimeout(time);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {}

    protected void interrupted() {

    }
}