/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for global variables (auton, driving) and constants (ports).
 */
public class RobotMap {
    /* CONTROLLER MAPPING */
    // Default controller port
    static final int driverControllerPort = 0;
    static final int operatorControllerPort = 1;

    // Ports for the Y axes of the two joysticks of the Logitech controller
    public static final int tankDriveLeftStickYAxisPort = 1;
    public static final int tankDriveRightStickYAxisPort = 5;

    // Port of the rotation axis of the Logitech joystick
    public static final int arcadeDriveRotationAxisPort = 2;
    public static final int arcadeDriveSpeedAxisPort = 3;
    static final int arcadeDriveBackwardButtonPort = 1;

    // Operator controller button mapping
    static final int operatorButtonAPort = 1;
    static final int operatorButtonBPort = 2;
    static final int operatorButtonXPort = 3;
    static final int operatorButtonYPort = 4;
    static final int operatorButtonLBPort = 5;
    static final int operatorButtonRBPort = 6;

    // Operator controller axis mapping
    public static final int operatorLeftXPort = 0;
    public static final int operatorLeftYPort = 1;
    public static final int operatorLeftTriggerPort = 2;
    public static final int operatorRightTriggerPort = 3;
    public static final int operatoRightXPort = 4;
    public static final int operatorRightYPort = 5;

    /* MOTOR MAPPING */
    public static final int drivebaseLeftMotorPort = 0;
    public static final int drivebaseRightMotorPort = 1;

    public static final int collectorLeftMotorPort = 2;
    public static final int collectorRightMotorPort = 3;

    public static final int elevatorLeftMotorPort = 4;
    public static final int elevatorRightMotorPort = 5;
    
    public static final int fourbarMotorPort = 6;

    public static final int hangerMotorPort = 7;

    /* SENSOR MAPPING */
    public static final int drivetrainEncoderBPort = 1;
    public static final int drivetrainEncoderAPort = 0;

    public static final int elevatorEncoderAPort = 2;
    public static final int elevatorEncoderBPort = 3;

    /* AUTONOMOUS, PID */
    // Values for the PID of gyro
    public static double drivetrainGyroP = 0.055;
    public static double drivetrainGyroI = 0.00001;
    public static double drivetrainGyroD = 0.008;

    // Values for the PID of the encoder
    public static double drivetrainEncoderP = 0.6;
    public static double drivetrainEncoderI = 0.0001;
    public static double drivetrainEncoderD = 0.025;

    // Conversion factor for rotational units of encoder to inches of robot travel
    public static double drivetrainEncoderCoefficient = 0.00618040;

    // Multiplies the speed of the robot by this value (in autonomous)
    public static double autonomousTurningSpeedLimit = 0.7;
    public static double autonomousDrivingSpeedLimit = 0.7;

    // The allowed errors for the Gyro PID and the Encoder PID
    public static double drivetrainGyroPIDError = 3.5;
    public static double drivetrainEncoderPIDError = 2.5;

    // Chunks of movement for the autonomous sequences
    public static String[] chunks = {
            // Middle to switch (for RRR and RLR)
    		"Drive(36) TurnBy(45) Drive(70) TurnBy(-45) DriveTime(2, 0.35) Outtake(0.5)",

            // Drive right to the switch (pass the line)
            "Drive(130)",

            // Turn and place on the switch
    		"TurnBy(-90) DriveTime(2, 0.35) Outtake(0.5)",

            // Right to the scale
    		"Drive(240) TurnBy(-45) Elevate(2) Outtake(0.5) Elevate(0)"
    };
    
    // Stores the starting positions of the robot (arr[0], arr[1] and arr[2] being left, middle and right)
    public static double[][] startingPositionCoordinates = new double[][]{{-115, 19.5}, {5, 19.5}, {115, 19.5}};

    // Stores the chunks that make up the autonomous sequences
    public static Map<String, String> autonomousFromLayout = new HashMap<>();

    // The absolute angle that the robot is currently in
    public static double absoluteAngle = 0;

    // The absolute position of the robot in the field
    public static double robotPositionX = 0;
    public static double robotPositionY = 0;

    /* COLLECTOR */
    // Values for the collector speeds
    public static double collectorIntakeSpeed = 1.0;
    public static double collectorOuttakeSpeed = -1.0;
    public static double collectorHoldSpeed = 0.3;

    // Should the collector be intaking?
    public static boolean intakeWithCollector = false;

    /* ELEVATOR */
    // Conversion factor for elevator (to distance in inches)
    public static double elevatorEncoderCoefficient = 0.013428226131;

    // The maximum height of the elevator
    public static double elevatorMaximumDistance = 100;

    // Possible saved positions of the elevator
    public static double[] elevatorSetPoints = new double[]{0, 15, 80};

    // Hold speed and the limit above which to hold the elevator (in elevatorMaximumDistance units)
    public static double elevatorHoldSpeed = 0.3;
    public static double minimumElevatorHoldDistance = 2;

    // Slow down the elevator by this factor when going down
    public static double elevatorSlowCoefficient = 0.3;

    /* FOURBAR */
    // Values for Lowering the fourbar
    // This is basically just an impulse, it will go down thanks to gravity
    public static double fourbarLowerLength = 0.8;
    public static double fourbarLowerPowerLength = 0.25;
    public static double fourbarLowerSpeed = -0.3;

    // Values for raising the fourbar
    public static double fourbarRaiseLength = 0.8;
    public static double fourbarRaiseSpeed = 0.6;

    // Hold speed of the fourbar
    public static double fourbarHoldSpeed = 0.2;

    // Should the fourbar be held in place now?
    public static boolean holdFourbar = false;


    /* MISCELLANEOUS */
    // Switching drive modes
    // 0 is tank drive and 1 is arcade drive with the Logitech controller
    // 2 uses the Y axis and the rotation axis from the joystick controller
    public static int driveMode = 2;
}
