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
	public static int controllerPort = 0;

	/** MOTOR MAPPING **/
	public static int leftMotor1Port = 0;
	public static int leftMotor2Port = 1;
	public static int leftMotor3Port = 2;

	public static int rightMotor1Port = 3;
	public static int rightMotor2Port = 4;
	public static int rightMotor3Port = 5;
	
	/** MISC. **/
	// Switching drive modes (tank == true, arcade == false)
	public static boolean driveMode = true;
	
	//Ports for the tank drive from the logitech controller
	public static int tankDriveLeftStickYAxisPort = 1;
	public static int tankDriveRightStickYAxisPort = 5;
}
