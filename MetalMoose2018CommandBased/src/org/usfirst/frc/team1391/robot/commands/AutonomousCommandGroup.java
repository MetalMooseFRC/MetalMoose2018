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
        // Removes whitespace from both ends of the String and changes all upper case chars to lower case
        // the .replaceAll and .split are for splitting into separate commands
        String[] commandList = commandString.trim().toLowerCase().replaceAll("\\)(\\s*)", ")\n").split("\n");

        for (String command : commandList) {
            // Split to individual parameters (on any ", ", or on "(")
            String[] commandParts = command.replaceAll("\\)", "").split("\\(+|((,)[\\s]*)+");

            // This array stores the values of the parameters of the command
            // The -1 is because the first part of the command is the command itself
            double[] commandParameterValues = new double[commandParts.length - 1];
            for (int i = 1; i < commandParts.length; i++) commandParameterValues[i-1] = Double.parseDouble(commandParts[i]);

            // Takes the first character of the first argument (all of the commands have differing first letters)
            switch (commandParts[0].charAt(0)) {
                // Move(distance in ft, angle in degrees) - moves or turns the robot
                case 'm': {
                    double distance = commandParameterValues[0];

                    // If reversed is true, we need to invert the angle
                    double angle = commandParameterValues[1] * (reversed ? -1 : 1);

                    addSequential(new DriveAutonomous(distance, angle));

                    break;
                }

                // Elevate(position) - parallel - moves the elevator into a set position
                case 'e': {
                    int elevatorPosition = (int)commandParameterValues[0];

                    addSequential(new ElevatorToHeight(elevatorPosition));

                    break;
                }

                // Intake(mode - 1 is intake with speed = 1, 0 is intake with hold speed) - parallel - intakes 
                case 'i': {
                    int intakeMode = (int)commandParameterValues[0];

                    addParallel(new CollectorIntake(intakeMode));
                    break;
                }

                // Outtake(time in seconds) - sequential - outtakes the cube
                case 'o': {
                    double lengthOfOuttake = commandParameterValues[0];

                    addSequential(new CollectorOuttake(lengthOfOuttake));
                    break;
                }

                // Timeout(time in seconds) - sequential timeout of the drivebase
                case 't': {
                    double lengthOfDrivebaseTimeout = commandParameterValues[0];

                    addSequential(new DrivebaseTimeout(lengthOfDrivebaseTimeout));
                    break;
                }

                // Goto(x, y) - generates two moves (turn, then drive) to move to the coordinate.
                case 'g': {
                    // If reversed is true, then we need to reverse x (the board is mirrored)
                    double x = commandParameterValues[0] * (reversed ? -1 : 1);
                    double y = commandParameterValues[1];

                    addSequential(new DriveAutonomous(x, y, true));
                    addSequential(new DriveAutonomous(x, y, false));

                    break;
                }

                // Angle(angle in degrees) - turn to a certain angle
                case 'a': {
                    double angle = commandParameterValues[0] * (reversed ? -1 : 1);

                    addSequential(new DriveAutonomous(angle));

                    break;
                }

                // Chunk(number of the chunk) - normal chunk
                case 'c': {
                    int chunkNumber = (int)commandParameterValues[0];
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, reversed);
                    
                    break;
                }

                // -Chunk(number of the chunk) - inverted chunk
                case '-': {
                    int chunkNumber = (int)commandParameterValues[0];
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, !reversed);
                    
                    break;
                }
            }
        }
    }
}
