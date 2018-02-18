/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

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
	public static final int drivebaseLeftMotorPort = 0;
	public static final int drivebaseRightMotorPort = 1;

	public static final int collectorLeftMotorPort = 8;
	public static final int collectorRightMotorPort = 9;
	/** SENSOR MAPPING **/
	public static final int encoderAPort = 0;
	public static final int encoderBPort = 1;

	/** MISCELLANEOUS **/
	// Switching drive modes
	// 0 is tank drive and 1 is arcade drive with the Logitech controller
	// 2 uses the Y axis and the rotation axis from the joystick controller
	public static int driveMode = 2;

	// Values for the PID objects (P, I, D)
	public static double gyroP = 0.09;
	public static double gyroI = 0.0;
	public static double gyroD = 0.0;

	public static double encoderP = 0.09;
	public static double encoderI = 0.00001;
	public static double encoderD = 0.0;

	// Conversion factor for rotational units of encoder to inches of robot travel
	public static double encoderCoefficient = 0.00618040;
	
	// Slows speed of the robot in autonomous to this value
	public static double autonSpeedLimit = 0.7;
	
	public static String[] chunks = {
			"m:0:40",
			"m:20:0",
			"m:0:40 m:0:40"
	};
	// Accumulate error during autonomous
	public static double angleError = 0;
	public static double distanceError = 0;
	
	//setpoint values
	public static double collectorIntakeSpeed = -1.0;
	public static double collectorOuttakeSpeed = 1.0;
	public static double collectorHoldSpeed = -0.1;
}
