package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Drives the robot in autonomous.
 */
public class DriveAutonomous extends Command {
    // The goals for the PID.
    private double distance, angle;

    // Information from the goto command.
    // The distance and angle are calculated at the beginning of the initialization command.
    private double x, y;
    private boolean gotoCalculation, calculateAngle;

    // Counts repetitions of the goal value (to make sure it really stayed on the hit goal).
    private int onTargetCounter = 0;
    private int onTargetCounterGoal = 15;

    // Counts repetitions of the same value (to see if we are not stuck).
    private int repeatCounter = 0;
    private int repeatCounterGoal = 5;
    private double previousReading = 0;

    /**
     * Default autonomous constructor.
     *
     * Drive the robot to distance (in inches), or angle (in degrees).
     * Either of the values has to equal zero for the move to be executed properly
     * (due to the fact, that the encoder is only on one side of the robot).
     *
     * @param distance Distance to be driven (in inches).
     * @param angle Angle to be turned (in degrees).
     */
    DriveAutonomous(double distance, double angle) {
        this.distance = distance;
        this.angle = angle;
    }

    /**
     * Constructor for the G command.
     *
     * Saves the absolute values of where the robot should be and does the calculation when this command is initialized.
     *
     * @param x The goal x coordinate of the robot.
     * @param y The goal y coordinate of the robot.
     * @param calculateAngle Should it calculate the angle? If not, it will calculate the distance.
     */
    DriveAutonomous(double x, double y, boolean calculateAngle) {
        this.x = x;
        this.y = y;
        this.calculateAngle = calculateAngle;

        // We need to let the initialize function know what it has to calculate the distance when it's called.
        this.gotoCalculation = true;
    }

    /**
     * Reset encoder, set goals for PID, enables PID.
     *
     * If the doGotoCalculation is true, calculate the distance from the absolute x and y coordinates.
     */
    protected void initialize() {
        if (gotoCalculation) {
            double relativeX = x - RobotMap.robotPositionX;
            double relativeY = y - RobotMap.robotPositionY;

            if (calculateAngle) {
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
     * Keeps re-adjusting the motors, depending on the output of PID.
     */
    protected void execute() {
        double pidEncoderOutput = Robot.myDriveTrain.encoderPID.get();
        double pidGyroOutput = Robot.myDriveTrain.gyroPID.get();

        // If we are turning, disregard the encoder output (since we only have one encoder on the drivebase)
        if (distance == 0) pidEncoderOutput = 0;

        // For tuning PID and debugging
        System.out.println("Encoder error " + Robot.myDriveTrain.encoderPID.getError());
        System.out.println("Gyro error " + Robot.myDriveTrain.gyroPID.getError());

        Robot.myDriveTrain.arcadeDrive(pidEncoderOutput, pidGyroOutput);
    }

    protected boolean isFinished() {
    	if (Double.isNaN(distance) || Double.isNaN(angle)) return true;
    	
        // Evaluating the angle
        if (distance == 0) {
            // If we are on target, add one
            if (Robot.myDriveTrain.gyroPID.onTarget()) onTargetCounter++;
            else onTargetCounter = 0;

            // If we are reading the same PID value as before, add one to repeat counter
            if (previousReading == Robot.myDriveTrain.myAHRS.getAngle()) repeatCounter++;
            else repeatCounter = 0;

            // Saving the angle as a previous reading
            previousReading = Robot.myDriveTrain.myAHRS.getAngle();
        } else {
            if (Robot.myDriveTrain.encoderPID.onTarget()) onTargetCounter++;
            else onTargetCounter = 0;

            if (previousReading == Robot.myDriveTrain.myEncoder.getDistance()) repeatCounter++;
            else repeatCounter = 0;

            // Reading the distance as previous reading
            previousReading = Robot.myDriveTrain.myEncoder.getDistance();
        }

        // If we are either on-target or stuck in the position
        if (onTargetCounter == onTargetCounterGoal || repeatCounter == repeatCounterGoal) {
            // If we just turned, update the absolute angle of the robot
            if (distance == 0) RobotMap.absoluteAngle += Robot.myDriveTrain.myAHRS.getAngle();
            else {
                // If we drove, adjust the X and Y coordinates accordingly
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
