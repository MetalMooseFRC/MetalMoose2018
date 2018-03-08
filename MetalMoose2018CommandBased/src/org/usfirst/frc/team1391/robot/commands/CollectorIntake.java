package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class CollectorIntake extends Command {
    /**
     * Constructor for teleop.
     */
    public CollectorIntake() {
        requires(Robot.myCollector);
    }
    
    protected void initialize() {
        // Hold the cube after this command is over
    	RobotMap.intakeWithCollector = true;
    }

    /**
     * Sets speed, defined by the collectorIntakeSpeed constant.
     */
    protected void execute() {
    	// If autonomousSpeed is zero, this is not called by autonomous, so we will control using collectorIntakeSpeed
        Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorIntakeSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
