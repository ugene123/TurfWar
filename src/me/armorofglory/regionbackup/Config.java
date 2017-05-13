package me.armorofglory.regionbackup;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import me.armorofglory.Turfwar;

public class Config {
	
	private static Plugin plugin = Turfwar.getPlugin();
	
	/*
	 * Generic object storage
	 */
	
	public static void storeNull(String path) {
		plugin.getConfig().set(path, "null");
	}
	
	public static <T> void store(String path, T obj) {
		plugin.getConfig().set(path, obj);
	}
	
	public static Object load(String path) {
		return plugin.getConfig().get(path);
	}
	
	/*
	 * Simple type storage
	 */
	
	public static void storeInt(String path, int x) {
		plugin.getConfig().set(path, x);
	}
	
	public static int loadInt(String path) {
		return plugin.getConfig().getInt(path);
	}
	
	public static void storeDouble(String path, double Double) {
		plugin.getConfig().set(path, Double);
	}
	
	public static double loadDouble(String path) {
		return plugin.getConfig().getDouble(path);
	}
	
	public static void storeString(String path, String string) {
		plugin.getConfig().set(path, string);
	}
	
	public static String loadString(String path) {
		return plugin.getConfig().getString(path);
	}
	
	public static void storeBoolean(String path, boolean Boolean) {
		plugin.getConfig().set(path, Boolean);
	}
	
	public static boolean loadBoolean(String path) {
		return plugin.getConfig().getBoolean(path);
	}
	
	/*
	 * List storage
	 */
	
	public static <T> void storeList(String path, List<T> list) {
		plugin.getConfig().set(path, list);
	}
	
	public static List<?> loadList(String path) {
		return plugin.getConfig().getList(path);
	}
	
	/*
	 * Material storage
	 */
	
	public static void storeMaterial(String path, Material mat) {
		storeString(path, mat.name());
	}
	
	public static Material loadMaterial(String path) {
		return Material.matchMaterial(loadString(path));
	}
	
	/*
	 * World storage
	 */
	
	public static void storeWorld(String path, World world) {
		storeString(path, world.getName());
	}
	
	public static World loadWorld(String path) {
		return Bukkit.getWorld(loadString(path));
	}
	
	/*
	 * BlockLocation storage
	 */
	
	public static void storeBlockLocation(String path, BlockLocation loc) {
		if (loc != null) {
			storeInt(path + ".x", loc.x);
			storeInt(path + ".y", loc.y);
			storeInt(path + ".z", loc.z);
			
		} else {
			storeNull(path);
		}
	}
	
	public static BlockLocation loadBlockLocation(String path) {
		if (!contains(path)) {
			return null;
		}
		
		String s = loadString(path);
		if (s != null && s.equals("null")) {
			return null;
			
		} else {
			int x = loadInt(path + ".x");
			int y = loadInt(path + ".y");
			int z = loadInt(path + ".z");
			return new BlockLocation(x, y, z);
		}
	}
	
	/*
	 * Location storage
	 */
	
	public static void storeLocation(String path, Location loc) {
		storeWorld(path + ".world", loc.getWorld());
		storeDouble(path + ".x", loc.getX());
		storeDouble(path + ".y", loc.getY());
		storeDouble(path + ".z", loc.getZ());
		storeDouble(path + ".yaw", loc.getYaw());
		storeDouble(path + ".pitch", loc.getPitch());
	}
	
	public static Location loadLocation(String path) {
		if (!contains(path)) {
			return null;
		}
		
		World world = loadWorld(path + ".world");
		double x = loadDouble(path + ".x");
		double y = loadDouble(path + ".y");
		double z = loadDouble(path + ".z");
		float yaw = (float) loadDouble(path + ".yaw");
		float pitch = (float) loadDouble(path + ".pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}
	
	/*
	 * Other methods
	 */
	
	public static void remove(String path) {
		plugin.getConfig().set(path, null);
	}
	
	public static boolean contains(String path) {
		return plugin.getConfig().contains(path);
	}
	
	public static Set<String> getKeys(String path) {
		return plugin.getConfig().getConfigurationSection(path).getKeys(false);
	}
	
	public static void createPath(String path) {
		plugin.getConfig().createSection(path);
	}
	
	public static void saveFile() {
		plugin.saveConfig();
	}
	
	public static void loadFile() {
		plugin.saveDefaultConfig();
	}
	
	public static void reloadFile() {
		plugin.reloadConfig();
	}
	
}
