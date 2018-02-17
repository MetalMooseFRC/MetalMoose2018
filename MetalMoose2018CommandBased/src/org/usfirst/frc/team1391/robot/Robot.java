/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import org.usfirst.frc.team1391.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team1391.robot.subsystems.*;

import edu.wpi.first.wpilibj.Encoder;
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

	// Create sensor objects
	public static Encoder myEncoder = new Encoder(RobotMap.encoderAPort, RobotMap.encoderBPort, false,
			Encoder.EncodingType.k4X);

	SendableChooser<Integer> driveModeChooser = new SendableChooser<>();
	SendableChooser<String> autoTypeChooser = new SendableChooser<>();
	SendableChooser<String> autoStrategyChooser = new SendableChooser<>();
	
	AutonomousCommandGroup myAuton;

	/**
	 * Puts values on SmartDashboard.
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
		
		myAuton = new AutonomousCommandGroup(autoTypeChooser, autoStrategyChooser);
		myAuton.start();
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
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
