/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Connect physical operator interface to commands.
 */
public class OI {
	/** DRIVE TEAM CONTROLLERS **/
	public static final Joystick driveStick = new Joystick(RobotMap.driverControllerPort);
	public static final Joystick operatorStick = new Joystick(RobotMap.operatorControllerPort);
}
