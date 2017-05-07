package me.armorofglory.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.LocationUtils;
import me.armorofglory.utils.SavedBlock;

public class Arena {

	private static Map<Location,SavedBlock> ArenaBlocks = new HashMap<Location, SavedBlock>();
	private static Location loc1 = LocationUtils.stringToLocation(ConfigAccessor.getString("Locations.Arena.Corner1"));
	private static Location loc2 = LocationUtils.stringToLocation(ConfigAccessor.getString("Locations.Arena.Corner2"));
	
	
	public static Map<Location, SavedBlock> save() {
 
        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
 
        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
 
        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
 
        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    Block arenablock = loc1.getWorld().getBlockAt(x, y, z);
                   
                    Location blockLocation = new Location(loc1.getWorld(), x, y, z);
                    
                    Material BlockType = arenablock.getType();
                    @SuppressWarnings("deprecation")
					byte BlockData = arenablock.getData();
                    
                    ArenaBlocks.put(blockLocation, new SavedBlock(BlockType, BlockData));
                }
            }
        }
       
        return ArenaBlocks;
    }
	
	@SuppressWarnings("deprecation")
	public static void reset() {
		
		for (Entry<Location, SavedBlock> entry : ArenaBlocks.entrySet()) {
			Location blockLocation = entry.getKey();
			SavedBlock block = entry.getValue();
			blockLocation.getBlock().setType(block.getMaterial());
			blockLocation.getBlock().setData(block.getData());
		}
	}
}
