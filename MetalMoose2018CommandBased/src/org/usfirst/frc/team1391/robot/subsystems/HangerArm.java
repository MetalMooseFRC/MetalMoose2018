package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.CollectorManualControl;
import org.usfirst.frc.team1391.robot.commands.HangerArmManualControl;

/**
 * Controls the collector motors.
 */
public class HangerArm extends Subsystem {
    private Spark motor = new Spark(8);

    public HangerArm() {
    }

    public void setSpeed(double speed) {
    	motor.set(speed);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new HangerArmManualControl());
    }
}
