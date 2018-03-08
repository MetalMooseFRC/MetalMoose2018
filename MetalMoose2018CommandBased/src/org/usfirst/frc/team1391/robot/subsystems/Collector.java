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
    // Speed controllers for the motors of the collector
    private VictorSP collectorLeftMotor = new VictorSP(RobotMap.collectorLeftMotorPort);
    private VictorSP collectorRightMotor = new VictorSP(RobotMap.collectorRightMotorPort);
    private SpeedControllerGroup collectorMotors = new SpeedControllerGroup(collectorLeftMotor, collectorRightMotor);

    public Collector() {
        collectorRightMotor.setInverted(true);
    }

    public void setAbsoluteSpeed(double speed) {
        collectorMotors.set(speed);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CollectorManualControl());
    }
}
