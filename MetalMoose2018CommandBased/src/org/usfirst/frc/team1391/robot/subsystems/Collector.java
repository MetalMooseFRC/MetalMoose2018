package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.OI;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Controls the collector/fidget spinner motors.
 */
public class Collector extends Subsystem {
	VictorSP rightCollectorMotor = new VictorSP(RobotMap.rightCollectorMotorPort);
	VictorSP leftCollectorMotor = new VictorSP(RobotMap.leftCollectorMotorPort);
	
	 public void initDefaultCommand() {

	    }
	 //The speeds are negative for intake and outtake since the motors need to spin in opposite directions
	 //intake spins the motors towards the inside of the collector and outtake spins the motors away from the collector
	 public void intake(boolean active) {
		 if (active == true) {
		 rightCollectorMotor.set(-1);
		 leftCollectorMotor.set(1);
		 }
		 
	 }
	 public void outtake(boolean active) {
		 if (active == true) {
		 rightCollectorMotor.set(1);
		 leftCollectorMotor.set(-1);
		 }
	 }


}

