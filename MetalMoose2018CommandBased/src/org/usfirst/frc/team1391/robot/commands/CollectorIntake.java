package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button).
 */
public class CollectorIntake extends Command {
	// Length of the timeout from an autonomous constructor
	double time = 0;

	// Speed of the intake
    double speed = 0;

    /**
     * Constructor for teleop.
     */
    public CollectorIntake() {
        requires(Robot.myCollector);
    }

    /**
     * Constructor for autonomous.
     *
     * @param time The length of the CollectorIntake command.
     */
    CollectorIntake(double time) {
        requires(Robot.myCollector);

        this.time = time;
    }

    /**
     * Constructor for autonomous with speed as an optional parameter.
     *
     * @param time  The length of the CollectorIntake command.
     * @param speed The speed at which to outtake.
     */
    CollectorIntake(double time, double speed) {
        requires(Robot.myCollector);

        this.time = time;
        this.speed = speed;
    }

    /**
     * Sets intakeWithCollector to true (we want to hold the cube after intaking).
     */
    protected void initialize() {
        if (time != 0) setTimeout(time);
    }

    /**
     * Sets speed, defined by the collectorIntakeSpeed constant.
     */
    protected void execute() {
        if (speed == 0) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorIntakeSpeed);
        else Robot.myCollector.setAbsoluteSpeed(speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        RobotMap.intakeWithCollector = true;
        Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorHoldSpeed);
    }

    protected void interrupted() {
        RobotMap.intakeWithCollector = true;
        Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorHoldSpeed);
    }
}
