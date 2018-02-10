/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

//import edu.wpi.first.wpilibj.*;

/**
 * Simplifies the wiring of the robot (so there are no magic numbers in the code)
 */
public class RobotMap {
	/** CONTROLLER MAPPING **/
	//Default controller port
	public static final int controllerPort = 0;	
	
	//Ports for the Y axes of the two joysticks of the Logitech controller
	public static final int tankDriveLeftStickYAxisPort = 1;
	public static final int tankDriveRightStickYAxisPort = 5;
	
	//Port of the rotation axis of the Logitech joystick
	public static final int arcadeDriveRotationAxisPort = 2;

	/** MOTOR MAPPING **/
	//When looking at the left side of the robot, the motors go counter-clockwise form the top one.
	public static final int leftMotor1Port = 0;
	public static final int leftMotor2Port = 1;
	public static final int leftMotor3Port = 2;

	//When looking at the right side of the robot, the motors go counter-clockwise form the top one.
	public static final int rightMotor1Port = 3;
	public static final int rightMotor2Port = 4;
	public static final int rightMotor3Port = 5;
	
	/*
	 * TO-DO: Determine which motor controller is responsible for which direction of elevator movement. Port number TBD
	 */
	//Channels for motors on the elevator
	public static final int eleMotorA = 6;
	public static final int eleMotorB = 7;
	
	/** MISCELLANEOUS **/
	//Switching drive modes
	//0 is tank drive and 1 is arcade drive with the Logitech controller
	//2 uses the Y axis and the rotation axis from the joystick controller
	public static int driveMode = 2;
}
