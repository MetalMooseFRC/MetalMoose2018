package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class FourbarManualControl extends Command {
    /**
     * Teleop constructor.
     */
	public FourbarManualControl() {
        requires(Robot.myFourbar);
    }
    
    protected void initialize() {}

    /**
     *
     */
    protected void execute() {
        // The '-' sign is there because the controller axes are reversed (forward is -1)
        double yAxisSpeed = -OI.operatorController.getRawAxis(RobotMap.operatorRightYPort);

        // Either set the fourbar to the axis value (if it is above the hold threshold, or just hold
        if (Math.abs(yAxisSpeed) > RobotMap.fourbarHoldSpeed) {
            // If it is smaller than zero, it just went down, so we don't want to hold it anymore
            if (yAxisSpeed < 0) RobotMap.holdFourbar = false;

            // If we just went up with the fourbar, we want to start holding it
            else if (yAxisSpeed > 0) RobotMap.holdFourbar = true;

            Robot.myFourbar.setSpeed(yAxisSpeed);
        }

        // If no inputs from the axis, just hold (if we want to)
        else if (RobotMap.holdFourbar) Robot.myFourbar.setSpeed(RobotMap.fourbarHoldSpeed);
        else Robot.myFourbar.setSpeed(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
