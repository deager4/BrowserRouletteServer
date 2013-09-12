package me.deager4.browserrouletteserver.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import me.deager4.browserrouletteserver.BrowserRouletteServer;

public class ListeningThread extends Thread
{
	private DatagramSocket socket;
	
	private boolean isRunning;
	
	public ListeningThread(int port) throws SocketException
	{
		super("Listening Thread");
		socket = new DatagramSocket(port);
	}
	
	public void run()
	{
		isRunning = true;
		while(isRunning)
		{
			byte[] buf = null;
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try 
			{
				socket.receive(packet);
				BrowserRouletteServer.getMainThread().addPacketToPool(packet);
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void terminate()
	{
		socket.close();
		isRunning = false;
	}
	
	
}
