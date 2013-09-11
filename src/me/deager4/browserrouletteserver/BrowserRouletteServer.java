package me.deager4.browserrouletteserver;

import me.deager4.browserrouletteserver.database.Database;

public class BrowserRouletteServer
{
	
	private static Database database;
	
	public static void miain(String args[])
	{
		database = new Database();
	}
	 
	public static Database getDatabase()
	{
		return database;
	}
	
}
