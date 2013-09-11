package me.deager4.browserrouletteserver.database;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Random;

public class Database 
{
	private HashMap<InetAddress, Client> clientList;
	
	public Database()
	{
		clientList = new HashMap<InetAddress, Client>();
	}
	
	public void addClient(Client client)
	{
		clientList.put(client.getAddress(), client);
	}
	
	public void removeClient(InetAddress address)
	{
		clientList.remove(address);
	}
	
	public void getMate(InetAddress address)
	{
		clientList.get(address).setIsConnectedToClient(true);
		clientList.get(randomClient(address).getAddress()).setIsConnectedToClient(true);
		//TODO:Add a sender thread to tell the clients to connect to each other
	}
	
	public HashMap<InetAddress, Client> getClientList()
	{
		return this.clientList;
	}
	
	private Client randomClient(InetAddress address)
	{
		Random generator = new Random(System.currentTimeMillis());
		int r = generator.nextInt(clientList.size());
		Object[] addressArray = clientList.keySet().toArray();
		if(clientList.get((InetAddress)addressArray[r]).getIsConnectedToClient() == true || (InetAddress)addressArray[r] == (address))
		{
			randomClient(address);
		}
		else
		{
			return clientList.get(r);
		}
		return null;
		
	}
}
