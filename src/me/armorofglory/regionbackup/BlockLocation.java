package me.armorofglory.regionbackup;

import org.bukkit.Location;

public class BlockLocation {
	
	public final int x;
	public final int y;
	public final int z;
	
	public BlockLocation(Location loc) {
		this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}
	
	public BlockLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean isBetween(BlockLocation b1, BlockLocation b2) {
		if ((b1.x - x) * (b2.x - x) > 0)
			return false;
		
		if ((b1.y - y) * (b2.y - y) > 0)
			return false;
		
		if ((b1.z - z) * (b2.z - z) > 0)
			return false;
		
		return true;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof BlockLocation) {
			BlockLocation loc = (BlockLocation) obj;
			return x == loc.x && y == loc.y && z == loc.z;
			
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + this.x;
		hash = 71 * hash + this.y;
		hash = 71 * hash + this.z;
		return hash;
	}
	
	public String toString() {
		return x + ", " + y + ", " + z;
	}
	
}
