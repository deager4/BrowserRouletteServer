package me.deager4.browserrouletteserver.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendingThread extends Thread
{
	
	private DatagramPacket packet;
	
	private DatagramSocket socket;
	
	public SendingThread(InetAddress address, String data, int port) throws SocketException
	{
		super("Sending Thread");
		byte[] buf = data.getBytes();
		packet = new DatagramPacket(buf, buf.length, address, port);
		socket = new DatagramSocket();
	}
	
	public void run()
	{
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.close();
	}
	
	
	
}
