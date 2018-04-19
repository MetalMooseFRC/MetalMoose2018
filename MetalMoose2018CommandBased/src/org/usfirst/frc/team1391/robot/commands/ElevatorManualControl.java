package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually controls the elevator (using throttling).
 */
public class ElevatorManualControl extends Command {
    // These variables are for detecting repeated readings from the encoder and resetting it...
    private double lastElevatorValue = 0;
    private double valueRepeatCounter = 0;

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
        if (OI.operatorB.get()) Robot.myElevator.setAbsoluteSpeed(leftJoystickInput);
        else {
            // If joystick is set to a value over a certain threshold, move the elevator.
            // If not and the elevator is above a certain height, hold (we don't want to toast the motors)
            if (Math.abs(leftJoystickInput) > RobotMap.minimalJoystickAxisInput)
                Robot.myElevator.setThrottledSpeed(leftJoystickInput);
            else if (Robot.myElevator.elevatorEncoder.getDistance() > RobotMap.minimumElevatorHoldDistance)
                Robot.myElevator.hold();
        }

        // If we are down and the value of the encoder is the same as it was before, increment the counter
        // We are doing this, because every time the elevator goes up and down, there is a ~0.5 inch deviation in the encoder
        if (Robot.myElevator.elevatorEncoder.getDistance() < RobotMap.minimumElevatorHoldDistance && lastElevatorValue == Robot.myElevator.elevatorEncoder.getDistance())
            valueRepeatCounter++;
        else valueRepeatCounter = 0;

        // If we repeated repetitionCounter number of times, reset the elevator encoder and the counter
        if (valueRepeatCounter > RobotMap.elevatorValueRepetitionCounter) {
            Robot.myElevator.elevatorEncoder.reset();
            valueRepeatCounter = 0;
        }

        // Save the last value of the encoder (for the repeat counter)
        lastElevatorValue = Robot.myElevator.elevatorEncoder.getDistance();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
