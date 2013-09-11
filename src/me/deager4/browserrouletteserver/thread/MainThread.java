package me.deager4.browserrouletteserver.thread;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import me.deager4.browserrouletteserver.BrowserRouletteServer;
import me.deager4.browserrouletteserver.database.Client;

public class MainThread extends Thread
{
	private boolean isRunning;
	
	private static ArrayList<DatagramPacket> packetPool;
	
	public MainThread(int port)
	{
		super("main thread");
		packetPool = new ArrayList<DatagramPacket>();
	}
	
	public static void addPacketToPool(DatagramPacket packet)
	{
		packetPool.add(packet);
	}
	
	public void run()
	{
		isRunning = true;
		while(isRunning)
		{
			if(packetPool.size() > 0)
			{
				DatagramPacket packet = packetPool.get(0);
				byte[] buf = packet.getData();
				String data = buf.toString();
				if(data.equals(""))
				{
					System.out.println("[Main Thread]:Server received an empty packet from " + packet.getAddress().toString());
				}
				else
				{
					
					if(BrowserRouletteServer.getDatabase().getClientList().get(packet.getAddress()) == null)
					{
						StringTokenizer a = new StringTokenizer(data, " ");
						String header = a.nextToken();
						if(!header.equals("auth"))
						{
							System.out.println("[Main Thread]:Server received unauthorized packet from " + packet.getAddress().toString());
						}
						else
						{
							try
							{
								String newPort = a.nextToken();
								int port = Integer.parseInt(newPort);
								Client client = new Client(packet.getAddress(), packet.getPort(), port);
								BrowserRouletteServer.getDatabase().addClient(client);
							}
							catch(NoSuchElementException e)
							{
								System.out.println("[Main Thread]:Server received a poorly executed auth packet from " + packet.getAddress().toString());
							}
							catch(NumberFormatException e)
							{
								System.out.println("[Main Thread]:Server received a non integer new port auth packet from " + packet.getAddress().toString());
							}
						}
					}
					else
					{
						StringTokenizer a = new StringTokenizer(data, " ");
						String header = a.nextToken();
						if(header.equals("leavePartner"))
						{
							if(BrowserRouletteServer.getDatabase().getClientList().get(packet.getAddress()).getIsConnectedToClient() == false)
							{
								System.out.println("[Main Thread]:Server received unnessary partner abandonment packet from " + packet.getAddress());
							}
							else
							{
								BrowserRouletteServer.getDatabase().getClientList().get(packet.getAddress()).setIsConnectedToClient(false);
							}
						}
						else if(header.equals("getPartner"))
						{
							if(BrowserRouletteServer.getDatabase().getClientList().get(packet.getAddress()).getIsConnectedToClient() == true)
							{
								System.out.println("[Main Thread]:Server received unnessary partner assignment packet from " + packet.getAddress());
							}
							else
							{
								BrowserRouletteServer.getDatabase().getMate(packet.getAddress());
							}
						}
						else if(header.equals("logout"))
						{
							BrowserRouletteServer.getDatabase().getClientList().remove(packet.getAddress());
						}
						else
						{
							
						}
						
					}
					
				}
				packetPool.remove(0);
			}
			else
			{
				continue;
			}
		}
	}
	
}
