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
        double joystickInput = OI.operatorController.getRawAxis(RobotMap.operatorRightYPort);

        // If joystick is moved, move the elevator. If not, if the elevator is above a certain height, hold
        if (joystickInput != 0) Robot.myElevator.setThrottledSpeed(joystickInput);
        else if (Robot.myElevator.elevatorEncoder.getDistance() > RobotMap.minimumElevatorHoldDistance) Robot.myElevator.hold();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
