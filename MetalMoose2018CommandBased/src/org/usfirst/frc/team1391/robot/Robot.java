/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1391.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team1391.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
    // Create subsystem objects
    public static final Drivetrain myDrivetrain = new Drivetrain();
    public static final Collector myCollector = new Collector();
    public static final Elevator myElevator = new Elevator();
    public static final Fourbar myFourbar = new Fourbar();
    public static final Hanger myHanger = new Hanger();
    public static final HangerArm myHangerArm = new HangerArm();
    public static final Clamp myClamp = new Clamp();

    // Create the OI
    public static final OI myOI = new OI();

    // Create SmartDashboard objects (drive mode selection, autonomous position selection)
    private SendableChooser<Integer> driveModeChooser = new SendableChooser<>();
    private SendableChooser<String> autonomousPositionChooser = new SendableChooser<>();

    // Declare the autonomous command group
    private AutonomousCommandGroup myAutonomousCommand;

    /**
     * Initial setup of the robot - puts values on SmartDashboard.
     */
    @Override
    public void robotInit() {
        // Which DriveMode to use in the teleop
        driveModeChooser.addDefault("Joystick Arcade Drive", 2);
        driveModeChooser.addObject("Tank Drive", 1);
        driveModeChooser.addObject("Arcade Drive", 0);
        SmartDashboard.putData("Drive Mode", driveModeChooser);

        // Autonomous position chooser
        autonomousPositionChooser.addDefault("Left", "Left");
        autonomousPositionChooser.addObject("Middle", "Middle");
        autonomousPositionChooser.addObject("Right", "Right");
        SmartDashboard.putData("Autonomous Position", autonomousPositionChooser);

        // Speed for Autonomous
        SmartDashboard.putNumber("Autonomous Default Turning Speed", RobotMap.autonomousDefaultTurningSpeed);
        SmartDashboard.putNumber("Autonomous Default Driving Speed", RobotMap.autonomousDefaultDrivingSpeed);

        // Orientation of the hanger arm
        SmartDashboard.putNumber("Hanger Arm Orientation", RobotMap.hangerArmOrientation);

        // A custom autonomous command String
        SmartDashboard.putString("Custom Autonomous Command", "");

        // PID values
        SmartDashboard.putNumber("Gyro P", RobotMap.drivetrainGyroP);
        SmartDashboard.putNumber("Gyro I", RobotMap.drivetrainGyroI);
        SmartDashboard.putNumber("Gyro D", RobotMap.drivetrainGyroD);

        SmartDashboard.putNumber("Encoder P", RobotMap.drivetrainEncoderP);
        SmartDashboard.putNumber("Encoder I", RobotMap.drivetrainEncoderI);
        SmartDashboard.putNumber("Encoder D", RobotMap.drivetrainEncoderD);

        // Status of the scheduler and the subsystems
        SmartDashboard.putData(Scheduler.getInstance());
        SmartDashboard.putData(myDrivetrain);
        SmartDashboard.putData(myCollector);
        SmartDashboard.putData(myElevator);
        SmartDashboard.putData(myFourbar);
        SmartDashboard.putData(myHanger);
        SmartDashboard.putData(myHangerArm);
        SmartDashboard.putData(myClamp);

        // The chunks
        for (int i = 0; i < RobotMap.chunks.length; i++) SmartDashboard.putString("Chunk " + i, RobotMap.chunks[i]);

        // The chunk layouts
        for (int i = 0; i < RobotMap.chunkLayout.length; i++) SmartDashboard.putString(RobotMap.chunkLayout[i][0], RobotMap.chunkLayout[i][1]);
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Get data from the DriverStation (layout) and SmartDashboard (robot position) and produce a command String.
     */
    @Override
    public void autonomousInit() {
        // Get the PID values
        RobotMap.drivetrainGyroP = SmartDashboard.getNumber("Gyro P", 0);
        RobotMap.drivetrainGyroI = SmartDashboard.getNumber("Gyro I", 0);
        RobotMap.drivetrainGyroD = SmartDashboard.getNumber("Gyro D", 0);

        RobotMap.drivetrainEncoderP = SmartDashboard.getNumber("Encoder P", 0);
        RobotMap.drivetrainEncoderI = SmartDashboard.getNumber("Encoder I", 0);
        RobotMap.drivetrainEncoderD = SmartDashboard.getNumber("Encoder D", 0);

        Robot.myDrivetrain.gyroPID.setPID(RobotMap.drivetrainGyroP, RobotMap.drivetrainGyroI, RobotMap.drivetrainGyroD);
        Robot.myDrivetrain.encoderPID.setPID(RobotMap.drivetrainEncoderP, RobotMap.drivetrainEncoderI, RobotMap.drivetrainEncoderD);

        /* AUTONOMOUS **/
        // Update chunks
        for (int i = 0; i < RobotMap.chunks.length; i++) RobotMap.chunks[i] = SmartDashboard.getString("Chunk " + i, RobotMap.chunks[i]);

        // Update chunk layouts
        for (int i = 0; i < RobotMap.chunkLayout.length; i++) RobotMap.chunkLayout[i][1] = SmartDashboard.getString(RobotMap.chunkLayout[i][0], RobotMap.chunkLayout[i][1]);

        //Get the chunk layouts
        for (int i = 0; i < RobotMap.chunkLayout.length; i++) RobotMap.autonomousFromLayout.put(RobotMap.chunkLayout[i][0], RobotMap.chunkLayout[i][1]);

        // Get the autonomous speeds
        RobotMap.autonomousDefaultTurningSpeed = SmartDashboard.getNumber("Autonomous Default Turning Speed", RobotMap.autonomousDefaultTurningSpeed);
        RobotMap.autonomousDefaultDrivingSpeed = SmartDashboard.getNumber("Autonomous Default Driving Speed", RobotMap.autonomousDefaultDrivingSpeed);

        // Set the PID output ranges (dependent on the autonomous)
        myDrivetrain.encoderPID.setOutputRange(-RobotMap.autonomousDefaultDrivingSpeed, RobotMap.autonomousDefaultDrivingSpeed);
        myDrivetrain.gyroPID.setOutputRange(-RobotMap.autonomousDefaultTurningSpeed, RobotMap.autonomousDefaultTurningSpeed);

        // Start of with both intaking a cube and holding the fourbar
        RobotMap.holdFourbar = true;
        RobotMap.intakeWithCollector = true;

        // Position of the robot from smartDashboard and the field layout from the driverstation
        String robotPosition = autonomousPositionChooser.getSelected();
        String fieldLayout = DriverStation.getInstance().getGameSpecificMessage();

        // The key to look for in the autonomousFromLayout map
        String commandString = RobotMap.autonomousFromLayout.get(robotPosition + fieldLayout);

        // The custom robot String from the SmartDashboard
        String customCommandString = SmartDashboard.getString("Custom Autonomous Command", "");

        // Custom String always overrides
        if (!customCommandString.equals("")) commandString = customCommandString;

        // If we are sending nothing to the robot as autonomous, that would be a problem.
        // This at least moves past the line (moves forward for a while)
        if (commandString == null || commandString.length() == 0) commandString = RobotMap.chunks[1];

        // Start the autonomous command
        myAutonomousCommand = new AutonomousCommandGroup(commandString);
        myAutonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Cancel the autonomous command (if it's still going) and get the drivemode from the SmartDashboard
     */
    @Override
    public void teleopInit() {
        if (myAutonomousCommand != null) myAutonomousCommand.cancel();

        // Get DriveMode from SmartDashBoard
        RobotMap.driveMode = driveModeChooser.getSelected();

        // Get the orientation of the arm
        RobotMap.hangerArmOrientation = (int)SmartDashboard.getNumber("Hanger Arm Orientation", RobotMap.hangerArmOrientation);
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {

    }
}
