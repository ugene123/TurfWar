package me.armorofglory.regionbackup;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import me.armorofglory.Turfwar;

public class Region implements Listener {
	
	private World world;
	private boolean active = false;
	private BlockLocation corner1 = null;
	private BlockLocation corner2 = null;
	private HashMap<BlockLocation, BlockData> savedBlocks = new HashMap<>();
	
	public Region(World world) {
		this.world = world;
	}
	
	/*
	 * SETTERS
	 */
	
	public boolean setCorner1(Location corner1) {
		return setCorner1(new BlockLocation(corner1));
	}
	
	public boolean setCorner2(Location corner2) {
		return setCorner2(new BlockLocation(corner2));
	}
	
	public boolean setCorner1(BlockLocation corner1) {
		if (active)
			return false;
		
		this.corner1 = corner1;
		return true;
	}
	
	public boolean setCorner2(BlockLocation corner2) {
		if (active)
			return false;
		
		this.corner2 = corner2;
		return true;
	}
	
	/*
	 * GETTERS
	 */
	
	public World getWorld() {
		return world;
	}
	
	public BlockLocation getCorner1() {
		return corner1;
	}
	
	public BlockLocation getCorner2() {
		return corner2;
	}
	
	public boolean isActive() {
		return active;
	}
	
	/*
	 * METHODS
	 */
	
	public boolean save() {
		if (corner1 == null || corner2 == null)
			return false;
		
		savedBlocks = new HashMap<>();
		active = true;
		return true;
	}
	
	public void stop() {
		savedBlocks = new HashMap<>();
		active = false;
	}
	
	public int reset() {
		int n = savedBlocks.size();
		
		for (BlockLocation loc : savedBlocks.keySet()) {
			BlockData bd = savedBlocks.get(loc);
			Block b = world.getBlockAt(loc.x, loc.y, loc.z);
			
			b.setType(bd.type);
			b.setData(bd.data);
		}
		
		savedBlocks = new HashMap<>();
		return n;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!active)
			return;
		
		Block block = e.getBlock();
		BlockLocation bl = new BlockLocation(block.getLocation());
		
		if (bl.isBetween(corner1, corner2) && !savedBlocks.containsKey(bl)) {
			savedBlocks.put(bl, new BlockData(block));
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!active)
			return;
		
		Block block = e.getBlock();
		BlockLocation bl = new BlockLocation(block.getLocation());
		
		if (bl.isBetween(corner1, corner2) && !savedBlocks.containsKey(bl)) {
			savedBlocks.put(bl, new BlockData(Material.AIR));
		}
	}
	
	/*
	 * STATIC METHODS
	 */
	
	public static String configPath = "regions";
	private static HashMap<String, Region> regions = new HashMap<>();
	
	public static boolean has(String name) {
		return regions.containsKey(name);
	}
	
	public static boolean add(String name, World world) {
		if (has(name)) {
			return false;
			
		} else {
			Region rr = new Region(world);
			regions.put(name, rr);
			
			Plugin plugin = Turfwar.getPlugin();
			plugin.getServer().getPluginManager().registerEvents(rr, plugin);
			return true;
		}
	}
	
	public static Set<String> list() {
		return regions.keySet();
	}
	
	public static Region get(String name) {
		if (has(name)) {
			return regions.get(name);
			
		} else {
			return null;
		}
	}
	
	public static boolean del(String name) {
		if (has(name)) {
			regions.remove(name);
			Config.remove(configPath + "." + name);
			return true;
			
		} else {
			return false;
		}
	}
	
	public static void storeAll() {
		String path;
		for (String name : regions.keySet()) {
			Region reg = get(name);
			reg.reset();
			
			path = configPath + "." + name;
			Config.storeWorld(path + ".world", reg.getWorld());
			Config.storeBoolean(path + ".active", reg.isActive());
			Config.storeBlockLocation(path + ".corner1", reg.getCorner1());
			Config.storeBlockLocation(path + ".corner2", reg.getCorner2());
		}
	}
	
	public static void loadAll() {
		String path;
		for (String name : Config.getKeys(configPath)) {
			path = configPath + "." + name;
			add(name, Config.loadWorld(path + ".world"));
			
			Region reg = get(name);
			reg.setCorner1(Config.loadBlockLocation(path + ".corner1"));
			reg.setCorner2(Config.loadBlockLocation(path + ".corner2"));
			
			if (Config.loadBoolean(path + ".active")) {
				reg.save();
			}
		}
	}
	
}
