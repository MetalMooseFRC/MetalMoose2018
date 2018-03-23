package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * A command to time-out the drivebase.
 */
public class HangerArmManualControl extends Command {

    // Constructor with time - for autonomous
    public HangerArmManualControl() {
        requires(Robot.myHangerArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (OI.operatorStart.get()) {
    		Robot.myHangerArm.setSpeed(0.5 * RobotMap.armOrientation);
    		RobotMap.holdHangerArm = true;
    	}
    	else if (OI.operatorBack.get()) {
    		Robot.myHangerArm.setSpeed(-0.5 * RobotMap.armOrientation);
    		RobotMap.holdHangerArm = false;
    	}
    	else if (RobotMap.holdHangerArm) Robot.myHangerArm.setSpeed(0.2 * RobotMap.armOrientation);
    	else Robot.myHangerArm.setSpeed(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {

    }
}
