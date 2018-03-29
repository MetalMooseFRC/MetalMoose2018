package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Controls the collector manually using axes on the operator controller.
 */
public class CollectorManualControl extends Command {
	// Value to watch for joystick release (for setting the hold of the cube)
	boolean wasJoystickPressed = false;
	boolean valueForCollectorIntake = false;
	
    public CollectorManualControl() {
        requires(Robot.myCollector);
    }

    protected void initialize() {
    }

    protected void execute() {
        double leftTriggerSpeed = OI.operatorController.getRawAxis(RobotMap.operatorLeftTriggerPort);
        double rightTriggerSpeed = OI.operatorController.getRawAxis(RobotMap.operatorRightTriggerPort);

        // Intakes the cube (the 0.1 is just an arbitrary threshold, because the joysticks' position is not 0)
        if (leftTriggerSpeed > 0.1) {
            Robot.myCollector.setAbsoluteSpeed(leftTriggerSpeed);

            // We are basically watching for when we release the joystick
            wasJoystickPressed = true;
            valueForCollectorIntake = true;
        }

        // Outtakes the cube (the 0.1 is just an arbitrary threshold, because the joysticks' position is not 0)
        else if (rightTriggerSpeed > 0.1) {
            Robot.myCollector.setAbsoluteSpeed(-rightTriggerSpeed);
            wasJoystickPressed = true;
            valueForCollectorIntake = false;
        } 
        
        // If there are no inputs from the axes
        else {
        	// If the joystick was just released
            if (wasJoystickPressed) {
            	RobotMap.intakeWithCollector = valueForCollectorIntake;
            	wasJoystickPressed = false;
            }
            
            // Else if we want to hold the cube (after intaking)
            if (RobotMap.intakeWithCollector) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorHoldSpeed);
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
