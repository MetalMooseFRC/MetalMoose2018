/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import org.usfirst.frc.team1391.robot.commands.CollectorIntake;
import org.usfirst.frc.team1391.robot.commands.CollectorOuttake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Connect physical operator interface to commands.
 */
public class OI {
	/** DRIVE TEAM CONTROLLERS **/
	public static final Joystick driveStick = new Joystick(RobotMap.driverControllerPort),
			operatorController = new Joystick(RobotMap.operatorControllerPort);

	public static final Button operatorA = new JoystickButton(operatorController, 1),
			operatorB = new JoystickButton(operatorController, 2),
			operatorX = new JoystickButton(operatorController, 3),
			operatorY = new JoystickButton(operatorController, 4),
			operatorLB = new JoystickButton(operatorController, 5),
			operatorRB = new JoystickButton(operatorController, 6);

	public static final int operatorLeftX = 0, operatorLeftY = 1, operatorLeftTrigger = 2, operatorRightTrigger = 3,
			operatoRightX = 4, operatorRightY = 5;
	
	public OI() {
		operatorLB.whileHeld(new CollectorIntake());
		operatorRB.whileHeld(new CollectorOuttake());
	}
}
