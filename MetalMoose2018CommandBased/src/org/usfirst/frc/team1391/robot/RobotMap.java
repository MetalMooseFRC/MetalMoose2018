/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.Preferences;

/**
 * Used for global variables and to reduce magic numbers in the code.
 */
public class RobotMap {
	/** CONTROLLER MAPPING **/
	// Default controller port
	public static final int driverControllerPort = 0;
	public static final int operatorControllerPort = 1;

	// Ports for the Y axes of the two joysticks of the Logitech controller
	public static final int tankDriveLeftStickYAxisPort = 1;
	public static final int tankDriveRightStickYAxisPort = 5;

	// Port of the rotation axis of the Logitech joystick
	public static final int arcadeDriveRotationAxisPort = 2;

	/** MOTOR MAPPING **/
	public static final int drivebaseLeftMotor1Port = 0;
	public static final int drivebaseLeftMotor2Port = 1;
	public static final int drivebaseLeftMotor3Port = 2;

	public static final int drivebaseRightMotor1Port = 3;
	public static final int drivebaseRightMotor2Port = 4;
	public static final int drivebaseRightMotor3Port = 5;

	/** SENSOR MAPPING **/
	public static final int encoderAPort = 0;
	public static final int encoderBPort = 1;
	
	/** MISCELLANEOUS **/
	// Switching drive modes
	// 0 is tank drive and 1 is arcade drive with the Logitech controller
	// 2 uses the Y axis and the rotation axis from the joystick controller
	public static int driveMode = 2;
	public static String autoType;
	public static String autoStrategy;
	public static Preferences prefs = Preferences.getInstance();
	public static String gameData;
	public static String autoCommandString;
	public static String autoCommandStringKey;
}
