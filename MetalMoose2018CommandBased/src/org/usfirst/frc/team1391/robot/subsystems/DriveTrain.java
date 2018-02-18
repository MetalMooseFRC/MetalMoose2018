package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team1391.robot.BlankPIDOutput;
import org.usfirst.frc.team1391.robot.Robot;
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
	private VictorSP leftMotor1 = new VictorSP(RobotMap.drivebaseLeftMotor1Port);
	private VictorSP leftMotor2 = new VictorSP(RobotMap.drivebaseLeftMotor2Port);
	private VictorSP leftMotor3 = new VictorSP(RobotMap.drivebaseLeftMotor3Port);
	private SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2, leftMotor3);

	// Right motor speed controllers
	private VictorSP rightMotor1 = new VictorSP(RobotMap.drivebaseRightMotor1Port);
	private VictorSP rightMotor2 = new VictorSP(RobotMap.drivebaseRightMotor2Port);
	private VictorSP rightMotor3 = new VictorSP(RobotMap.drivebaseRightMotor3Port);
	private SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2, rightMotor3);

	// DifferentialDrive object
	private DifferentialDrive myDifferentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	// Sensors (encoder, gyro)
	public Encoder myEncoder = new Encoder(RobotMap.encoderAPort, RobotMap.encoderBPort, false,
			Encoder.EncodingType.k4X);
	public AHRS myAHRS = new AHRS(SPI.Port.kMXP);

	// PIDOutput objects
	public BlankPIDOutput encoderOutput = new BlankPIDOutput();
	public BlankPIDOutput gyroOutput = new BlankPIDOutput();

	// PIDController objects
	public PIDController encoderController = new PIDController(RobotMap.encoderP, RobotMap.encoderI, RobotMap.encoderD, 0, myEncoder, encoderOutput);
	public PIDController gyroController = new PIDController(RobotMap.gyroP, RobotMap.gyroI, RobotMap.gyroD, 0, myAHRS, gyroOutput);

	public DriveTrain() {
		// Encoder PIDObject values
		encoderController.setOutputRange(-RobotMap.autonSpeedLimit, RobotMap.autonSpeedLimit);
		encoderController.setAbsoluteTolerance(3.0);

		// Gyro PIDObject values
		gyroController.setInputRange(-180.0, +180.0);
		gyroController.setOutputRange(-RobotMap.autonSpeedLimit, RobotMap.autonSpeedLimit);
		gyroController.setAbsoluteTolerance(0.1);
		gyroController.setContinuous(true);

		// Sets myEncoder to output distance travelled in inches
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
