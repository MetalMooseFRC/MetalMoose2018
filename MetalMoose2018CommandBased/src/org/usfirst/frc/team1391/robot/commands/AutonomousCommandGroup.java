package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Takes a string consisting of g-code style command series and produces an
 * autonomous. See the markdown documentation for more details.
 */
public class AutonomousCommandGroup extends CommandGroup {

    /**
     * Takes a String in the g-code style language and converts it into commands.
     *
     * @param commandString The command to be parsed.
     */
    public AutonomousCommandGroup(String commandString) {
        // Removes whitespace from both ends of the String and changes all upper case chars to lower case
        // It also separates all individual commands with a newline symbol
        commandString = commandString.trim().toLowerCase().replaceAll("\\)(\\s+)", ")\n");

        // The actual parsing of the command
        if (commandString.length() != 0) parseCommand(commandString, false);
    }

    /**
     * Parses the command String.
     *
     * @param commandString  The String to be parsed.
     * @param reversed Mirrors commands (reverses angles and x axes).
     */
    private void parseCommand(String commandString, boolean reversed) {
        // Split the String into a list of commands
        String[] commandList = commandString.split("\n");

        for (String command : commandList) {

            // Split to individual parameters (on any ", ", or on "(")
            String[] commandParts = command.replaceAll("\\)", "").split("\\(+|((,)[\\s]*)+");


            // Takes the first character of the first argument, so we can explicitly write out the commands
            switch (commandParts[0].charAt(0)) {

                // Move(distance in ft, angle in degrees) - moves or turns the robot
                case 'm': {
                    double distance = Double.parseDouble(commandParts[1]);

                    // If reversed is true, we need to invert the angle
                    double angle = Double.parseDouble(stepParameterValues[2]) * (reversed ? -1 : 1);

                    addSequential(new DriveAutonomous(distance, angle));

                    break;
                }

                // Elevate:(position - 0/1/2) - parallel - raises the elevator to a certain position
                case 'e': {
                    int elevatorPosition = Integer.parseInt(stepParameterValues[1]);

                    /*TODO - finish this command**/
                    //addParallel(new ElevatorMovement(elevatorPosition));

                    break;
                }

                // Intake(time in seconds) - parallel - intakes the cube
                case 'i': {
                    double lengthOfIntake = Double.parseDouble(commandParts[1]);

                    addParallel(new CollectorIntake(lengthOfIntake));
                    break;
                }

                // Outtake(time in seconds) - sequential - outtakes the cube
                case 'o': {
                    double lengthOfOuttake = Double.parseDouble(commandParts[1]);

                    addSequential(new CollectorOuttake(lengthOfOuttake));
                    break;
                }

                // Timeout(time in seconds) - sequential timeout of the drivebase
                case 't': {
                    double lengthOfDrivebaseTimeout = Double.parseDouble(commandParts[1]);

                    addSequential(new DrivebaseTimeout(lengthOfDrivebaseTimeout));
                    break;
                }

                // Goto(x, y) - generates two moves (turn, then drive) to move to the coordinate.
                case 'g': {
                    // If reversed is true, then we need to reverse x (the board is mirrored)
                    double x = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);
                    double y = Double.parseDouble(commandParts[2]);

                    addSequential(new DriveAutonomous(x, y, true));
                    addSequential(new DriveAutonomous(x, y, false));

                    break;
                }

                // -Chunk(number of the chunk) - inverted chunk
                case '-': {
                    int chunkNumber = Integer.parseInt(commandParts[1]);
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, !reversed);
                }

                // Chunk(number of the chunk) - normal chunk
                case 'c': {
                    int chunkNumber = Integer.parseInt(commandParts[1]);
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, reversed);
                }
            }
        }
    }
}
