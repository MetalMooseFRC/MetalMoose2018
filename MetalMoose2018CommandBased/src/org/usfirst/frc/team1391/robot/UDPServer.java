package org.usfirst.frc.team1391.robot;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	private DatagramSocket socket;
	private byte[] buf = new byte[256];
	private int port;

	public UDPServer(int portnum) {
		port = portnum;
	}

	public boolean initSocket() {
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(1000);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String listen() {
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		try {
			socket.receive(packet);
			String received = new String(packet.getData(), 0, packet.getLength());
			return received;
		} catch (Exception e) {
			return "timeout";
		}

	}

	public void close() {
		socket.close();
	}

}