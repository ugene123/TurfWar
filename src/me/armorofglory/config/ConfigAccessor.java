package me.armorofglory.config;


import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import me.armorofglory.Warfare;


public class ConfigAccessor {
	
	private static Plugin plugin = Warfare.getPlugin();
	
	
	public static void storeInt(String path, int Int) {
		plugin.getConfig().set(path, Int);
		plugin.saveConfig();
	}
	
	public static void storeString(String path , String string) {
		plugin.getConfig().set(path, string);
		plugin.saveConfig();
	}
	
	public static void storeDouble(String path , double Double) {
		plugin.getConfig().set(path, Double);
		plugin.saveConfig();
	}
	
	public static void storeBoolean(String path , boolean Boolean) {
		plugin.getConfig().set(path, Boolean);
		plugin.saveConfig();
	}
	public static int getInt(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().getInt(path);
	}
	
	public static String getString(String path) {
		String string = plugin.getConfig().getString(path);
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static double getDouble(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().getDouble(path);
	}
	
	public static boolean getBoolean(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().getBoolean(path);
	}
	
	public static ArrayList getArray(ArrayList path) {
		plugin.reloadConfig();
		return plugin.getConfig().getAr;
	}
	
	public static void loadConfiguration(){
		plugin.saveDefaultConfig();
	}     
	public static void reloadConfiguration() {
		plugin.reloadConfig();
	}

}
