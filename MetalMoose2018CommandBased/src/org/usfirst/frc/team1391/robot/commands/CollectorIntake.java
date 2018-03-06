package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or through autonomous).
 */
public class CollectorIntake extends Command {

    // Length of the autonomous command
    private double time = -1;

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
     * If the command is autonomous, sets timeout.
     */
    protected void initialize() {
        //  Set a timeout only if the time was initialized to something
        if (time > 0) setTimeout(time);
    }

    /**
     * Sets speed, defined by the collectorIntakeSpeed constant.
     */
    protected void execute() {
        Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorIntakeSpeed);
    }

    /**
     * Returns true, only if the time is non-zero and isTimedOut() is true.
     */
    protected boolean isFinished() {
        return time > 0 && isTimedOut();
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
