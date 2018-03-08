package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class CollectorIntake extends Command {
    // Speed during autonomous. If it changes to something else than 0, do
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
        // Hold the cube after this command is over
    	RobotMap.holdCollector = true;
    }

    /**
     * Sets speed, defined by the collectorIntakeSpeed constant.
     */
    protected void execute() {
    	// If autonomousSpeed is zero, this is not called by autonomous, so we will control using collectorIntakeSpeed
        if (autonomousSpeed == 0) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorIntakeSpeed);
        else Robot.myCollector.setAbsoluteSpeed(autonomousSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
