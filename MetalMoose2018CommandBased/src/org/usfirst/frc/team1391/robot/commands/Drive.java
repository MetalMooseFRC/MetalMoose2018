package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in teleop.
 */
public class Drive extends Command {

    public Drive() {
        requires(Robot.myDriveTrain);
    }

    protected void initialize() {

    }

    /**
     * Repeatedly adjust the speed of the drive train from the reading of the joystick axes
     */
    protected void execute() {
        switch (RobotMap.driveMode) {

            // Arcade drive using the reading from the main joystick of the Logitech
            // controller
            case 0: {
                // The '-' is because pulling the joystick forward is -1 and we want it to be +1 (and vice versa)
                double yAxisReading = -OI.driveStick.getY();

                // The X axis is fine, since the rotation is clockwise and rightmost value of the x axis is +1
                double xAxisReading = OI.driveStick.getX();

                Robot.myDriveTrain.arcadeDrive(yAxisReading, xAxisReading);

                break;
            }

            // Tank drive using both joysticks' y axes from the Logitech controller
            case 1: {
                // The '-' is for the same reason as the '-' on the arcade drive
                double yAxisLeftStickReading = -OI.driveStick.getRawAxis(RobotMap.tankDriveLeftStickYAxisPort);
                double yAxisRightStickReading = -OI.driveStick.getRawAxis(RobotMap.tankDriveRightStickYAxisPort);

                Robot.myDriveTrain.tankDrive(yAxisLeftStickReading, yAxisRightStickReading);

                break;
            }

            // Arcade drive using the Y and the rotation (as X) axis of the Logitech joystick
            case 2: {
                // This is the axis that adjusts the speed of the entire robot
                // The arithmetic changes are made so that it goes from 0 to 1, instead of from -1 to 1
                double robotSpeed = (1.0 - OI.driveStick.getRawAxis(RobotMap.arcadeDriveSpeedAxisPort)) / 2.0;

                double forwardSpeed = OI.driveStick.getY() * robotSpeed;
                double turningSpeed = OI.driveStick.getRawAxis(RobotMap.arcadeDriveRotationAxisPort) * robotSpeed;

                if (OI.driveButton.get()) Robot.myDriveTrain.arcadeDrive(forwardSpeed, turningSpeed);
                else Robot.myDriveTrain.arcadeDrive(-forwardSpeed, turningSpeed);

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
