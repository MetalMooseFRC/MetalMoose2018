package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class FourbarRaise extends Command {
    // Length for the fourbar to do its movement.
    double timeoutLength = 0;

    public FourbarRaise(double timeoutLength) {
        requires(Robot.myFourbar);

        this.timeoutLength = timeoutLength;
    }

    /**
     * Start holding the fourbar, set the timeout
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
