package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team1391.robot.BlankPIDOutput;
import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;

/**
 * Controls the drivebase motors.
 */
public class DriveTrain extends Subsystem {

	// Left motor speed controllers
	VictorSP leftMotor1 = new VictorSP(RobotMap.drivebaseLeftMotor1Port);
	VictorSP leftMotor2 = new VictorSP(RobotMap.drivebaseLeftMotor2Port);
	VictorSP leftMotor3 = new VictorSP(RobotMap.drivebaseLeftMotor3Port);
	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2, leftMotor3);

	// Right motor speed controllers
	VictorSP rightMotor1 = new VictorSP(RobotMap.drivebaseRightMotor1Port);
	VictorSP rightMotor2 = new VictorSP(RobotMap.drivebaseRightMotor2Port);
	VictorSP rightMotor3 = new VictorSP(RobotMap.drivebaseRightMotor3Port);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2, rightMotor3);

	// The actual drive
	DifferentialDrive myDifferentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	// Create sensors
	public Encoder myEncoder = new Encoder(RobotMap.encoderAPort, RobotMap.encoderBPort, false, Encoder.EncodingType.k4X);
	public AHRS myAHRS = new AHRS(SPI.Port.kMXP);

	public BlankPIDOutput encoderOutput = new BlankPIDOutput();
	public BlankPIDOutput gyroOutput = new BlankPIDOutput();
	
	public PIDController encoderController = new PIDController(0, 0, 0, 0, myEncoder, encoderOutput);
	public PIDController gyroController = new PIDController(0.09, 0.005, 0, 0, myAHRS, gyroOutput);
	
	public DriveTrain() {
		encoderController.setOutputRange(-1.0, 1.0);
		encoderController.setAbsoluteTolerance(0.5);
		
		gyroController.setInputRange(-180.0, +180.0);
		gyroController.setOutputRange(-1.0, 1.0);
		gyroController.setAbsoluteTolerance(0.1);
		gyroController.setContinuous(true);
		
	}
	public void initDefaultCommand() {
		//setDefaultCommand(new Drive());
	}

	public void arcadeDrive(double left, double right) {
		myDifferentialDrive.arcadeDrive(left, right);
	}

	public void tankDrive(double left, double right) {
		myDifferentialDrive.tankDrive(left, right);
	}
	
	
}
