package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Outtakes (either manually using a button, or through autonomous).
 */
public class CollectorOuttake extends Command {
    // Length of the autonomous command
    private double time = 0;

    /**
     * Constructor for teleop.
     */
    public CollectorOuttake() {
        requires(Robot.myCollector);
    }

    /**
     * Constructor for autonomous.
     *
     * @param time The length of the CollectorIntake command.
     */
    CollectorOuttake(double time) {
        requires(Robot.myCollector);

        this.time = time;
    }

    /**
     * If the command is autonomous, sets timeout.
     */
    protected void initialize() {
    	RobotMap.holdCollector = false;
    	
        //  Set a timeout only if the time was initialized to something
        if (time > 0) setTimeout(time);
    }

    /**
     * Sets speed, defined by the collectorIntakeSpeed constant.
     */
    protected void execute() {
        Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorOuttakeSpeed);
    }

    /**
     * Returns true if isTimedOut() is true.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {}

    protected void interrupted() {}
}
