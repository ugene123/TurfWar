package me.armorofglory.menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Button {
	
	public static HashMap<String, ButtonHandler> buttons = new HashMap<>();
	
	public static final Listener listener = new Listener() {
		@EventHandler
		public void onPlayerDropItem(PlayerDropItemEvent event) {
			Item item = event.getItemDrop();
			if (isButton(item.getItemStack())) {
				event.setCancelled(true);
				event.getPlayer().sendMessage("You cannot drop this item");
			}
		}
		
		@EventHandler
		public void onPlayerInteract(PlayerInteractEvent event) {
			ItemStack item = event.getItem();
			if (isButton(item)) {
				Action action = event.getAction();
				if (action == Action.RIGHT_CLICK_AIR | action == Action.RIGHT_CLICK_BLOCK) {
					getHandler(item).onPress(event.getPlayer(), item);
				}
			}
		}
		
		@EventHandler
		public void onInventoryClick(InventoryClickEvent event) {
			ItemStack item = event.getCurrentItem();
			if (isButton(item)) {
				getHandler(item).onPress(event.getWhoClicked(), item);
				event.setCancelled(true);
			}
		}
	};
	
	public static boolean isButton(ItemStack item) {
		if (item != null && item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null) {
			return buttons.containsKey(item.getItemMeta().getDisplayName());
		} else {
			return false;
		}
	}
	
	public static ButtonHandler getHandler(ItemStack item) {
		return buttons.get(item.getItemMeta().getDisplayName());
	}
	
	public static ItemStack create(Material type, String name, ButtonHandler handler) {
		return create(type, new String[]{ name }, handler);
	}
	
	public static ItemStack create(Material type, String[] text, ButtonHandler handler) {
		return create(type, new LinkedList(Arrays.asList(text)), handler);
	}
	
	public static ItemStack create(Material type, List<String> text, ButtonHandler handler) {
		if (handler == null) {
			return null;
		}
		
		String name = text.remove(0) + "§r";
		while (buttons.containsKey(name)) {
			name += "§r";
		}
		buttons.put(name, handler);
		
		ItemStack item = new ItemStack(type);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(text);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static void delete(ItemStack item) {
		if (isButton(item)) {
			buttons.remove(item);
		}
	}
	
}
