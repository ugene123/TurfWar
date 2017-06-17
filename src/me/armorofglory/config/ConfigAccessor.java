package me.armorofglory.config;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import me.armorofglory.Turfwar;


public class ConfigAccessor {
	
	private static Plugin plugin = Turfwar.getPlugin();
	
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
	
	public static void storeList(String path, List<String> list) {
		plugin.getConfig().set(path, list);
		plugin.saveConfig();
	}
	
	public static void storeMap(String path, Map map) {
		plugin.getConfig().set(path, map);
		plugin.saveConfig();
	}
	
	public static List<Map<?, ?>> getMap(String path) {
		plugin.getConfig().get(path);
		return plugin.getConfig().getMapList(path);
	}
	
	public static List<String> getList(String path) {
		plugin.getConfig().get(path);
		return plugin.getConfig().getStringList(path);
	}
	
	public static int getInt(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().getInt(path);
	}
	
	public static String getStringWithColor(String path) {
		String string = plugin.getConfig().getString(path);
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static String getString(String path) {
		String string = plugin.getConfig().getString(path);
		return string;
	}
	
	public static double getDouble(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().getDouble(path);
	}
	
	public static boolean getBoolean(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().getBoolean(path);
	}
	
	public static boolean containsPath(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().contains(path);
	}
	
	public static void createPath(String path) {
		plugin.getConfig().createSection(path);
		plugin.saveConfig();
	}
	
	public static void removePath(String path) {
		plugin.getConfig().set(path, null);
		plugin.saveConfig();
	}
	
	public static void loadConfiguration(){
		plugin.saveDefaultConfig();
	}   
	
	public static void reloadConfiguration() {
		plugin.reloadConfig();
	}
	
	public static void storeObject(String path , Object object) {
		plugin.getConfig().set(path, object);
		plugin.saveConfig();
	}
	
	public static Object getObject(String path) {
		plugin.reloadConfig();
		return plugin.getConfig().get(path);
	}
	
	public static boolean contains(String path) {
		return plugin.getConfig().contains(path);
	}

}
