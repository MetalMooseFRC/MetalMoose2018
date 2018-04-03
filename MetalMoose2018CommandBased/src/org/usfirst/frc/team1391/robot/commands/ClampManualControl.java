package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually controls the clamp.
 */
public class ClampManualControl extends Command {

    public ClampManualControl() {
        requires(Robot.myClamp);
    }

    protected void initialize() {
    }

    protected void execute() {
        // If we are intaking with the collector, but not clamp yet, clamp (and cancel this command)
        if (RobotMap.collectorHold && !RobotMap.clamped) {
            RobotMap.clamped = true;
            new ClampIn().start();
            this.cancel();
        }

        // If we are not intaking with the collector but clamped, unclamp (and cancel this command)
        else if (!RobotMap.collectorHold && RobotMap.clamped) {
            RobotMap.clamped = false;
            new ClampOut().start();
            this.cancel();
        }

        // If we are just clamped, clamp
        else if (RobotMap.clamped) Robot.myClamp.clamp();

            // Else just set the motor to 0
        else Robot.myClamp.setSpeed(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
