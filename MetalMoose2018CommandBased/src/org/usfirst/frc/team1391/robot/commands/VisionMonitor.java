package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;

public class VisionMonitor extends Command {

    public VisionMonitor() {
        requires(Robot.myVisionSystem);
    }

    protected void initialize() {
        Robot.myVisionSystem.initVision();
    }

    protected void execute() {
    	Robot.myVisionSystem.updateVision();
    	System.out.println(Robot.myVisionSystem.getVisionAngle());
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
