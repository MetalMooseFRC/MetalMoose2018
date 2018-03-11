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

/**
 * Connect physical operator interface to commands.
 */
public class OI {
	/** DRIVE TEAM CONTROLLERS **/
	public static final Joystick driveStick = new Joystick(RobotMap.driverControllerPort),
			operatorController = new Joystick(RobotMap.operatorControllerPort);
		
	// For going backwards in teleop (for the driver)
	public static final Button driveButton = new JoystickButton(driveStick, RobotMap.arcadeDriveBackwardButtonPort);

	// Control buttons for the operator
	public static final Button operatorA = new JoystickButton(operatorController, RobotMap.operatorButtonAPort),
			operatorB = new JoystickButton(operatorController, RobotMap.operatorButtonBPort),
			operatorX = new JoystickButton(operatorController, RobotMap.operatorButtonXPort),
			operatorY = new JoystickButton(operatorController, RobotMap.operatorButtonYPort),
			operatorLB = new JoystickButton(operatorController, RobotMap.operatorButtonLBPort),
			operatorRB = new JoystickButton(operatorController, RobotMap.operatorButtonRBPort);
	
	// Map commands to operator stick
	public OI() {
	    // Control the collector
		operatorLB.whileHeld(new CollectorIntake());
		operatorRB.whileHeld(new CollectorOuttake());

		// Control the elevator
        operatorA.whenPressed(new FourbarLower(RobotMap.fourbarLowerLength));
        operatorY.whenPressed(new FourbarRaise(RobotMap.fourbarRaiseLength));
	}
}
