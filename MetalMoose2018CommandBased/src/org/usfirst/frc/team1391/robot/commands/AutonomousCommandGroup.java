package org.usfirst.frc.team1391.robot.commands;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Takes a string consisting of g-code style command series and produces an
 * autonomous. Each command is separated by a space, and each argument is
 * separated by a colon. Example: "m:10 w:2 c:0"
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
					int distance = Integer.parseInt(stepValues[1]);
					int angle = ((reverseAngle) ? (-1) : (1)) * Integer.parseInt(stepValues[2]);
					
					addSequential(new DriveAutonomous(distance, angle));
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
