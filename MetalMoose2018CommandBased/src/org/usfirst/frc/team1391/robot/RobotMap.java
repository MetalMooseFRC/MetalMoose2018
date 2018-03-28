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

    // Port of the axes for driving of the Logitech joystick
    public static final int arcadeDriveRotationAxisPort = 2;

    // Port of the buttons for the driving of the Logitech joystick
    public static final int reverseDriveButtonPort = 1;
    public static final int throttleDriveButtonPort = 5;

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
    
    public static final int operatorButtonEndPort = 7;
    public static final int operatorButtonStartPort = 8;

    /* MOTOR MAPPING */
    public static final int drivebaseLeftMotorPort = 0;
    public static final int drivebaseRightMotorPort = 1;

    public static final int collectorLeftMotorPort = 2;
    public static final int collectorRightMotorPort = 3;

    public static final int elevatorLeftMotorPort = 4;
    public static final int elevatorRightMotorPort = 5;
    
    public static final int fourbarMotorPort = 6;

    public static final int hangerMotorPort = 7;

    public static final int hangerArmMotorPort = 8;

    /* SENSOR MAPPING */
    public static final int drivetrainEncoderAPort = 0;
    public static final int drivetrainEncoderBPort = 1;

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
    public static double autonomousDefaultTurningSpeed = 0.75;
    public static double autonomousDefaultDrivingSpeed = 0.75;

    // The allowed errors for the Gyro PID and the Encoder PID
    public static double drivetrainGyroPIDError = 3.5;
    public static double drivetrainEncoderPIDError = 2.5;

    // Data for the autonomous (layouts)
    public static String[][] chunkLayout = new String[][]{
            {"LeftLRL", "-Chunk(4)"},
            {"LeftLLL", "-Chunk(3)"},
            {"LeftRLR", "-Chunk(3)"},
            {"LeftRRR", "-Chunk(4)"},
            {"MiddleLRL", "-Chunk(0)"},
            {"MiddleLLL", "-Chunk(0)"},
            {"MiddleRLR", "Chunk(0)"},
            {"MiddleRRR", "Chunk(0)"},
            {"RightLRL", "Chunk(3)"},
            {"RightLLL", "Chunk(4)"},
            {"RightRLR", "Chunk(4)"},
            {"RightRRR", "Chunk(3)"}
    };

    // Chunks of movement for the autonomous sequences
    public static String[] chunks = {
            // Middle to switch (for RRR and RLR)
            "TurnDrive(45) DriveDistance(43) TurnDrive(-45) DriveTime(2) Outttake(0.5, S=0.8)",

            // Drive right to the switch (and pass the line in the process)
            "DriveDistance(130)",

            // Turn and place on the switch
            "TurnBy(-90) DriveTime(1.5) Outtake(0.5, S=0.8)",

            // Right to the scale
            "DriveDistance(290) TurnBy(-90) Elevate(2) Outtake(0.5, S=0.8) Elevate(0)",

            // Right to scale on the opposite side
            "DriveD(190) TurnDrive(-90) DriveDistance(180) TurnDrive(90) DriveDistance(30) TurnBy(90) Elevate(2) Output(0.5, S=1) Elevate(0)",

            // Back off from the middle, grab another cube from the pyramid
            "DD(-30) TD(45, S=-0.75) DD(-43) TD(-45, S=-0.75) FD() DT(1.5) I(1.5) FU() DD(-80) TD(45) DD(43) TD(-45) E(1) O(0.5, S=0.8)",

            // Score another cube on the scale
            "TB(-75) FD() DT(3) I(3) FU() DD(-30) TB(75) E(2) O(0.5, S=1) E(0)"
    };

    // Stores the chunks that make up the autonomous sequences
    public static Map<String, String> autonomousFromLayout = new HashMap<>();

    /* COLLECTOR */
    // Values for the collector speeds
    public static double collectorIntakeSpeed = 1.0;
    public static double collectorOuttakeSpeed = -0.3;
    public static double collectorHoldSpeed = 0.3;

    // Should the collector be intaking?
    public static boolean intakeWithCollector = false;

    /* ELEVATOR */
    // Conversion factor for elevator (to distance in inches)
    public static double elevatorEncoderCoefficient = 0.013428226131;

    // The maximum height of the elevator
    public static double elevatorMaximumDistance = 100;

    // Possible saved positions of the elevator
    public static double[] elevatorSetPoints = new double[]{0, 20, 100};

    // Hold speed and the limit above which to hold the elevator (in elevatorMaximumDistance units)
    public static double elevatorHoldSpeed = 0.3;
    public static double minimumElevatorHoldDistance = 3;

    // Slow down the elevator by this factor when going down
    public static double elevatorSlowCoefficient = 0.55;

    // This is the error tolerated by the ElevatorToHeight command
    public static double elevatorToHeightTolerance = 2;

    /* FOURBAR */
    // Hold the fourbar either up or down
    public static double fourbarHoldUpSpeed = 0.35;
    public static double fourbarHoldDownSpeed = -0.35;

    // The length and speed of the FourbarRaise and FourbarLower commands
    public static double fourbarRaiseSpeed = 0.85;
    public static double fourbarLowerSpeed = -0.4;
    public static double fourbarRaiseLength =1.5;
    public static double fourbarLowerLength = 1;

    // Should the fourbar be held in place now?
    public static boolean holdFourbar = true;

    /* HANGER */
    public static boolean holdHangerArm = false;
    public static int hangerArmOrientation = 1;
    public static double hangerMoveSpeed = 0.5;
    public static double hangerHoldSpeed = 0.2;

    /* MISCELLANEOUS */
    // Switching drive modes
    // 0 is tank drive and 1 is arcade drive with the Logitech controller
    // 2 uses the Y axis and the rotation axis from the joystick controller
    public static int driveMode = 2;

    // Minimal input from the joystick axes (when in default position, they are not always zero
    public static double minimalJoystickAxisInput = 0.1;
}
