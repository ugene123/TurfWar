package me.armorofglory.utils;

import org.bukkit.Material;

public class SavedBlock {
	
	/*
	 * 
	 *  This class is for storing temporary blocks into a hashmap
	 *  with block type and block data
	 *  https://bukkit.org/threads/save-blocks-temporary.308517/
	 * 
	 */
	
	private Material material;
	private byte data;
 
	public SavedBlock(Material material, byte data) {
			this.material = material;
			this.data = data;
	}
 
	public Material getMaterial() {
			return material;
	}
 
	public byte getData() {
			return data;
	}
}