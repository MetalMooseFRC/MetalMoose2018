package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;

public class HangerManualControl extends Command {

	public HangerManualControl() {
        requires(Robot.myHanger);
    }
    
    protected void initialize() {}

    protected void execute() {
	    // Controlled using the POV - if up (0), go up. If down (180), go down
    	if (OI.operatorController.getPOV() == 0) Robot.myHanger.setSpeed(1);
    	else if (OI.operatorController.getPOV() == 180) Robot.myHanger.setSpeed(-1);
    	else Robot.myHanger.setSpeed(0);
	}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
