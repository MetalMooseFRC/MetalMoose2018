package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the collector manually using axes on the operator controller.
 */
public class CollectorManualControl extends Command {
    // Value to watch for axis release (for setting the hold of the cube)
    boolean wasJoystickPressed = false;

    // Value to set the collectorHold to after the axis as been released
    boolean valueForCollectorIntake = false;

    public CollectorManualControl() {
        requires(Robot.myCollector);
    }

    protected void initialize() {
    }

    protected void execute() {
        double leftTriggerValue = OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort);
        double rightTriggerValue = OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort);

        if (leftTriggerValue > RobotMap.minimalJoystickAxisInput) {
            Robot.myCollector.setAbsoluteSpeed(leftTriggerValue);

            wasJoystickPressed = true;
            valueForCollectorIntake = true;
        } else if (rightTriggerValue > RobotMap.minimalJoystickAxisInput) {
            Robot.myCollector.setAbsoluteSpeed(-rightTriggerValue);

            wasJoystickPressed = true;
            valueForCollectorIntake = false;
        }

        // If there are no inputs from the axes
        else {
            // If the joystick was just released
            if (wasJoystickPressed) {
                RobotMap.collectorHold = valueForCollectorIntake;
                wasJoystickPressed = false;
            }

            // Else if we want to hold the cube
            if (RobotMap.collectorHold) Robot.myCollector.hold();

                // Else just set to zero
            else Robot.myCollector.setAbsoluteSpeed(0);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
