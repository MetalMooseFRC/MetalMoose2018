package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;

import org.usfirst.frc.team1391.robot.commands.Drive;

/**
 * Controls the drivebase motors.
 */
public class DriveTrain extends Subsystem {

	//Left motor speed controllers																																						
	VictorSP leftMotor1 = new VictorSP(RobotMap.leftMotor1Port);
	VictorSP leftMotor2 = new VictorSP(RobotMap.leftMotor2Port);
	VictorSP leftMotor3 = new VictorSP(RobotMap.leftMotor3Port);
	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2, leftMotor3);
	
	//Right motor speed controllers
	VictorSP rightMotor1 = new VictorSP(RobotMap.rightMotor1Port);
	VictorSP rightMotor2 = new VictorSP(RobotMap.rightMotor2Port);
	VictorSP rightMotor3 = new VictorSP(RobotMap.rightMotor3Port);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2, rightMotor3);

	DifferentialDrive myDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
    
    public void arcadeDrive(double left, double right) {
    	myDrive.arcadeDrive(left, right);
    }
	
    public void tankDrive(double left, double right) {
    	myDrive.tankDrive(left, right);
    }
}

