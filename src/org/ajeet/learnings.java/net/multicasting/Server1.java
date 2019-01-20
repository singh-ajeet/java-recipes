package org.ajeet.sample.net.multicasting;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server1 {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String MCAST_ADDR = "224.2.2.3";
		int DEST_PORT = 7777;
		int BUFFER_LENGTH = 1024;
		
		DatagramSocket socket = new DatagramSocket();
		
		byte[] b = new byte[BUFFER_LENGTH];
		DatagramPacket dgram;
		
		dgram = new DatagramPacket(b, b.length, InetAddress.getByName(MCAST_ADDR), DEST_PORT);
		
		System.err.println("Sending " + b.length + " bytes to " +  dgram.getAddress() + ':' + dgram.getPort());
		
		String counter = "A";
		while(true) {
			System.err.print(".");			
			dgram.setData(counter.getBytes());
			counter+= "B";
			socket.send(dgram);
		    Thread.sleep(1000);

		}
	}


}
