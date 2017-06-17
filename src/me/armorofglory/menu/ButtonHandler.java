package me.armorofglory.menu;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

public interface ButtonHandler {
	
	public void onPress(HumanEntity player, ItemStack item);
	
}
