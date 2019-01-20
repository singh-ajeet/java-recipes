package org.ajeet.sample.net.multicasting;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server2 {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String MCAST_ADDR = "224.2.2.3";
		int DEST_PORT = 7777;
		int BUFFER_LENGTH = 1024;
		
		
		byte[] b = new byte[BUFFER_LENGTH];
		DatagramPacket dgram = new DatagramPacket(b, b.length);
		
		//DatagramSocket socket = new DatagramSocket(DEST_PORT);
		
		MulticastSocket socket = new MulticastSocket(DEST_PORT); // must bind receive side
		socket.joinGroup(InetAddress.getByName(MCAST_ADDR));

		
		while(true) {
			socket.receive(dgram); // blocks until a datagram is received
			String msg = new String(b, 0, dgram.getLength());
			System.err.println("Server 2 Received " + dgram.getLength() +  " data " + msg + " bytes from " + dgram.getAddress());
			dgram.setLength(b.length); // must reset length field!
		}
	}

}
