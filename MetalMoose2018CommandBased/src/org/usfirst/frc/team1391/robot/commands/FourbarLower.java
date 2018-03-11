package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Lower the fourbar and disable fourbar hold.
 */
public class FourbarLower extends Command {
    // Length for the fourbar to do its movement.
    double timeoutLength;

    public FourbarLower(double timeoutLength) {
        requires(Robot.myFourbar);

        this.timeoutLength = timeoutLength;
    }

    /**
     * Stop holding the fourbar (this will come into effect after the command ends), set the timeout
     */
    protected void initialize() {
        RobotMap.holdFourbar = false;
        setTimeout(timeoutLength);
    }

    protected void execute() {
        Robot.myFourbar.setSpeed(RobotMap.fourbarLowerSpeed);
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
