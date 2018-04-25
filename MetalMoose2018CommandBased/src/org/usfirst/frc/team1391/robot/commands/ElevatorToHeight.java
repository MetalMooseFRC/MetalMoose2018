package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1391.robot.Robot;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Moves the elevator to a certain position.
 */
public class ElevatorToHeight extends Command {
    // The starting and ending position of the elevator (if it needs to go to a certain point)
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

    protected void initialize() {
    }

    /**
     * Keep moving towards the endPosition (using the throttled function)
     */
    protected void execute() {
        double currentPosition = Robot.myElevator.elevatorEncoder.getDistance();

        // Where do we want to go (if positive, go up; if negative, go down)
        // Signum returns either +1 or -1 (that is what we want the speed to be
        double speed = Math.signum(endPosition - currentPosition);

        Robot.myElevator.setThrottledSpeed(speed);
    }

    /**
     * Finishes only when it's close to the end (see elevatorToHeightTolerance)
     */
    protected boolean isFinished() {
        return Math.abs(Robot.myElevator.elevatorEncoder.getDistance() - endPosition) < RobotMap.elevatorToHeightTolerance;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
