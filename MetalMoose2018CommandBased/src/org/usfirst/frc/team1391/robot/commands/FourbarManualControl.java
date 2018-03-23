package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.OI;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Manually control the fourbar.
 * <p>
 * If the elevator goes up and the fourbar is down, forces the fourbar to go up and times out the command.
 */
public class FourbarManualControl extends Command {
    // If the elevator goes up, did we set a timeout for the command.
    boolean wasTimeoutSet = false;

    /**
     * Teleop constructor.
     */
    public FourbarManualControl() {
        requires(Robot.myFourbar);
    }

    protected void initialize() {
    }

    /**
     * Manually controls the fourbar (either on override, or just hold either up or down).
     * If elevator goes up and fourbar is down, essentially serves as a FourbarRaise command.
     */
    protected void execute() {
        // The '-' sign is there because the controller axes are reversed (forward is -1)
        double yAxisSpeed = -OI.operatorController.getRawAxis(RobotMap.operatorRightYPort);

        // Override
        if (OI.operatorB.get()) {
            // Either set the fourbar to the axis value (if it is above the hold threshold), or just hold
            if (Math.abs(yAxisSpeed) > RobotMap.elevatorHoldSpeed) {
                // If it is smaller than zero (it just went down) so we don't want to hold it anymore
                // If we just went up with the fourbar, we want to start holding it
                if (yAxisSpeed < 0) RobotMap.holdFourbar = false;
                else if (yAxisSpeed > 0) RobotMap.holdFourbar = true;

                Robot.myFourbar.setSpeed(yAxisSpeed);
            }
        } else if (Robot.myElevator.elevatorEncoder.getDistance() > RobotMap.minimumElevatorHoldDistance && (!RobotMap.holdFourbar || wasTimeoutSet)) {
            // If the elevator is up, and the fourbar is either down or we are just raising it
            Robot.myFourbar.setSpeed(RobotMap.fourbarRaiseSpeed);

            // The first time that this is triggered sets the timeout and holdFourbar
            if (!wasTimeoutSet) {
                setTimeout(RobotMap.fourbarRaiseLength);
                wasTimeoutSet = true;
                RobotMap.holdFourbar = true;
            }
        }

        // Hold
        else if (RobotMap.holdFourbar) Robot.myFourbar.setSpeed(RobotMap.fourbarHoldUpSpeed);
        else Robot.myFourbar.setSpeed(RobotMap.fourbarHoldDownSpeed);


        System.out.println(RobotMap.holdFourbar);
    }

    protected boolean isFinished() {
        if (isTimedOut()) {
            wasTimeoutSet = false;
            RobotMap.holdFourbar = true;
            setTimeout(5000); // Please don't judge me it works ok
            return true;
        }
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
