package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually controls the elevator (with absolute values).
 */
public class ElevatorOverride extends Command {

    public ElevatorOverride() {
        requires(Robot.myElevator);
    }

    protected void initialize() {}

    /**
     * Repeatedly sets the absolute speed of the elevator.
     */
    protected void execute() {
        double joystickInput = OI.operatorController.getRawAxis(RobotMap.operatorLeftYPort);

        Robot.myElevator.setAbsoluteSpeed(joystickInput);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
