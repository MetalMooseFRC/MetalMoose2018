package org.usfirst.frc.team1391.robot.commands;

import org.usfirst.frc.team1391.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Takes a string consisting of g-code style command series and produces an
 * autonomous. Each command is separated by a space, and each argument is
 * separated by a colon. Example: "m:10 w:2 c:0"
 */
public class AutonomousCommandGroup extends CommandGroup {

	public AutonomousCommandGroup(SendableChooser<String> autoTypeChooser,
			SendableChooser<String> autoStrategyChooser) {

		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
		RobotMap.autoType = autoTypeChooser.getSelected();
		RobotMap.autoStrategy = autoStrategyChooser.getSelected();
		if (RobotMap.autoType == "Custom") {
			RobotMap.autoCommandString = SmartDashboard.getString("Custom Autonomous String", "");
		} else {
			RobotMap.autoCommandString = RobotMap.prefs
					.getString(RobotMap.autoType + RobotMap.gameData + RobotMap.autoStrategy, "");

			String[] autonStepList = RobotMap.autoCommandString.split(" ");
			for (String step : autonStepList) {
				String[] args = step.split(":");

				switch (args[0]) {

				case "m":
					// addSequential(new
					// DriveDistance(Integer.parseInt(args[1])));
					break;

				case "r":
					// addSequential(new TurnAngle(Integer.parseInt(args[1])));
					break;

				case "e":
					// addParallel(new
					// ElevatorToPosition(Integer.parseInt(args[1])));
					break;

				case "i":
					// addParallel(new
					// CollectCube(Double.parseDouble(args[1])));
					break;
				case "o":
					// addSequential(new
					// IntakeCube(Double.parseDouble(args[1])));
					break;

				case "w":
					// addSequential(new Wait(Double.parseDouble(args[1])));
					break;

				}
			}
		}
	}
}
