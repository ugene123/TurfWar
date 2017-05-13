package me.armorofglory.regionbackup;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockData {
	
	public final Material type;
	public final byte data;
	
	public BlockData(Block b) {
		this(b.getType(), b.getData());
	}
	
	public BlockData(Material type) {
		this(type, (byte) 0);
	}
	
	public BlockData(Material type, byte data) {
		this.type = type;
		this.data = data;
	}
	
}
