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
     * If the B button is pressed, override to absolute speed (unthrottled).
     * If there is no input in the joystick, holds the elevator in place.
     */
    protected void execute() {
        // The stick that controls the elevator ('-' is because the axes are reversed - forward is -1...)
        double leftJoystickInput = -OI.operatorController.getRawAxis(RobotMap.operatorLeftYPort);

        // If 'overriden' by pressing B on the operator joystick
    	if (OI.operatorB.get()) {
    		Robot.myElevator.setAbsoluteSpeed(leftJoystickInput);
    	} else {
    	    // When going down, we want to be way slower than when going up
            // This will be added to the RobotMap in the future (RobotMap needs to be refactored before that)
    		if (leftJoystickInput < 0) leftJoystickInput /= 3;
    			
            // If joystick is set to a value over the hold threshold, move the elevator.
            // If not and the elevator is above a certain height, hold (we don't want to toast the motors)
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
