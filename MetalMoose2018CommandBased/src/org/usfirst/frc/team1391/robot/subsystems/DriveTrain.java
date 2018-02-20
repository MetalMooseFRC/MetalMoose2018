package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team1391.robot.BlankPIDOutput;
import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;

/**
 * Controls the drivebase motors.
 */
public class DriveTrain extends Subsystem {

	// Objects that control the driving of the drivebase
	private VictorSP leftMotor = new VictorSP(RobotMap.drivebaseLeftMotorPort);
	private VictorSP rightMotor = new VictorSP(RobotMap.drivebaseRightMotorPort);
	private DifferentialDrive myDifferentialDrive = new DifferentialDrive(leftMotor, rightMotor);

	// Sensors (encoder, gyro)
	public Encoder myEncoder = new Encoder(RobotMap.encoderAPort, RobotMap.encoderBPort, false, Encoder.EncodingType.k4X);
	public AHRS myAHRS = new AHRS(SPI.Port.kMXP);

	// PIDOutput objects
	public BlankPIDOutput encoderOutput = new BlankPIDOutput();
	public BlankPIDOutput gyroOutput = new BlankPIDOutput();

	// PIDController objects
	public PIDController encoderPID = new PIDController(RobotMap.encoderP, RobotMap.encoderI, RobotMap.encoderD, 0, myEncoder, encoderOutput);
	public PIDController gyroPID = new PIDController(RobotMap.gyroP, RobotMap.gyroI, RobotMap.gyroD, 0, myAHRS, gyroOutput);

	public DriveTrain() {
		// Encoder PIDObject values
		encoderPID.setOutputRange(-RobotMap.autonomousSpeedLimit, RobotMap.autonomousSpeedLimit);
		encoderPID.setAbsoluteTolerance(2.0); //2 inches

		// Gyro PIDObject values
		gyroPID.setInputRange(-180.0, +180.0);
		gyroPID.setOutputRange(-RobotMap.autonomousSpeedLimit, RobotMap.autonomousSpeedLimit);
		gyroPID.setAbsoluteTolerance(3.5); //3.5 degrees
		gyroPID.setContinuous(true); //loops around

		// Sets myEncoder to output distance traveled in inches
		myEncoder.setDistancePerPulse(RobotMap.encoderCoefficient);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void arcadeDrive(double x, double y) {
		myDifferentialDrive.arcadeDrive(x, y);
	}

	public void tankDrive(double left, double right) {
		myDifferentialDrive.tankDrive(left, right);
	}

}
