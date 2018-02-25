package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.CollectorManualControl;

/**
 * Controls the collector motors.
 */
public class Collector extends Subsystem {
    private VictorSP collectorLeftMotor = new VictorSP(RobotMap.collectorLeftMotorPort);
    private VictorSP collectorRightMotor = new VictorSP(RobotMap.collectorRightMotorPort);
    private SpeedControllerGroup collectorMotors = new SpeedControllerGroup(collectorLeftMotor, collectorRightMotor);

    public Collector() {
        collectorLeftMotor.setInverted(true);
    }

    public void setSpeed(double speed) {
        collectorMotors.set(speed);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CollectorManualControl());
    }
}
