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
	private boolean isVisionTargetted = false;
	private double visionAngle = 0;

	public VisionSystem() {
		myUDPServer = new UDPServer(RobotMap.visionPort);
	}
	/**
	 * This method should be called at least once per FMS cycle while the vision system is actively being used.
	 */
	public void updateVision() {
		String visionString = myUDPServer.listen();
		if (visionString == "null") {
			isVisionTargetted = false;
			visionAngle = 0;
		} else if (visionString == "timeout") { 
			myUDPServer.close();
			myUDPServer = new UDPServer(RobotMap.visionPort);
			isVisionConnected = false;
		} else {
			isVisionConnected = true;
			visionAngle = Double.parseDouble(visionString);
		}
	}
	
	public void initVision() {
		boolean isSuccess = false;
		isVisionConnected = false;
		while (isSuccess == false) {
			isSuccess = myUDPServer.initSocket();
		}
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new VisionMonitor());
	}
	
	public boolean getIsVisionTargetted() {
		return isVisionTargetted;
	}
	
	public double getVisionAngle() {
		return visionAngle;
	}
	
	public boolean getIsVisionConnected() {
		return isVisionConnected;
	}
}
