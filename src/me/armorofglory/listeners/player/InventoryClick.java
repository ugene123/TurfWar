package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryClick implements Listener{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		
		// SHOP MENU
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Shop Kits/Upgrades")){
				
			if(event.isRightClick() || event.isLeftClick()) {
				event.setCancelled(true);
			}
		
		}
		
		// MAP SELECTOR
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Map Selector")){
			
			if(event.isRightClick() || event.isLeftClick()) {
				event.setCancelled(true);
			}
		
		}
		
	}
}
