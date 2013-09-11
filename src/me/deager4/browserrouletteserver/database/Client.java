package me.deager4.browserrouletteserver.database;

import java.net.InetAddress;

public class Client 
{
	private InetAddress address;
	
	private int port;
	
	private int tcpPort;
	
	private boolean isConnectedToClient;
	
	public Client(InetAddress address, int port, int tcpPort)
	{
		this.address = address;
		this.port = port;
		this.tcpPort = tcpPort;
		isConnectedToClient = false;
	}
	
	public boolean getIsConnectedToClient()
	{
		return isConnectedToClient;
	}
	
	public void setIsConnectedToClient(boolean isConnected)
	{
		this.isConnectedToClient = isConnected;
	}
	
	public int getPort()
	{
		return this.port;
	}
	
	public InetAddress getAddress()
	{
		return this.address;
	}
}
