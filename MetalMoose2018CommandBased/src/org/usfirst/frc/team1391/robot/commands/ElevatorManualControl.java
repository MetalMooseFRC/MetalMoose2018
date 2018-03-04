package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

public class ElevatorManualControl extends Command {

    public ElevatorManualControl() {
        requires(Robot.myElevator);
    }

    protected void initialize() {

    }

    protected void execute() {
        double joystickSpeed = OI.operatorController.getRawAxis(RobotMap.operatorRightYPort);

        if (joystickSpeed != 0) Robot.myElevator.setThrottledSpeed(joystickSpeed);
        else if (Robot.myElevator.elevatorEncoder.getDistance() > 6) Robot.myElevator.hold();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
