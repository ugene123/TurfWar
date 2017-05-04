package me.armorofglory.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;

public class Arena {

	private static Map<Location, Material> changedBlocks = new HashMap<Location, Material>();
		
	
	public static void resetArena() {
		for (Entry<Location, Material> entry : changedBlocks.entrySet()) {
	        Location loc = entry.getKey();
	        Material mat = entry.getValue();
	        loc.getBlock().setType(mat);
	    }
	}
	
	public static void addChangedBlock(Location location, Material material) {
		changedBlocks.put(location, material);
	}
}
