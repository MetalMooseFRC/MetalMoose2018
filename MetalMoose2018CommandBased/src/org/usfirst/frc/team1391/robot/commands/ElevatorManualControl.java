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

    protected void initialize() {
    }

    /**
     * Repeatedly sets the speed of the elevator (through the setThrottle function).
     * If the B button is pressed, override to absolute speed (unthrottled).
     * If there is no input in the joystick, holds the elevator in place.
     */
    protected void execute() {
        // The stick that controls the elevator ('-' is because the axes are reversed - forward is -1...)
        double leftJoystickInput = -OI.operatorController.getRawAxis(RobotMap.operatorLeftYPort);

        // If 'overriden,' get absolute control
        if (OI.operatorB.get()) {
            Robot.myElevator.setAbsoluteSpeed(leftJoystickInput);
        } else {
            // When going down, we want to go more slowly (than going up... gravity)
            if (leftJoystickInput < 0) leftJoystickInput *= RobotMap.elevatorSlowCoefficient;

            // If joystick is set to a value over a certain threshold, move the elevator.
            // If not and the elevator is above a certain height, hold (we don't want to toast the motors)
            if (Math.abs(leftJoystickInput) > RobotMap.minimalJoystickAxisInput)
                Robot.myElevator.setThrottledSpeed(leftJoystickInput, 0, RobotMap.elevatorMaximumDistance);
        }

        if (Robot.myElevator.elevatorEncoder.getDistance() > RobotMap.minimumElevatorHoldDistance)
            Robot.myElevator.hold();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
