package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.ClampManualControl;

/**
 * Controls the clamp motor.
 */
public class Clamp extends Subsystem {
	
	// The speed controller controlling the clamp
    private VictorSP motor = new VictorSP(RobotMap.clampMotorPort);

    public Clamp() {
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ClampManualControl());
    }

    /**
     * Sets speed of the clamp motor.
     * 
     * @param speed The speed to the clamp motor to.
     */
    public void setSpeed(double speed) {
    	motor.setSpeed(speed);
    }
}
