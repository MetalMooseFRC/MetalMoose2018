package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Turns the robot in autonomous (to a pair of coordinates).
 */
public class DrivetrainTurnToCoordinates extends Command {
    // The goals for the PID.
    private double angle;

    // The angle is calculated at the beginning of the initialization command.
    private double x, y;

    /**
     * Turn the robot to a certain coordinate pair.
     *
     * @param x The x value of the coordinate that we want to turn to.
     * @param y The y value of the coordinate that we want to turn to.
     */
    DrivetrainTurnToCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Reset encoder, set goals for PID, enables PID.
     *
     * Calculate the coordinate pair using an arctan function.
     * To make sure that the angle is optimal, same trick as in DrivetrainTurnToAngle is used.
     */
    protected void initialize() {
        double relativeX = x - RobotMap.robotPositionX;
        double relativeY = y - RobotMap.robotPositionY;

        // The angle of the move that we need to make (from standard position)
        double relativeAngle = Math.toDegrees(Math.atan(relativeY / relativeX));

        // Arctan does not know the difference between the II and the IV quadrant, or the I and the III...
        if (relativeX < 0 && relativeY < 0) relativeAngle -= 180;
        else if (relativeY < 0) relativeAngle += 180;

        // We have to calculate the angle from the position of the robot (and the robot can be in any angle)
        double absoluteAngle = (relativeAngle - RobotMap.absoluteAngle) % 360;

        // To create the optimal turning, we have to optimize any angle above + or - 180 degrees
        if (absoluteAngle > 180) absoluteAngle = -180 + (absoluteAngle % 180);
        else if (absoluteAngle < -180) absoluteAngle = 180 - (absoluteAngle % 180);

        // Finally, set the angle to the angle calculated
        angle = absoluteAngle;

        // Reset the gyro
        Robot.myDrivetrain.myAHRS.reset();

        // Set point, enable gyro PID
        Robot.myDrivetrain.gyroPID.setSetpoint(angle);
        Robot.myDrivetrain.gyroPID.reset();
        Robot.myDrivetrain.gyroPID.enable();
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double pidGyroOutput = Robot.myDrivetrain.gyroPID.get();

        Robot.myDrivetrain.arcadeDrive(0, pidGyroOutput);
    }

    protected boolean isFinished() {
    	if (Double.isNaN(angle)) return true;

        // If we are under gyroStopAtError
        if (Robot.myDrivetrain.gyroPID.onTarget()) {
            // Update the absolute angle of the robot
            RobotMap.absoluteAngle += Robot.myDrivetrain.myAHRS.getAngle();

            return true;
        } else return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
