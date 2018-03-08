package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Moves the elevator to a certain position.
 */
public class ElevatorToHeight extends Command {
    // The ending position of the elevator (if it needs to go to a certain point)
    private double endPosition;

    // The shift and the coefficient to properly squish and move the function (if it needs to go to a certain point)
    private double shift;
    private double coefficient;

    /**
     * Go to the elevatorSetPoints[endPosition] height.
     *
     * @param endPosition The position of the value from the elevatorSetPoints array to which to go.
     */
    public ElevatorToHeight(int endPosition) {
        requires(Robot.myElevator);

        // Picks the saved values for the setPoints of the elevator from RobotMap
        this.endPosition = RobotMap.elevatorSetPoints[endPosition];
    }

    /**
     * Set the coefficient and shift.
     */
    protected void initialize() {
        // We are starting where the elevator currently is
        double startPosition = Robot.myElevator.elevatorEncoder.getDistance();

        // We want to basically map the values of the elevatorEncoder from startPosition to endPosition onto the main function
        // That is why the coefficient is going to be bigger than one (total length / fraction length)
        coefficient = RobotMap.elevatorMaximumDistance / (endPosition - startPosition);

        // After squishing by the coefficient, shift so it matches the midpoint between start and end
        shift = (startPosition * coefficient);
    }

    /**
     * Keep moving towards the endPosition
     */
    protected void execute() {
        double currentPosition = Robot.myElevator.elevatorEncoder.getDistance();

        // Where do we want to go (if positive, go up; if negative, go down)
        int direction = (int)Math.signum(endPosition - currentPosition);
        
        // Get the value after shift and direction alteration and set it to absolute speed
        Robot.myElevator.setAbsoluteSpeed(direction * Robot.myElevator.getThrottledSpeed(currentPosition * coefficient - shift));
    }

    // Only once we hit the target do we stop
    protected boolean isFinished() {
        // If we are close to the target
        if (Math.abs(Robot.myElevator.elevatorEncoder.getDistance() - endPosition) < 0.1) return true;
        else return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
