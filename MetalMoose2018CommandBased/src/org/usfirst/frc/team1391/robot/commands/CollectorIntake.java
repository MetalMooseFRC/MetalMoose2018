package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Intakes (either manually using a button, or during autonomous).
 */
public class CollectorIntake extends Command {
    // Length of the timeout, set by the autonomous constructor
    private double time = 0;

    // Speed of the intake, set by the autonomous constructor
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
     * If time is set to anything, set timeout.
     */
    protected void initialize() {
        if (time != 0) setTimeout(time);

        RobotMap.collectorHold = false;
    }

    /**
     * Repeatedly sets speed, defined by the collectorIntakeSpeed constant.
     * <p>
     * Alternatively, if the autonomous constructor set speed to anything, set motor to that.
     */
    protected void execute() {
        if (speed == 0) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorIntakeSpeed);
        else Robot.myCollector.setAbsoluteSpeed(speed);
    }

    /**
     * Returns true when the command times out.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    /**
     * If ends, start holding with the collector.
     */
    protected void end() {
        RobotMap.collectorHold = true;
        Robot.myCollector.hold();
    }

    /**
     * If is interrupted, start holding with the collector.
     */
    protected void interrupted() {
        RobotMap.collectorHold = true;
        Robot.myCollector.hold();
    }
}
