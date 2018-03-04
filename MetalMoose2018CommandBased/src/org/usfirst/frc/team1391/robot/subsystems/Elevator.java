package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.ElevatorManualControl;

/**
 * Controls the elevator.
 */
public class Elevator extends Subsystem {
    //
    private Spark elevatorLeftMotor = new Spark(RobotMap.elevatorLeftMotorPort);
    private Spark elevatorRightMotor = new Spark(RobotMap.elevatorRightMotorPort);
    private SpeedControllerGroup elevatorMotors = new SpeedControllerGroup(elevatorLeftMotor, elevatorRightMotor);

    public Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderAPort, RobotMap.elevatorEncoderBPort, false, Encoder.EncodingType.k4X);

    public Elevator() {
        elevatorEncoder.setDistancePerPulse(RobotMap.elevatorEncoderCoefficient);
        elevatorLeftMotor.setInverted(true);
    }

    public void setThrottledSpeed(double speed) {
        double elevatorPosition = elevatorEncoder.getDistance();

        // Calculates the progressively slower elevator speed
        double elevatorSpeed = -speed * ((-(elevatorPosition * elevatorPosition) / 264) + (17 * elevatorPosition) / 132);
    }

    public void setAbsoluteSpeed(double speed) {
        elevatorMotors.set(speed);
    }

    public void hold() {
        elevatorMotors.set(RobotMap.elevatorHoldSpeed);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorManualControl());
    }
}
