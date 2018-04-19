package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (by an angle).
 */
public class VisionMonitor extends Command {
    // A counter to update the vision every updatePeriod seconds
    private int counter = 0;
    private double updatePeriod = 0.1;

    public VisionMonitor() {
        requires(Robot.myVisionSystemClient);
    }

    protected void initialize() {
    }

    /**
     * Updates vision once every updatePeriod number of seconds
     */
    protected void execute() {
        if (timeSinceInitialized() > counter * updatePeriod) {
            Robot.myVisionSystemClient.updateVision();

            counter++;
        }

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
