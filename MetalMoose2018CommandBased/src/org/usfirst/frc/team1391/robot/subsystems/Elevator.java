package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.ElevatorMovement;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * Controls the two motors on the elevator to lift and drop.
 */
public class Elevator extends Subsystem {

	Spark leftElevatorMotor = new Spark(RobotMap.elevatorMotorLeftPort);
	Spark rightElevatorMotor = new Spark(RobotMap.elevatorMotorRightPort);

	// Since the movement of the motors will be simultaneous, we can group them.
	SpeedControllerGroup elevatorMotorGroup = new SpeedControllerGroup(leftElevatorMotor, rightElevatorMotor);

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorMovement());
	}

	public void moveElevator(double speed) {
		elevatorMotorGroup.set(speed);
	}
}
