package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Takes a string consisting of g-code style command series and produces an
 * autonomous. Each command is separated by a space, and each argument of the
 * command is separated by a colon.
 */
public class AutonomousCommandGroup extends CommandGroup {

    /**
     * Takes a String in the g-code style language and converts it into commands.
     *
     * @param command The command to be parsed.
     */
    public AutonomousCommandGroup(String command) {
        // Removes whitespace from both ends of the String and changes all upper case chars to lower case
        command = command.trim().toLowerCase();

        // The actual parsing of the command
        parseCommand(command, false);
    }

    /**
     * Parses the command String.
     *
     * @param command  The String to be parsed.
     * @param reversed Mirrors the angles, so there is no need for duplicate autonomodes
     */
    private void parseCommand(String command, boolean reversed) {

        String[] stepList = command.split(" ");

        for (String step : stepList) {

            // Split to individual parameters
            String[] stepParameterValues = step.split(":");

            // Takes the first character of the first argument, so we can explicitly write out the commands
            switch (stepParameterValues[0].charAt(0)) {

                // Move:(distance in ft):(angle in degrees) - moves or turns the robot
                case 'm': {
                    double distance = Double.parseDouble(stepParameterValues[1]);

                    // If reversed is true, we need to invert the angle
                    double angle = Double.parseDouble(stepParameterValues[2]) * (reversed ? -1 : 1);

                    addSequential(new DriveAutonomous(distance, angle));

                    break;
                }

                // Elevate:(position - 0/1/2) - parallel - raises the elevator to a certain position
                case 'e': {
                    int elevatorPosition = Integer.parseInt(stepParameterValues[1]);

                    //Uncomment this when we actually have a function that does this
                    //addParallel(new ElevatorMovement(elevatorPosition));
                    break;
                }

                // Intake:(time in seconds) - parallel - intakes the cube
                case 'i': {
                    double lengthOfIntake = Double.parseDouble(stepParameterValues[1]);

                    addParallel(new CollectorIntake(lengthOfIntake));
                    break;
                }

                // Outtake:(time in seconds) - sequential - outtakes the cube
                case 'o': {
                    double lengthOfOuttake = Double.parseDouble(stepParameterValues[1]);

                    addSequential(new CollectorOuttake(lengthOfOuttake));
                    break;
                }

                // Timeout:(time in seconds) - sequential - times out the robot
                case 't': {
                    double lengthOfDrivebaseTimeout = Double.parseDouble(stepParameterValues[1]);

                    addSequential(new DrivebaseTimeout(lengthOfDrivebaseTimeout));
                    break;
                }

                // Goto:(x coordinate):(y coordinate) - generates two moves (turn
                // and movement) to move to a certain coordinate on the board.
                case 'g': {
                    // If reversed is true, then we need to reverse x (the board is mirrored)
                    double x = Double.parseDouble(stepParameterValues[1]) * (reversed ? -1 : 1);
                    double y = Double.parseDouble(stepParameterValues[2]);

                    double relativeX = x - RobotMap.robotPositionX;
                    double relativeY = y - RobotMap.robotPositionY;

                    // The angle of the move that we need to make
                    double relativeAngle = Math.toDegrees(Math.atan(relativeY / relativeX));

                    // We have to calculate the angle from the position of the robot
                    double absoluteAngle = relativeAngle - RobotMap.absoluteAngle;

                    // Pythagorean on the triangle
                    double relativeDistance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);

                    // Create two move commands (first turn, then drive)
                    addSequential(new DriveAutonomous(0, absoluteAngle));
                    addSequential(new DriveAutonomous(relativeDistance, 0));

                    break;
                }

                // -Chunk:(number of the chunk)
                case '-': {
                    int chunkNumber = Integer.parseInt(stepParameterValues[1]);
                    String subcommand = RobotMap.chunks[chunkNumber];

                    // Invert the reverse variable - this is an inverted chunk
                    parseCommand(subcommand, !reversed);
                }

                // Chunk:(number of the chunk) - recursively calls this function to process the chunk and issue the commands
                case 'c': {
                    int chunkNumber = Integer.parseInt(stepParameterValues[1]);
                    String subcommand = RobotMap.chunks[chunkNumber];

                    // Pass the reversed variable - we are not inverting
                    parseCommand(subcommand, reversed);
                }
            }
        }
    }
}
