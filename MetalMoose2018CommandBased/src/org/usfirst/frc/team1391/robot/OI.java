/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team1391.robot.commands.CollectorIntake;
import org.usfirst.frc.team1391.robot.commands.CollectorOuttake;
import org.usfirst.frc.team1391.robot.commands.FourbarLower;
import org.usfirst.frc.team1391.robot.commands.FourbarRaise;
import org.usfirst.frc.team1391.robot.commands.DrivetrainSkootch;
import org.usfirst.frc.team1391.robot.commands.ElevatorToHeight;

/**
 * Connect physical operator interface to commands.
 */
public class OI {
    /* DRIVE TEAM CONTROLLERS */
    public static final Joystick driveStick = new Joystick(RobotMap.driverControllerPort),
            operatorController = new Joystick(RobotMap.operatorControllerPort);

    // For going backwards in teleop (for the driver), overriding the throttled drivebase, and skootching the robot
    public static final Button reverseDriveButton = new JoystickButton(driveStick, RobotMap.reverseDriveButtonPort),
            throttleDriveButton = new JoystickButton(driveStick, RobotMap.throttleDriveButtonPort),
            skootchRightButton = new JoystickButton(driveStick, RobotMap.skootchLeftButtonPort),
            skootchLeftButton = new JoystickButton(driveStick, RobotMap.skootchRightButtonPort);

    // Control buttons for the operator
    public static final Button operatorA = new JoystickButton(operatorController, RobotMap.operatorButtonAPort),
            operatorB = new JoystickButton(operatorController, RobotMap.operatorButtonBPort),
            operatorX = new JoystickButton(operatorController, RobotMap.operatorButtonXPort),
            operatorY = new JoystickButton(operatorController, RobotMap.operatorButtonYPort),
            operatorLB = new JoystickButton(operatorController, RobotMap.operatorButtonLBPort),
            operatorRB = new JoystickButton(operatorController, RobotMap.operatorButtonRBPort);
	
	// Map commands to operator stick
	OI() {
	    // Control the collector
		operatorLB.whileHeld(new CollectorIntake());
		operatorRB.whileHeld(new CollectorOuttake());

		// Control the fourbar
		operatorA.whenPressed(new FourbarLower());
		operatorY.whenPressed(new FourbarRaise());

    	// Skootching buttons
    	skootchLeftButton.whenPressed(new DrivetrainSkootch(0));
    	skootchRightButton.whenPressed(new DrivetrainSkootch(1));
    	
    	// Go to hanging position with the elevator
    	operatorX.whenPressed(new ElevatorToHeight(3));
	}
}
