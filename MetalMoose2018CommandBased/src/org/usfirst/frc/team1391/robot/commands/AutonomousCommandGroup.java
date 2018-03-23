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
        String[] commandList = commandString.trim().replaceAll("[a-z]", "").split("\\) *");

        for (String command : commandList) {
            // Split to individual parameters (on any ",", or on "(")
            String[] commandParts = command.trim().split(" *\\( *| *, *");

            // Optional parameter variables
            double speed = 0;
            String mode = "S";

            // Get the values of all optional parameters
            for (int i = 1; i < commandParts.length; i++) {
                if (commandParts[i].contains("=")) {
                    String[] optionalCommandParts = commandParts[i].split(" *= *");

                    // Which optional parameter is it?
                    switch (optionalCommandParts[0]) {
                        case "S": {
                            speed = Double.parseDouble(optionalCommandParts[1]);
                            break;
                        }
                        case "M": {
                            mode = optionalCommandParts[1];
                            break;
                        }
                    }
                }
            }

            switch (commandParts[0]) {
                // Elevate(position) - parallel - moves the elevator into a set position
                case "E": {
                    int elevatorPosition = Integer.parseInt(commandParts[1]);

                    if (mode.equals("S")) addSequential(new ElevatorToHeight(elevatorPosition));
                    else if (mode.equals("P")) addParallel(new ElevatorToHeight(elevatorPosition));

                    break;
                }

                // Outtake(time in seconds) - sequential - outtakes the cube
                case "O": {
                    double lengthOfOuttake = Double.parseDouble(commandParts[1]);

                    if (speed == 0) addSequential(new CollectorOuttake(lengthOfOuttake));
                    else addSequential(new CollectorOuttake(lengthOfOuttake, -speed));

                    break;
                }

                // Timeout(time in seconds) - sequential - timeout of the drivetrain
                case "T": {
                    double lengthOfDrivebaseTimeout = Double.parseDouble(commandParts[1]);

                    addSequential(new DrivetrainTimeout(lengthOfDrivebaseTimeout));
                    break;
                }

                // TurnDrive(angle in degrees) - sequential - turn and drive at the same time
                case "TD": {
                    double angle = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);

                    if (speed != 0) {
                        if (mode.equals("S")) addSequential(new DrivetrainTurnDrive(angle, speed));
                        else if (mode.equals("P")) addParallel(new DrivetrainTurnDrive(angle, speed));
                    } else {
                        if (mode.equals("S")) addSequential(new DrivetrainTurnDrive(angle));
                        else if (mode.equals("P")) addParallel(new DrivetrainTurnDrive(angle));
                    }

                    break;
                }

                // TurnBy(angle in degrees) - sequential - turn by an angle
                case "TB": {
                    // If reversed is true, we need to invert the angle
                    double angle = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);

                    if (speed != 0) {
                        if (mode.equals("S")) addSequential(new DrivetrainTurnByAngle(angle, speed));
                        else if (mode.equals("P")) addParallel(new DrivetrainTurnByAngle(angle, speed));
                    } else {
                        if (mode.equals("S")) addSequential(new DrivetrainTurnByAngle(angle));
                        else if (mode.equals("P")) addParallel(new DrivetrainTurnByAngle(angle));
                    }

                    break;
                }

                // Drive(distance in in) - sequential - drive
                case "DD": {
                    double distance = Double.parseDouble(commandParts[1]);

                    if (speed != 0) {
                        if (mode.equals("S")) addSequential(new DrivetrainDriveDistance(distance, speed));
                        else if (mode.equals("P")) addParallel(new DrivetrainDriveDistance(distance, speed));
                    } else {
                        if (mode.equals("S")) addSequential(new DrivetrainDriveDistance(distance));
                        else if (mode.equals("P")) addParallel(new DrivetrainDriveDistance(distance));
                    }

                    break;
                }

                // DriveTime(time, speed) - sequential - drive for a certain period of time at a certain speed
                case "DT": {
                    double time = Double.parseDouble(commandParts[1]);

                    if (speed != 0) {
                        if (mode.equals("S")) addSequential(new DrivetrainDriveTime(time, speed));
                        else if (mode.equals("P")) addParallel(new DrivetrainDriveTime(time, speed));
                    } else {
                        if (mode.equals("S")) addSequential(new DrivetrainDriveTime(time));
                        else if (mode.equals("P")) addParallel(new DrivetrainDriveTime(time));
                    }

                    break;
                }

                // Chunk(number of the chunk) - normal chunk
                case "C": {
                    int chunkNumber = Integer.parseInt(commandParts[1]);
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, reversed);
                    
                    break;
                }

                // -Chunk(number of the chunk) - inverted chunk
                case "-C": {
                    int chunkNumber = Integer.parseInt(commandParts[1]);
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, !reversed);
                    
                    break;
                }
            }
        }
    }
}
