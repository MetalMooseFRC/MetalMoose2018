package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the collector manually using axes on the operator controller.
 */
public class CollectorManualControl extends Command {

    public CollectorManualControl() {
        requires(Robot.myCollector);
    }

    protected void initialize() {

    }

    protected void execute() {
        double leftTriggerSpeed = OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort);
        double rightTriggerSpeed = OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort);

        // Outtakes the cube (the 0.1 is just an arbitrary threshold)
        if (leftTriggerSpeed > 0.1) {
        	RobotMap.holdCollector = false;
            Robot.myCollector.setAbsoluteSpeed(leftTriggerSpeed);
        }

        // Intakes the cube (the 0.1 is just an arbitrary threshold)
        else if (rightTriggerSpeed > 0.1) {
        	RobotMap.holdCollector = true;
        	Robot.myCollector.setAbsoluteSpeed(-rightTriggerSpeed);
        }

        // If we want to hold the cube
        else if (RobotMap.holdCollector) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorHoldSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
