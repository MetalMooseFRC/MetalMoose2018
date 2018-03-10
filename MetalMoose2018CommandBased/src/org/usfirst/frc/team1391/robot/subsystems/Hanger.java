package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.FourbarManualControl;
import org.usfirst.frc.team1391.robot.commands.HangerManualControl;

/**
 * Controls the hanger.
 */
public class Hanger extends Subsystem {

    // Speed controller controlling the fourbar
	private Spark motor = new Spark(RobotMap.hangerMotorPort);

    public Hanger() {

    }

    public void initDefaultCommand() {
        setDefaultCommand(new HangerManualControl());
    }
    
    public void setSpeed(double speed) {
    	motor.set(speed);
    }
}
