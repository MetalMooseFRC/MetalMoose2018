package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;

import org.usfirst.frc.team1391.robot.commands.Drive;
/**
 *
 */
public class DriveTrain extends Subsystem {

	VictorSP leftMotor = new VictorSP(RobotMap.leftMotorPort);
	VictorSP rightMotor = new VictorSP(RobotMap.rightMotorPort);

	DifferentialDrive myDrive = new DifferentialDrive(leftMotor,rightMotor);
	
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

