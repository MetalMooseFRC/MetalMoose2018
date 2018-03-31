package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually controls the hanger arm.
 */
public class HangerArmManualControl extends Command {

    public HangerArmManualControl() {
        requires(Robot.myHangerArm);
    }

    protected void initialize() {
    }

    /**
     * If we are moving the hanger with either of the buttons, move the hanger.
     * If it was just moved up, hold it there
     */
    protected void execute() {
        if (OI.operatorStart.get()) {
            Robot.myHangerArm.setSpeed(-RobotMap.hangerMoveSpeed * RobotMap.hangerArmOrientation);
            RobotMap.holdHangerArm = true;
        } else if (RobotMap.holdHangerArm)
            Robot.myHangerArm.setSpeed(-RobotMap.hangerHoldSpeed * RobotMap.hangerArmOrientation);
        else Robot.myHangerArm.setSpeed(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
