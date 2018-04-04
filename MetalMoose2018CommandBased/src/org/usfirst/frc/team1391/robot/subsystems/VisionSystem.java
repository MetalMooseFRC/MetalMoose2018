package org.usfirst.frc.team1391.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1391.robot.RobotMap;
import org.usfirst.frc.team1391.robot.UDPServer;
import org.usfirst.frc.team1391.robot.commands.VisionMonitor;

/**
 * Interfaces the UDP Server with the rest of robot code
 */
public class VisionSystem extends Subsystem {
	private UDPServer myUDPServer;
	private boolean isVisionConnected = false;
	private boolean isVisionTargeted = false;
	private double visionAngle = 0;

	public VisionSystem() {
		myUDPServer = new UDPServer(RobotMap.visionPort);
	}
	/**
	 * This method should be called at least once per FMS cycle while the vision system is actively being used.
	 */
	public void updateVision() {
		String visionString = myUDPServer.listen();

		switch (visionString) {
			case "null":
				isVisionTargeted = false;
				visionAngle = 0;
				break;
			case "timeout":
				myUDPServer.close();
				myUDPServer = new UDPServer(RobotMap.visionPort);
				isVisionConnected = false;
				break;
			default:
				isVisionConnected = true;
				visionAngle = Double.parseDouble(visionString);
				break;
		}
	}
	
	public void initVision() {
		boolean isSuccess = false;
		isVisionConnected = false;
		while (!isSuccess) isSuccess = myUDPServer.initSocket();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new VisionMonitor());
	}
	
	public boolean getIsVisionTargetted() {
		return isVisionTargeted;
	}
	
	public double getVisionAngle() {
		return visionAngle;
	}
	
	public boolean getIsVisionConnected() {
		return isVisionConnected;
	}
}
