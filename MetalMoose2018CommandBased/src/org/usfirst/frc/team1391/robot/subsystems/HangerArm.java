package org.usfirst.frc.team1391.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.commands.HangerArmManualControl;

/**
 * Controls the hanger arm motor.
 */
public class HangerArm extends Subsystem {
    
	// The speed controller controlling the hanger arm
    private Spark motor = new Spark(RobotMap.hangerArmMotorPort);

    public HangerArm() {
    }

    public void initDefaultCommand() {
        setDefaultCommand(new HangerArmManualControl());
    }

    /**
     * Sets speed of the hanger arm motor.
     *
     * @param speed The speed for the motor to be set to.
     */
    public void setSpeed(double speed) {
        motor.set(speed);
    }
}
