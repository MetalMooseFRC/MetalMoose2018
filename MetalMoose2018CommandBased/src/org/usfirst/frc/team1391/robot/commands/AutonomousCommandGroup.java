package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1391.robot.RobotMap;

/**
 * Takes an autonomous command String and produces an autonomous
 * robot sequence. See the markdown documentation for more details.
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
     * @param commandString The String to be parsed.
     * @param reversed      Mirrors commands (inverts angles).
     */
    private void parseCommand(String commandString, boolean reversed) {
        // Removes whitespace from both ends of the String, removes all lower case alphabetical characters
        // After that, splits into different commands
        String[] commandList = commandString.trim().replaceAll("[a-z]", "").split("\\) *");

        for (String command : commandList) {
            // Split to individual parameters (on any ",", or on "(")
            String[] commandParts = command.trim().split(" *\\( *| *, *");

            // Optional parameter variables
            double speed = 0;
            String mode = "";

            // Get the values of all optional parameters
            for (int i = 1; i < commandParts.length; i++) {
                // If it contains a "=", it has to be an optional parameter
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
                // Elevate(position) - parallel - moves the elevator into position
                case "E": {
                    int elevatorPosition = Integer.parseInt(commandParts[1]);

                    switch (mode) {
                        case "S":
                            addSequential(new ElevatorToHeight(elevatorPosition));
                            break;
                        case "P":
                            addParallel(new ElevatorToHeight(elevatorPosition));
                            break;
                        default:
                            addSequential(new ElevatorToHeight(elevatorPosition));
                            break;
                    }

                    break;
                }

                // Outtake(time in seconds) - sequential - outtakes the cube
                case "O": {
                    double lengthOfOuttake = Double.parseDouble(commandParts[1]);

                    // The '-' symbol is because intake is actually negative value for the collector
                    if (speed == 0) addSequential(new CollectorOuttake(lengthOfOuttake));
                    else addSequential(new CollectorOuttake(lengthOfOuttake, -speed));

                    break;
                }

                // Intake(time in seconds) - parallel - intakes the cube
                case "I": {
                    double lengthOfIntake = Double.parseDouble(commandParts[1]);

                    if (speed == 0) addParallel(new CollectorIntake(lengthOfIntake));
                    else addParallel(new CollectorIntake(lengthOfIntake, speed));

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

                    if (speed == 0) {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainTurnDrive(angle));
                                break;
                            case "P":
                                addParallel(new DrivetrainTurnDrive(angle));
                                break;
                            default:
                                addSequential(new DrivetrainTurnDrive(angle));
                                break;
                        }
                    } else {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainTurnDrive(angle, speed));
                                break;
                            case "P":
                                addParallel(new DrivetrainTurnDrive(angle, speed));
                                break;
                            default:
                                addSequential(new DrivetrainTurnDrive(angle, speed));
                                break;
                        }
                    }

                    break;
                }

                // TurnBy(angle in degrees) - sequential - turn by an angle
                case "TB": {
                    double angle = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);

                    if (speed == 0) {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainTurnByAngle(angle));
                                break;
                            case "P":
                                addParallel(new DrivetrainTurnByAngle(angle));
                                break;
                            default:
                                addSequential(new DrivetrainTurnByAngle(angle));
                                break;
                        }
                    } else {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainTurnByAngle(angle, speed));
                                break;
                            case "P":
                                addParallel(new DrivetrainTurnByAngle(angle, speed));
                                break;
                            default:
                                addSequential(new DrivetrainTurnByAngle(angle, speed));
                                break;
                        }
                    }

                    break;
                }

                // TurnToCube() - sequential - turn to the nearest cube using vision
                case "TTC": {
                    if (speed == 0) {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainTurnToCube());
                                break;
                            case "P":
                                addParallel(new DrivetrainTurnToCube());
                                break;
                            default:
                                addSequential(new DrivetrainTurnToCube());
                                break;
                        }
                    } else {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainTurnToCube(speed));
                                break;
                            case "P":
                                addParallel(new DrivetrainTurnToCube(speed));
                                break;
                            default:
                                addSequential(new DrivetrainTurnToCube(speed));
                                break;
                        }
                    }

                    break;
                }

                // Drive(distance in in) - sequential - drive
                case "DD": {
                    double distance = Double.parseDouble(commandParts[1]);

                    if (speed == 0) {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainDriveDistance(distance));
                                break;
                            case "P":
                                addParallel(new DrivetrainDriveDistance(distance));
                                break;
                            default:
                                addSequential(new DrivetrainDriveDistance(distance));
                                break;
                        }
                    } else {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainDriveDistance(distance, speed));
                                break;
                            case "P":
                                addParallel(new DrivetrainDriveDistance(distance, speed));
                                break;
                            default:
                                addSequential(new DrivetrainDriveDistance(distance, speed));
                                break;
                        }
                    }

                    break;
                }

                // DriveTime(time, speed) - sequential - drive for a certain period of time at a certain speed
                case "DT": {
                    double time = Double.parseDouble(commandParts[1]);

                    if (speed == 0) {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainDriveTime(time));
                                break;
                            case "P":
                                addParallel(new DrivetrainDriveTime(time));
                                break;
                            default:
                                addSequential(new DrivetrainDriveTime(time));
                                break;
                        }
                    } else {
                        switch (mode) {
                            case "S":
                                addSequential(new DrivetrainDriveTime(time, speed));
                                break;
                            case "P":
                                addParallel(new DrivetrainDriveTime(time, speed));
                                break;
                            default:
                                addSequential(new DrivetrainDriveTime(time, speed));
                                break;
                        }
                    }

                    break;
                }

                // FourbarRaise() / FourbarUp() - sequential - raise the fourbar
                case "FR":
                case "FU": {
                    switch (mode) {
                        case "S":
                            addSequential(new FourbarRaise());
                            break;
                        case "P":
                            addParallel(new FourbarRaise());
                            break;
                        default:
                            addSequential(new FourbarRaise());
                            break;
                    }

                    break;
                }

                // FourbarLower() / FourbarDown() - sequential - lower the fourbar
                case "FL":
                case "FD": {
                    switch (mode) {
                        case "S":
                            addSequential(new FourbarLower());
                            break;
                        case "P":
                            addParallel(new FourbarLower());
                            break;
                        default:
                            addSequential(new FourbarLower());
                            break;
                    }
                    break;
                }

                // ClampIn() - sequential - clamps in the collector
                case "CI": {
                	addSequential(new ClampIn());
                	
                	break;
                }

                // ClampOut() - sequential - clamps out the collector
                case "CO": {
                	addSequential(new ClampOut());
                	
                	break;
                }
                
                // Chunk(chunk number) - recursively generates the commands depending on the chunk
                case "C": {
                    int chunkNumber = Integer.parseInt(commandParts[1]);
                    String chunk = RobotMap.chunks[chunkNumber];

                    parseCommand(chunk, reversed);

                    break;
                }

                // -Chunk(chunk number) - recursively generates the inverted commands depending on the chunk
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
