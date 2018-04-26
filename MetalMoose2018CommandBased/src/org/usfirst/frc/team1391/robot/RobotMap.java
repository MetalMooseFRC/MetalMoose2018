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
    public static final int fourbarButtonPort = 2;

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

    public static final int clampMotorPort = 9;

    /* SENSOR MAPPING */
    public static final int drivetrainEncoderAPort = 0;
    public static final int drivetrainEncoderBPort = 1;

    public static final int elevatorEncoderAPort = 2;
    public static final int elevatorEncoderBPort = 3;

    /* AUTONOMOUS, PID */
    // Values for the PID of gyro
    public static double drivetrainGyroP = 0.1;
    public static double drivetrainGyroI = 0.00001;
    public static double drivetrainGyroD = 0;

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
            {"LeftLRL", "-C(4)"},
            {"LeftLLL", "-C(3) -C(6) -C(7)"},
            {"LeftRLR", "-C(3) -C(6) -C(7)"},
            {"LeftRRR", "-C(4)"},
            {"MiddleLRL", "-C(13) -C(5) -C(10)"},
            {"MiddleLLL", "-C(13) -C(5) -C(10)"},
            {"MiddleRLR", "C(0) C(5) C(10)"},
            {"MiddleRRR", "C(0) C(5) C(10)"},
            {"RightLRL", "C(3) C(6) C(8)"},
            {"RightLLL", "C(4)"},
            {"RightRLR", "C(1) C(2)"},
            {"RightRRR", "C(3) C(6) C(8)"}
    };

    // Autonomous sequences
    public static String[] chunks = {
            // [0] From middle to switch
            "TD(45, S=0.78) DD(44) TD(-45, S=0.78) DT(0.35) O(0.4, S=0.8)",

            // [1] Cross the line
            "DD(130)",

            // [2] Turn and place on the switch after crossing the line from the side
            "TB(-90) DT(1.5) O(0.8, S=0.8)",

            // [3] Same side scale (coming short)
            "DD(224, S=0.9) E(2) TB(-40) O(0.4, S=1) E(0, M=P)",

            // [4] Opposite side scale
            //"DD(185, S=0.8) TD(-90) DD(165, S=0.8) TB(90) DD(15, S=0.6, M=P) E(2) O(0.4, S=0.4) DD(-6, M=P, S=0.7) E(0) TB(180) FD() T(0.5) TTC() TTC() I(0.8) DT(0.8) CI() DD(-5) FU()",
            "DD(185, S=0.8) TD(-90) DD(130, S=0.8)",

            // [5] Grab 2nd cube from the middle pyramid
            "DD(-10) TD(45, S=-0.78) DD(-44) TD(-45, S=-0.78) DT(0.5, S=-0.78) FD() I(2) DT(2, S=0.65) DD(-10) FU(M=P)",

            // [6] Grab 2nd cube after scoring on the scale
            "TB(-100) FD() DD(15) TTC() TTC() CO() I(1.9, S=0.6) DT(1.7, S=0.6) CI()",

            // [7] Back off and lift a cube up
            "DD(-5) FU()",

            // [8] Score on scale after picking up 2nd cube
            "DD(-15) TB(130) FU(M=P) DD(25, M=P, S=0.6) E(2) O(1, S=0.45) DD(-20, S=0.5) E(0)",

            // [9] Same side scale (fully into the null zone)
            "DD(285, S=0.85) TB(-90, S=0.8) DD(-13) E(2) O(0.8, S=0.9) E(0)",
            
            // [10] Score 2nd cube mid and grab 3rd one
            "TB(90) TD(-90, S=0.80) DT(0.4) O(0.5, S=0.8) FD(M=P) DD(-40) TB(-40) I(2.2) DT(2.2, S=0.6) CI() DD(-20) FU()",

            // [11] Score on the same side switch and grab another cube (after this, use chunk 7)
            "DD(120) TD(-90) DT(1) O(0.5, S=0.7) TD(-90, S=-0.78) FD() DD(-50) TTC() TTC() I(2.2) DT(2, S=0.6)",
            
            // [12] Opposite side throw cube
            "DD(185, S=0.8) TD(-90) DD(70, S=0.8) O(0.5, S=0.65) FD() DD(-60, S=0.8) TB(-90) TTC() TTC() I(1.2) DT(1, S=0.65)",
            
            // [13] From middle to specifically the left switch
            "TD(45, S=0.78) DD(50) TD(-45, S=0.78) DT(0.35) O(0.4, S=0.8)"
    };

    // Stores the chunks that make up the autonomous sequences
    public static Map<String, String> autonomousFromLayout = new HashMap<>();

    /* COLLECTOR */
    // Values for the collector speeds
    public static double collectorIntakeSpeed = 1.0;
    public static double collectorOuttakeSpeed = -0.3;
    public static double collectorHoldSpeed = 0.3;

    // Should the collector be intaking?
    public static boolean collectorHold = false;

    /* ELEVATOR */
    // Conversion factor for elevator (to distance in inches)
    public static double elevatorEncoderCoefficient = 0.013428226131;

    // The maximum height of the elevator
    public static double elevatorMaximumDistance = 100;

    // Possible saved positions of the elevator
    public static double[] elevatorSetPoints = new double[]{0, 11, 92};

    // Hold speed and the limit above which to hold the elevator (in elevatorMaximumDistance units)
    public static double elevatorHoldSpeed = 0.3;
    public static double minimumElevatorHoldDistance = 3;

    // This is the error tolerated by the ElevatorToHeight command
    public static double elevatorToHeightTolerance = 2;

    // How many times does the elevator reading have to repeat to reset the encoder
    public static int elevatorValueRepetitionCounter = 30;

    /* FOURBAR */
    // Hold the fourbar either up or down
    public static double fourbarHoldUpSpeed = 0.25;
    public static double fourbarHoldDownSpeed = -0.15;

    // The length and speed of the FourbarRaise and FourbarLower commands
    public static double fourbarRaiseSpeed = 0.85;
    public static double fourbarLowerSpeed = -0.4;
    public static double fourbarRaiseLength = 1.5;
    public static double fourbarLowerLength = 0.4;

    // Should the fourbar be held in place now?
    public static boolean holdFourbar = true;

    /* HANGER ARM */
    public static boolean holdHangerArm = false;
    public static double hangerMoveSpeed = 1;
    public static double hangerHoldSpeed = 0.2;

    /* COLLECTOR CLAMP */
    // Is true when set to clamped, and false if unclamped
    public static boolean clamped = true;

    // The lengths and speeds for the ClampIn and ClampOut command
    public static double clampInLength = 0.2;
    public static double clampOutLength = 0.2;
    public static double clampInSpeed = 0.6;
    public static double clampOutSpeed = -0.6;

    // The speed at which to hold the clamp
    public static double clampHoldSpeed = 0.2;

    /* VISION */
    // Variables for the subsystem
    public static int visionPort = 5805;
    public static byte[] piAddress = {10, 13, 91, 12};

    // Length of the information collecting from the RPi
    public static double visionValueCollectionLength = 0.3;

    // The vision does not give the correct angle - this coefficient aims to fix that
    public static double visionCoefficient = 0.5;

    /* MISCELLANEOUS */
    // Switching drive modes
    // 0 is tank drive and 1 is arcade drive with the Logitech controller
    // 2 uses the Y axis and the rotation axis from the joystick controller
    public static int driveMode = 2;

    // Minimal input from the joystick axes (when in default position, they are not always zero
    public static double minimalJoystickAxisInput = 0.1;
}
