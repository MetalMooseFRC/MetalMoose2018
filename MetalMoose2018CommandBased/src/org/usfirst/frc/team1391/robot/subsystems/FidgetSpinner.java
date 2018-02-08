package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;

import org.usfirst.frc.team1391.robot.commands.Drive;

/**
 * Controls the collector/fidget spinner motors.
 */
public class FidgetSpinner extends Subsystem {
	VictorSP rightCollectorMotor = new VictorSP(RobotMap.rightCollectorMotorPort);
	VictorSP leftCollectorMotor = new VictorSP(RobotMap.leftCollectorMotorPort);
	
	 public void initDefaultCommand() {

	    }


}

