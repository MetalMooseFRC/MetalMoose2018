package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (by an angle).
 */
public class VisionMonitor extends Command {
	int counter = 0;
	

    /**
     * Turn the robot to the nearest cube.
     */
   public VisionMonitor() {
        requires(Robot.myVisionSystemClient);
    }


    /**
     * Resets encoder and gyro, set goals for PID, enables PID.
     */
    protected void initialize() {
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
    		if (timeSinceInitialized() > counter * 0.1) {
    			counter++;
    			
    			Robot.myVisionSystemClient.updateVision();
    		}

    }

    /**
     * Finished when it hits the gyroPID target
     */
    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
