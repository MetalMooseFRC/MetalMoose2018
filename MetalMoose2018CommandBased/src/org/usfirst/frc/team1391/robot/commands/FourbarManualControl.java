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
        	Robot.myFourbar.setSpeed(yAxisSpeed);
            
            return;
        }
        
        // Else just hold (either up or down)
        if (RobotMap.holdFourbar) Robot.myFourbar.holdUp();
        else Robot.myFourbar.holdDown();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
