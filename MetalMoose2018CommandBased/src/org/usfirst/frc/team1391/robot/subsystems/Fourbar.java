package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.FourbarManualControl;

/**
 * Controls the drivebase motors.
 */
public class Fourbar extends Subsystem {

    // Speed controller controlling the fourbar
	private Spark motor = new Spark(RobotMap.fourbarMotorPort);

    public Fourbar() {

    }

    public void initDefaultCommand() {
        setDefaultCommand(new FourbarManualControl());
    }
    
    public void setSpeed(double speed) {
    	motor.set(speed);
    }
}
