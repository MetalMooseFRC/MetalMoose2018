package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Raises the elevator.
 */
public class FourbarRaise extends Command {
    // Length for the fourbar to do its movement.
    double timeoutLength;

    public FourbarRaise(double timeoutLength) {
        requires(Robot.myFourbar);

        this.timeoutLength = timeoutLength;
    }

    /**
     * Start holding the fourbar (after the command is done), set the timeout
     */
    protected void initialize() {
        RobotMap.holdFourbar = true;
        setTimeout(timeoutLength);
    }

    protected void execute() {
        Robot.myFourbar.setSpeed(RobotMap.fourbarRaiseSpeed);
    }

    /**
     * Returns when timed out
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {}

    protected void interrupted() {}
}
