package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Clamps out using the collector motor.
 */
public class ClampOut extends Command {

    public ClampOut() {
        requires(Robot.myClamp);
    }

    /**
     * Sets timeout for clamp out length.
     */
    protected void initialize() {
        setTimeout(RobotMap.clampOutLength);
    }

    /**
     * Repeatedly set the clamp motor to the clamp out speed.
     */
    protected void execute() {
        Robot.myClamp.setSpeed(RobotMap.clampOutSpeed);
    }

    /**
     * Returns true when the command times out.
     */
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
