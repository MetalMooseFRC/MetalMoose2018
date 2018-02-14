package org.usfirst.frc.team1391.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Takes a string consisting of g-code style command series and produces an
 * autonomous. Each command is separated by a space, and each argument is
 * separated by a colon. Example: "m:10 w:2 c:0"
 */
public class AutonomousCommandGroup extends CommandGroup {

	public AutonomousCommandGroup() {
		String autonString = SmartDashboard.getString("AutoString", "");
		String[] autonStepList = autonString.split(" ");
		for (String step : autonStepList) {
			String[] args = step.split(":");

			switch (args[0]) {

			case "m":
				// addSequential(new DriveDistance(Integer.parseInt(args[1])));
				break;

			case "r":
				// addSequential(new TurnAngle(Integer.parseInt(args[1])));
				break;

			case "e":
				// addParallel(new
				// ElevatorToPosition(Integer.parseInt(args[1])));
				break;

			case "i":
				// addParallel(new CollectCube(Double.parseDouble(args[1])));
				break;
			case "o":
				// addSequential(new IntakeCube(Double.parseDouble(args[1])));
				break;

			case "w":
				// addSequential(new Wait(Double.parseDouble(args[1])));
				break;

			}
		}

	}
}
