package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.commands.Drive;
import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;


/**
 * Controls the drivebase motors.
 */
public class DriveTrain extends Subsystem {

	//Left motor speed controllers																																						
	VictorSP leftMotor1 = new VictorSP(RobotMap.drivebaseLeftMotor1Port);
	VictorSP leftMotor2 = new VictorSP(RobotMap.drivebaseLeftMotor2Port);
	VictorSP leftMotor3 = new VictorSP(RobotMap.drivebaseLeftMotor3Port);
	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2, leftMotor3);
	
	//Right motor speed controllers
	VictorSP rightMotor1 = new VictorSP(RobotMap.drivebaseRightMotor1Port);
	VictorSP rightMotor2 = new VictorSP(RobotMap.drivebaseRightMotor2Port);
	VictorSP rightMotor3 = new VictorSP(RobotMap.drivebaseRightMotor3Port);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2, rightMotor3);

	//The actual drive
	DifferentialDrive myDifferentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
	
	Encoder myEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    	
    	myEncoder.reset();
    }
    
    public void arcadeDrive(double left, double right) {
    	myDifferentialDrive.arcadeDrive(left, right);

    	System.out.println(myEncoder.getRaw());
    }
	
    public void tankDrive(double left, double right) {
    	myDifferentialDrive.tankDrive(left, right);
    }
}
