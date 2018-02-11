package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;

/**
 *  Controls the two motors on the elevator to lift and drop. 
 */
public class Elevator extends Subsystem {
	
	Spark leftMotor = new Spark(RobotMap.elevatorMotorLeftPort);
	Spark rightMotor = new Spark(RobotMap.elevatorMotorRightPort);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

