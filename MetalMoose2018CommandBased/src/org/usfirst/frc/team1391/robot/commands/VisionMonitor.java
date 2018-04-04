package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;

public class VisionMonitor extends Command {

    public VisionMonitor() {
        requires(Robot.myVisionSystemClient);
    }

    protected void initialize() {
        //Robot.myVisionSystemClient.initVision();
    }

    protected void execute() {
    	Robot.myVisionSystemClient.updateVision();
    	System.out.println(Robot.myVisionSystemClient.getVisionAngle());
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
