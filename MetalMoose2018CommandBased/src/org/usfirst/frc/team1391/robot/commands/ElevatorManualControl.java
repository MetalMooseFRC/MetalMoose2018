package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually controls the elevator (using throttling).
 */
public class ElevatorManualControl extends Command {

    public ElevatorManualControl() {
        requires(Robot.myElevator);
    }

    protected void initialize() {}

    /**
     * Repeatedly sets the speed of the elevator (through the setThrottle function).
     * If there is no input in the joystick, holds the elevator in place.
     */
    protected void execute() {
    	if (OI.operatorB.get()) {
    		// The '-' sign is because the values of the axes are reversed (forward is -1)
    		double leftJoystickInput = -OI.operatorController.getRawAxis(RobotMap.operatorLeftYPort);
    		
    		Robot.myElevator.setAbsoluteSpeed(leftJoystickInput);
    	} else {
    		// The '-' sign is because the values of the axes are reversed (forward is -1)
    		double leftJoystickInput = -OI.operatorController.getRawAxis(RobotMap.operatorLeftYPort);

    		if (leftJoystickInput < 0) leftJoystickInput /= 3;
    			
            // If joystick is moved, move the elevator. If not, if the elevator is above a certain height, hold
            if (Math.abs(leftJoystickInput) > 0.1) Robot.myElevator.setThrottledSpeed(leftJoystickInput);
            else if (Robot.myElevator.elevatorEncoder.getDistance() > RobotMap.minimumElevatorHoldDistance) Robot.myElevator.hold();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
