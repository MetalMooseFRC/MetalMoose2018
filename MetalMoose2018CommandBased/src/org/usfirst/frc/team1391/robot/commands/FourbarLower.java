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
    
    protected void initialize() {
        setTimeout(RobotMap.fourbarLowerLength);
    }

    protected void execute() {
        Robot.myFourbar.setSpeed(RobotMap.fourbarLowerSpeed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
