package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Raise the fourbar.
 */
public class FourbarRaise extends Command {

    public FourbarRaise() {
        requires(Robot.myFourbar);
    }

    /**
     * Initializes the command only when the elevator is down.
     */
    protected void initialize() {
        if (Robot.myElevator.elevatorEncoder.getDistance() < RobotMap.minimumElevatorHoldDistance) {
            setTimeout(RobotMap.fourbarRaiseLength);
            RobotMap.holdFourbar = true;
        } else setTimeout(0);
    }

    /**
     * Keeps raising the fourbar.
     */
    protected void execute() {
        Robot.myFourbar.setSpeed(RobotMap.fourbarRaiseSpeed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
