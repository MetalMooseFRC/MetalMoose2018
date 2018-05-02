/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1391.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team1391.robot.commands.*;

/**
 * Connect physical operator interface to commands.
 */
public class OI {

    // Drive team controllers
    public static final Joystick driveStick = new Joystick(RobotMap.driverControllerPort),
            operatorController = new Joystick(RobotMap.operatorControllerPort);

    // Driver control buttons
    public static final Button reverseDriveButton = new JoystickButton(driveStick, RobotMap.reverseDriveButtonPort),
            throttleDriveButton = new JoystickButton(driveStick, RobotMap.throttleDriveButtonPort),
            fourbarButton = new JoystickButton(driveStick, RobotMap.fourbarButtonPort),
            topButton1 = new JoystickButton(driveStick, RobotMap.fourbarUpButton1),
            topButton2 = new JoystickButton(driveStick, RobotMap.fourbarUpButton2),
            topButton3 = new JoystickButton(driveStick, RobotMap.fourbarUpButton3),
            topButton4 = new JoystickButton(driveStick, RobotMap.fourbarUpButton4);

    // Operator control buttons
    public static final Button operatorA = new JoystickButton(operatorController, RobotMap.operatorButtonAPort),
            operatorB = new JoystickButton(operatorController, RobotMap.operatorButtonBPort),
            operatorX = new JoystickButton(operatorController, RobotMap.operatorButtonXPort),
            operatorY = new JoystickButton(operatorController, RobotMap.operatorButtonYPort),
            operatorLB = new JoystickButton(operatorController, RobotMap.operatorButtonLBPort),
            operatorRB = new JoystickButton(operatorController, RobotMap.operatorButtonRBPort),
            operatorBack = new JoystickButton(operatorController, RobotMap.operatorButtonEndPort),
            operatorStart = new JoystickButton(operatorController, RobotMap.operatorButtonStartPort);

    // Map commands to sticks stick
    OI() {
        // Controls the collector
        operatorLB.whileHeld(new CollectorIntake());
        operatorRB.whileHeld(new CollectorOuttake());

        // Controls the fourbar
        fourbarButton.whenPressed(new FourbarLower());
        fourbarButton.whenReleased(new FourbarRaise());
    }
}
