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
        String[] commandList = commandString.trim().replaceAll("\\) *", ")\n").spliqt("\n");

        for (String command : commandList) {
            // Split to individual parameters (on any ",", or on "(")
            String[] commandParts = command.replaceAll("\\)", "").trim().split("\\( *| *, *");

            // Takes the first character of the first argument (all of the commands have differing first letters)
            switch (commandParts[0].replaceAll("[a-z]", "")) {
                // Elevate(position) - parallel - moves the elevator into a set position
                case "E": {
                    int elevatorPosition = Integer.parseInt(commandParts[1]);

                    addSequential(new ElevatorToHeight(elevatorPosition));

                    break;
                }

                // Intake(mode) - parallel - 1 is intake with speed set 1, 0 is intake with hold speed)
                case "I": {
                    int intakeMode = Integer.parseInt(commandParts[1]);

                    addParallel(new CollectorIntake());
                    break;
                }

                // Outtake(time in seconds) - sequential - outtakes the cube
                case "O": {
                    double lengthOfOuttake = Double.parseDouble(commandParts[1]);

                    addSequential(new CollectorOuttake(lengthOfOuttake));
                    break;
                }

                // FourbarUp() - parallel - moves the fourbar up
                case "FU": {
                    addSequential(new FourbarRaise(RobotMap.fourbarRaiseLength));
                    break;
                }

                // FourbarDown() - parallel - moves the fourbar down
                case "FD": {
                    addSequential(new FourbarLower(RobotMap.fourbarLowerLength));
                    break;
                }

                // Timeout(time in seconds) - sequential - timeout of the drivetrain
                case "T": {
                    double lengthOfDrivebaseTimeout = Double.parseDouble(commandParts[1]);

                    addSequential(new DrivetrainTimeout(lengthOfDrivebaseTimeout));
                    break;
                }

                // Goto(x, y, intake=mode, stopFromGoal=distance) - generates two moves (turnby, then drive) to move to the coordinate.
                // intake is an optional parameter. If it is true, the robot will start intaking when it starts driving to the coordinates
                // stopFromGoal is an optional parameter. If it is true, the robot will start intaking before moving
                case "G": {
                    // If reversed is true, then we need to reverse x (the board is mirrored)
                    double x = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);
                    double y = Double.parseDouble(commandParts[2]);

                    // The values here are arbitrary - just to check whether they changed later
                    double stopFromGoalDistance = 0;
                    boolean intakeMode = false;

                    // If the command has optional parameters
                    for (int i = 3; i < commandParts.length; i++) {
                        String[] optionalCommandParts = commandParts[i].split(" *= *");

                        switch (optionalCommandParts[0]) {
                            case "intake":
                                intakeMode = Boolean.parseBoolean(optionalCommandParts[1]);
                                break;

                            case "stopFromGoal":
                                stopFromGoalDistance = Double.parseDouble(optionalCommandParts[1]);
                                break;
                        }
                    }

                    // The turning part of the goto
                    addSequential(new DrivetrainTurnToCoordinates(x, y));

                    // If we do intake during the goto
                    if (intakeMode) addParallel(new CollectorIntake());

                    // The moving part of the goto
                    if (stopFromGoalDistance != 0) addSequential(new DrivetrainDriveToCoordinates(x, y, stopFromGoalDistance));
                    else addSequential(new DrivetrainDriveToCoordinates(x, y));

                    break;
                }

                // TurnTo(angle in degrees) - sequential - turn to an angle
                case "TT": {
                    double angle = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);

                    addSequential(new DrivetrainTurnToAngle(angle));

                    break;
                }

                // TurnBy(angle in degrees) - sequential - turn by an angle
                case "TB": {
                    // If reversed is true, we need to invert the angle
                    double angle = Double.parseDouble(commandParts[1]) * (reversed ? -1 : 1);

                    addSequential(new DrivetrainTurnByAngle(angle));

                    break;
                }

                // Drive(distance in in) - sequential - drive
                case "D": {
                    double distance = Double.parseDouble(commandParts[1]);

                    addSequential(new DrivetrainDrive(distance));

                    break;
                }

                // DriveTime(time, speed) - sequential - drive for a certain period of time at a certain speed
                case "DT": {
                    double distance = Double.parseDouble(commandParts[1]);
                    double speed = Double.parseDouble(commandParts[2]);

                    addSequential(new DrivetrainDrive(distance, speed));

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
