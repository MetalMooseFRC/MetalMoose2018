package org.usfirst.frc.team1391.robot.subsystems;

import org.usfirst.frc.team1391.robot.UDPClient;
import org.usfirst.frc.team1391.robot.commands.VisionMonitor;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Interfaces the UDP Server with the rest of robot code
 */
public class VisionSystemClient extends Subsystem{
	private UDPClient myUDPClient;
	private boolean isVisionConnected = false;
	private boolean isVisionTargetted = false;
	private double visionAngle = 0;

	public VisionSystemClient() {
		myUDPClient = new UDPClient();
	}
	/**
	 * This method should be called at least once per FMS cycle while the vision system is actively being used.
	 */
	public void updateVision() {
		myUDPClient.query("q");
		String visionString = myUDPClient.listen();
		if (visionString == "timeout") { 
			System.out.println("timeout");
			isVisionConnected = false;
		} else {
			isVisionConnected = true;
			try {
				visionAngle = Double.parseDouble(visionString);
			} catch (Exception e) {
				isVisionTargetted = false;
				visionAngle = 0;
				System.out.println(visionAngle);
				System.out.println(e);
				System.out.println(visionString);
			}
		}
	}
	
	public void initVision() {
		boolean isSuccess = false;
		isVisionConnected = false;
		while (isSuccess == false) {
			isSuccess = myUDPClient.initSocket();
		}
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
	
	public void reset() {
		myUDPClient.close();
		myUDPClient = new UDPClient();
		initVision();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new VisionMonitor());
	}
}

