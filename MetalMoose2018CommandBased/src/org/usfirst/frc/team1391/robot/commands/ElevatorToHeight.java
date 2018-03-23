package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Moves the elevator to a certain position.
 */
public class ElevatorToHeight extends Command {
    // The starting and ending position of the elevator (if it needs to go to a certain point)
    private double startPosition;
    private double endPosition;

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
     * Set the starting position.
     */
    protected void initialize() {
        // We are starting where the elevator currently is (at the initialization of this command)
        startPosition = Robot.myElevator.elevatorEncoder.getDistance();
    }

    /**
     * Keep moving towards the endPosition (using the throttled function)
     */
    protected void execute() {
        double currentPosition = Robot.myElevator.elevatorEncoder.getDistance();

        // Where do we want to go (if positive, go up; if negative, go down)
        // Signum returns either +1 or -1 (that is what we want the speed to be
        double speed = Math.signum(endPosition - currentPosition);

        Robot.myElevator.setThrottledSpeed(speed, startPosition, endPosition);
    }

    // Only once we hit the target do we stop
    protected boolean isFinished() {
        // If we are close to the target
        return Math.abs(Robot.myElevator.elevatorEncoder.getDistance() - endPosition) < RobotMap.elevatorToHeightTolerance;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
