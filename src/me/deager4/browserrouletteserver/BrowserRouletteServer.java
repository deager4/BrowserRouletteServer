package me.deager4.browserrouletteserver;

import java.net.SocketException;

import me.deager4.browserrouletteserver.database.Database;
import me.deager4.browserrouletteserver.thread.ListeningThread;
import me.deager4.browserrouletteserver.thread.MainThread;

public class BrowserRouletteServer
{
	
	private static Database database;
	
	private static MainThread mainThread;
	
	private static ListeningThread listeningThread;
	
	public static void miain(String args[])
	{
		database = new Database();
		mainThread = new MainThread();
		//TODO:initilize the listening thread, and start the listening thread;
	}
	
	public static void startListeningThread(int port) throws SocketException
	{
		listeningThread = new ListeningThread(port);
	}
	 
	public static Database getDatabase()
	{
		return database;
	}
	
	public static MainThread getMainThread()
	{
		return mainThread;
	}
	
}
