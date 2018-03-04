package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

public class ElevatorToHeight extends Command {
    double setpoint;

    public ElevatorToHeight(double setpoint) {
        requires(Robot.myElevator);

        this.setpoint = setpoint;
    }

    protected void initialize() {

    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
