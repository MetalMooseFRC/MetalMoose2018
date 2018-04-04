package org.usfirst.frc.team1391.robot;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.io.IOException;

public class UDPClient {

	private DatagramSocket socket;
	private byte[] sendData = new byte[256];
	private byte[] receiveData = new byte[256];
	private int port = RobotMap.visionPort;
	private InetAddress address;
	private byte[] addressBytes = RobotMap.piAddress;

	public UDPClient() {
		
	}

	public boolean initSocket() {
		try {
			address = InetAddress.getByAddress(addressBytes);
			socket = new DatagramSocket(port);
			socket.setSoTimeout(3);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public String listen() {
		DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
		try {
			socket.receive(packet);
			return new String(packet.getData(), 0, packet.getLength());
		} catch (Exception e) {
			System.out.println("failure recieving data from raspberry pi!!");
			System.out.println(e);
			return "timeout";
		}

	}
	
	public boolean query(String query) {
		sendData = receiveData;
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
		try {
			socket.send(sendPacket);
			return true;
		} catch (IOException e) {
			System.out.println("failure sending to raspberry pi!!");
			System.out.println(e);
			return false;
			
		}
	}
	
	public void close() {
		socket.close();
		socket.close();
	}

}
