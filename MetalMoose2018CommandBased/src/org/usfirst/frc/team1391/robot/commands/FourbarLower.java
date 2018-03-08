package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class FourbarLower extends Command {
    public FourbarLower() {
        requires(Robot.myFourbar);
    }

    /**
     * Stop holding the fourbar, set the FourbarLower timeout
     */
    protected void initialize() {
        RobotMap.holdFourbar = false;
        setTimeout(RobotMap.fourbarLowerLength);
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
