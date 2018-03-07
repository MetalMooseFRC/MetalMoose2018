package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class FourbarHold extends Command {
    public FourbarHold() {
        requires(Robot.myFourbar);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        Robot.myFourbar.setSpeed(RobotMap.fourbarHoldSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
