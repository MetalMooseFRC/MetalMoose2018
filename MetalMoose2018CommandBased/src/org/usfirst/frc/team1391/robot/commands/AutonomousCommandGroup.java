package org.usfirst.frc.team1391.robot.commands;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Takes a string consisting of g-code style command series and produces an
 * autonomous. Each command is separated by a space, and each argument of the
 * command is separated by a colon.
 */
public class AutonomousCommandGroup extends CommandGroup {

	public AutonomousCommandGroup(String command) {
		parseCommand(command, false);
	}

	private void parseCommand(String command, boolean reverseAngle) {
		String[] stepList = command.split(" ");

		for (String step : stepList) {
			String[] stepValues = step.split(":");

			switch (stepValues[0]) {
				case "m": {
					double distance = Double.parseDouble(stepValues[1]);
					double angle = ((reverseAngle) ? (-1) : (1)) * Double.parseDouble(stepValues[2]);

					if (distance == 0) addSequential(new DriveAutonomous(distance, angle), 2);
					else addSequential(new DriveAutonomous(distance, angle));
					break;
				}
	
				case "f": {
					int chunkNumber = Integer.parseInt(stepValues[1]);
					int reverseNumber = Integer.parseInt(stepValues[2]);
	
					parseCommand(RobotMap.chunks[chunkNumber], reverseNumber == 1);
				}
			}
		}
	}
}
