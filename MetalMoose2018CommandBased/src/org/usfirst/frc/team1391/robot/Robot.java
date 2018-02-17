/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import org.usfirst.frc.team1391.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team1391.robot.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	// Create subsystem objects
	public static final DriveTrain myDriveTrain = new DriveTrain();

	// Create OI object
	public static final OI myOI = new OI();

	// Create SmartDashboard objects
	SendableChooser<Integer> driveModeChooser = new SendableChooser<>();
	SendableChooser<String> autoTypeChooser = new SendableChooser<>();
	SendableChooser<String> autoStrategyChooser = new SendableChooser<>();
	
	AutonomousCommandGroup myAuton;

	/**
	 * Initial setup of the robot (values on SmartDashboard)
	 */
	@Override
	public void robotInit() {
		// Which DriveMode to use in the teleop
		driveModeChooser.addDefault("Arcade Drive", 0);
		driveModeChooser.addObject("Tank Drive", 1);
		driveModeChooser.addObject("Joystick Arcade Drive", 2);
		SmartDashboard.putData("Drive Mode", driveModeChooser);
		
		autoTypeChooser.addDefault("Center", "Center");
		autoTypeChooser.addObject("Left", "Left");
		autoTypeChooser.addObject("Right", "Left");
		autoTypeChooser.addObject("Custom", "Custom");
		SmartDashboard.putData("Autonomous Type", autoTypeChooser);
		
		autoStrategyChooser.addDefault("Aggressive", "Aggressive");
		autoStrategyChooser.addObject("Conservative", "Conservative");
		autoStrategyChooser.addObject("Basic", "Basic");
		SmartDashboard.putData("Autonomous Strategy", autoStrategyChooser);
		SmartDashboard.putString("Custom Autonomous Command String", "");

		/** Temp for PID tuning **/
		SmartDashboard.putNumber("Gyro P", RobotMap.gyroP);
		SmartDashboard.putNumber("Gyro I", RobotMap.gyroI);
		SmartDashboard.putNumber("Gyro D", RobotMap.gyroD);

		SmartDashboard.putNumber("Encoder P", RobotMap.encoderP);
		SmartDashboard.putNumber("Encoder I", RobotMap.encoderI);
		SmartDashboard.putNumber("Encoder D", RobotMap.encoderD);

		SmartDashboard.putNumber("Distance", 0);
		SmartDashboard.putNumber("Angle", 0);

		// Status of the scheduler and the subsystems
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(myDriveTrain);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		/** Temp for PID tuning **/
		RobotMap.gyroP = SmartDashboard.getNumber("Gyro P", 0);
		RobotMap.gyroI = SmartDashboard.getNumber("Gyro I", 0);
		RobotMap.gyroD = SmartDashboard.getNumber("Gyro D", 0);

		RobotMap.encoderP = SmartDashboard.getNumber("Encoder P", 0);
		RobotMap.encoderI = SmartDashboard.getNumber("Encoder I", 0);
		RobotMap.encoderD = SmartDashboard.getNumber("Encoder D", 0);

		Robot.myDriveTrain.gyroController.setPID(RobotMap.gyroP, RobotMap.gyroI, RobotMap.gyroD);
		Robot.myDriveTrain.encoderController.setPID(RobotMap.encoderP, RobotMap.encoderI, RobotMap.encoderD);
		
		myAuton = new AutonomousCommandGroup(autoTypeChooser, autoStrategyChooser);
		myAuton.start();
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.myDriveTrain.myAHRS.reset();
		Robot.myDriveTrain.myEncoder.reset();
		
		// Get DriveMode from SmartDashBoard
		myAuton.cancel();
		RobotMap.driveMode = driveModeChooser.getSelected();
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {

	}
}
