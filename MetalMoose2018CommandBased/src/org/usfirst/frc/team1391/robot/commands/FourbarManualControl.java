package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually control the fourbar.
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
     * Manually control the fourbar (move up/down/hold)
     *
     * If we just moved up, then hold. If we just moved down, stop holding
     */
    protected void execute() {
        // The '-' sign is there because the controller axes are reversed (forward is -1)
        double yAxisSpeed = -OI.operatorController.getRawAxis(RobotMap.operatorRightYPort);

        if (Robot.myElevator.elevatorEncoder.get() > RobotMap.minimumElevatorHoldDistance) Robot.myFourbar.setSpeed(RobotMap.fourbarHoldUpSpeed);
        else Robot.myFourbar.setSpeed(RobotMap.fourbarHoldDownSpeed);

        if (OI.operatorB.get()) {
            // Either set the fourbar to the axis value (if it is above the hold threshold, or just hold
            if (Math.abs(yAxisSpeed) > RobotMap.fourbarHoldUpSpeed) {
                // If it is smaller than zero, it just went down, so we don't want to hold it anymore
                if (yAxisSpeed < 0) RobotMap.holdFourbar = false;

                    // If we just went up with the fourbar, we want to start holding it
                else if (yAxisSpeed > 0) RobotMap.holdFourbar = true;

                Robot.myFourbar.setSpeed(yAxisSpeed);
            }

            // If no inputs from the axis, just hold (if we want to)
            else if (RobotMap.holdFourbar) Robot.myFourbar.setSpeed(RobotMap.fourbarHoldUpSpeed);
            else Robot.myFourbar.setSpeed(RobotMap.fourbarHoldDownSpeed);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
