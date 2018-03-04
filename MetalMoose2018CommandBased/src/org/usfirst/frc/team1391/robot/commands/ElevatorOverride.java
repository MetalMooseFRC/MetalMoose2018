package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

public class ElevatorOverride extends Command {

    public ElevatorOverride() {
        requires(Robot.myElevator);
    }

    protected void initialize() {

    }

    protected void execute() {
        double stickInput = OI.operatorController.getRawAxis(RobotMap.operatorLeftYPort);

        Robot.myElevator.setAbsoluteSpeed(stickInput);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
