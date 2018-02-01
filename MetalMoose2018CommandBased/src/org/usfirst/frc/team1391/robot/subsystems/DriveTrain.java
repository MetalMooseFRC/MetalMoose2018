package org.usfirst.frc.team1391.robot.subsystems;

//import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;

/**
 *
 */
public class DriveTrain extends Subsystem {

	Spark leftMotor = new Spark(RobotMap.leftMotorPort);
	Spark rightMotor = new Spark(RobotMap.rightMotorPort);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	DifferentialDrive myDrive = new DifferentialDrive(leftMotor,rightMotor);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void arcadeDrive(double left, double right) {
    		myDrive.arcadeDrive(left,right);
    }
}

