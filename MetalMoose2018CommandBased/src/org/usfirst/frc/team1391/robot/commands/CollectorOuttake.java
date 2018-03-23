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

    // Speed of the outtake (for autonomous)
    private double speed = 0;

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
     * Constructor for autonomous with speed as an optional parameter.
     *
     * @param time  The length of the CollectorIntake command.
     * @param speed The speed at which to outtake.
     */
    CollectorOuttake(double time, double speed) {
        requires(Robot.myCollector);

        this.time = time;
        this.speed = speed;
    }

    /**
     * If the command is autonomous, sets timeout.
     * Also sets the intakeWithCollector boolean to false - we just spat out the cube, we don't need to hold it
     */
    protected void initialize() {
        RobotMap.intakeWithCollector = false;

        //  Set a timeout only if the time was initialized
        if (time > 0) setTimeout(time);
    }

    /**
     * Sets speed, defined either by the collectorIntakeSpeed constant or by the speed variable.
     */
    protected void execute() {
        if (speed == 0) Robot.myCollector.setAbsoluteSpeed(RobotMap.collectorOuttakeSpeed);
        else Robot.myCollector.setAbsoluteSpeed(speed);
    }

    /**
     * Finishes when isTimedOut() is true.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
