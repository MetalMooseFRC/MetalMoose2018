package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class Drive extends Command {

	public Drive() {
		requires(Robot.myDriveTrain);
	}

	protected void initialize() {

	}

	// Repeatedly adjust the speed of the drive train from the reading of the
	// joystick axes
	protected void execute() {
		switch (RobotMap.driveMode) {
		
			// Arcade drive using the reading from the main joystick of the Logitech
			// controller
			case 0: {
				// The '-' is because pulling the joystick forward is -1 and we want it to be +1
				// (and vice versa)
				// The X axis is fine, since the rotation is clockwise and rightmost value of
				// the x axis is +1
				Robot.myDriveTrain.arcadeDrive(-OI.driveStick.getY(), OI.driveStick.getX());
				break;
			}
			
			// Tank drive using both joysticks from the Logitech controller
			case 1: {
				// Reading the Y axes of the joysticks on the Logitech controller
				// The '-' is for the same reason as the '-' on the arcade drive
				Robot.myDriveTrain.tankDrive(-OI.driveStick.getRawAxis(RobotMap.tankDriveLeftStickYAxisPort),
						-OI.driveStick.getRawAxis(RobotMap.tankDriveRightStickYAxisPort));
				break;
			}
				
			// Arcade drive using the Y and the rotation (as X) axis of the Logitech
			// joystick
			case 2: {
				// The '-' sign is because the value is reversed
				double robotSpeed = 1.0 / 2.0 - OI.driveStick.getRawAxis(RobotMap.arcadeDriveSpeedAxisPort) / 2.0;
	
				double forwardMotion = OI.driveStick.getY() * robotSpeed;
				double turningMotion = OI.driveStick.getRawAxis(RobotMap.arcadeDriveRotationAxisPort) * robotSpeed;
	
				if (OI.driveButton.get()) Robot.myDriveTrain.arcadeDrive(forwardMotion, turningMotion);
				else Robot.myDriveTrain.arcadeDrive(-forwardMotion, turningMotion);
				break;
			}
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
