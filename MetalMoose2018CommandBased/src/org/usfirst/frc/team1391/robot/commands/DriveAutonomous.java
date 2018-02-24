package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot.
 */
public class DriveAutonomous extends Command {
	// The goals for the PID.
    private double distance, angle;
    
    // Information for the GOTO command to calculate the distance and the angle
    // This needs to be calculated in the initialization of the command
    private double x, y;
    private boolean calculate, calculateAngle;

    // Counts repetitions of the goal value.
    private int onTargetCounter = 0;
    private int onTargetCounterGoal = 15;

    // Counts repetitions of the same value.
    private int repeatCounter = 0;
    private int repeatCounterGoal = 5;
    private double previousReading = 0;

    // Drive the robot to distance (in inches), or angle (in degrees).
    // Either of the values has to equal zero for the move to be executed properly.
    DriveAutonomous(double distance, double angle) {
        this.distance = distance;
        this.angle = angle;
    }
    
    // For GOTO command: it has to calculate when it arives at the command
    DriveAutonomous(double x, double y, boolean calculateAngle) {
    	this.x = x;
    	this.y = y;
    	this.calculateAngle = calculateAngle;
    	this.calculate = true;
    }

    /**
     * Reset encoder, set goals for PID, enables PID
     */
    protected void initialize() {
    	if (calculate) {
            double relativeX = x - RobotMap.robotPositionX;
            double relativeY = y - RobotMap.robotPositionY;
            
            if (calculateAngle) {
            	// The angle of the move that we need to make (from standard position)
                double relativeAngle = Math.toDegrees(Math.atan(relativeY / relativeX));

                // Arctan does not know the difference between the II and the IV quadrant, or the I and the III...
                if (relativeX < 0 && relativeY < 0) relativeAngle -= 180.0;
                else if (relativeX < 0) relativeAngle -= 90.0;
                
                // We have to calculate the angle from the position of the robot
                double absoluteAngle = relativeAngle - RobotMap.absoluteAngle;
                
                angle = absoluteAngle;
            } else distance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);
    	}
    	
        // Reset the sensors
        Robot.myDriveTrain.myEncoder.reset();
        Robot.myDriveTrain.myAHRS.reset();

        // Set point, reset and enable encoder PID
        Robot.myDriveTrain.encoderPID.setSetpoint(distance);
        Robot.myDriveTrain.encoderPID.reset();
        Robot.myDriveTrain.encoderPID.enable();

        // Set point, enable gyro PID
        Robot.myDriveTrain.gyroPID.setSetpoint(angle);
        Robot.myDriveTrain.gyroPID.reset();
        Robot.myDriveTrain.gyroPID.enable();
    }

    /**
     * Keeps re-adjusting the motors, depending on the output of PID
     */
    protected void execute() {
        double pidEncoderOutput = Robot.myDriveTrain.encoderPID.get();
        double pidGyroOutput = Robot.myDriveTrain.gyroPID.get();

        // If we are turning, disregard the encoder output
        if (distance == 0) pidEncoderOutput = 0;

        // For tuning PID and debugging
        System.out.println("Encoder error " + Robot.myDriveTrain.encoderPID.getError());
        System.out.println("Gyro error " + Robot.myDriveTrain.gyroPID.getError());

        Robot.myDriveTrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
    }

    protected boolean isFinished() {
        // Evaluating the angle
        if (distance == 0) {
            // If we are on target, add one
            if (Robot.myDriveTrain.gyroPID.onTarget()) onTargetCounter++;
            else onTargetCounter = 0;

            // If we are reading the same PID value as before,
            if (previousReading == Robot.myDriveTrain.myAHRS.getAngle()) repeatCounter++;
            else repeatCounter = 0;

            // Reading the angle as previous reading (distance is 0)
            previousReading = Robot.myDriveTrain.myAHRS.getAngle();
        }

        // Evaluating the distance
        else {
            if (Robot.myDriveTrain.encoderPID.onTarget()) onTargetCounter++;
            else onTargetCounter = 0;

            if (previousReading == Robot.myDriveTrain.myEncoder.getDistance()) repeatCounter++;
            else repeatCounter = 0;

            // Reading the distance as previous reading (angle is 0)
            previousReading = Robot.myDriveTrain.myEncoder.getDistance();
        }

        if (onTargetCounter == onTargetCounterGoal || repeatCounter == repeatCounterGoal) {

            // If we just turned, adjust the absolute angle of the robot
        	// The '-' sign is there because the robot thinks turning to the right is positive (although it is negative)
            if (distance == 0) RobotMap.absoluteAngle += Robot.myDriveTrain.myAHRS.getAngle();

            // If we drove, adjust the X and Y coordinates accordingly
            else {
                RobotMap.robotPositionX += Math.sin(Math.toRadians(RobotMap.absoluteAngle)) * Robot.myDriveTrain.myEncoder.getDistance();
                RobotMap.robotPositionY += Math.cos(Math.toRadians(RobotMap.absoluteAngle)) * Robot.myDriveTrain.myEncoder.getDistance();
            }
            return true;
        } else return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
