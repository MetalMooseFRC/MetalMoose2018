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
        if (OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort) != 0)
            Robot.myCollector.setAbsoluteSpeed(OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort));
        else if (OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort) != 0)
            Robot.myCollector.setAbsoluteSpeed(-OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort));
        else
            Robot.myCollector.setAbsoluteSpeed(-RobotMap.collectorHoldSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
