package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Outtakes (either manually using a button, or through autonomous).
 */
public class VisionMonitor extends Command {

    public VisionMonitor() {
        requires(Robot.myVisionSystem);
    }

    protected void initialize() {
        Robot.myVisionSystem.initVision();
    }

    /**
     * Sets speed, defined either by the collectorIntakeSpeed constant or by the speed variable.
     */
    protected void execute() {
    	Robot.myVisionSystem.updateVision();
    	System.out.println(Robot.myVisionSystem.getVisionAngle());
    }

    /**
     * Finishes when isTimedOut() is true.
     */
    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
