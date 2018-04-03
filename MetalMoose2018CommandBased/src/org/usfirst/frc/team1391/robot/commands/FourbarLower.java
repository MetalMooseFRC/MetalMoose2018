package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Lowers the fourbar.
 */
public class FourbarLower extends Command {

    public FourbarLower() {
        requires(Robot.myFourbar);
    }

    /**
     * Sets timeout and stops holding the fourbar.
     */
    protected void initialize() {
        setTimeout(RobotMap.fourbarLowerLength);
        RobotMap.holdFourbar = false;
    }

    /**
     * Keeps lowering the fourbar.
     */
    protected void execute() {
        Robot.myFourbar.setSpeed(RobotMap.fourbarLowerSpeed);
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
