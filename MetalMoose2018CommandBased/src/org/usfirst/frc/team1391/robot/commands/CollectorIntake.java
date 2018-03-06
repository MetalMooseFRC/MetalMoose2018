package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class CollectorIntake extends Command {
	double autonomousSpeed = 0;
	
    /**
     * Constructor for teleop.
     */
    public CollectorIntake() {
        requires(Robot.myCollector);
    }
    
    /**
     * Constructor for teleop.
     */
    public CollectorIntake(int mode) {
    	if (mode == 1) autonomousSpeed = 1;
    	else autonomousSpeed = RobotMap.collectorHoldSpeed;
    	
        requires(Robot.myCollector);
    }    
    
    protected void initialize() {

    }

    /**
     * Sets speed, defined by the collectorIntakeSpeed constant.
     */
    protected void execute() {
    	// If speed is zero, this is not called by autonomous
        if (autonomousSpeed == 0) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorIntakeSpeed);
        else Robot.myCollector.setAbsoluteSpeed(autonomousSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
