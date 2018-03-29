package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.ClampManualControl;
import org.usfirst.frc.team1391.robot.commands.CollectorManualControl;

/**
 * Controls the clamp motor.
 */
public class Clamp extends Subsystem {
    private VictorSP motor = new VictorSP(RobotMap.clampMotorPort);

    public Clamp() {
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ClampManualControl());
    }
    
    public void setSpeed(double speed) {
    	motor.setSpeed(speed);
    }
}
