package me.armorofglory.menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {
	
	private static final HashMap<String, Menu> menus = new HashMap<>();
	
	public static final Listener listener = new Listener() {
		@EventHandler
		public void onInventoryClick(InventoryClickEvent event) {
			Inventory inv = event.getInventory();
			if (isMenu(inv) && event.getRawSlot() < getMenu(inv).size) {
				event.setCancelled(true);
			}
		}
	};
	
	public static boolean isMenu(Inventory inv) {
		return menus.containsKey(inv.getName());
	}
	
	public static Menu getMenu(Inventory inv) {
		return menus.get(inv.getName());
	}
	
	public static ItemStack createExitButton(Material type, String name) {
		return createExitButton(type, new String[]{ name });
	}
	
	public static ItemStack createExitButton(Material type, String[] text) {
		return createExitButton(type, new LinkedList(Arrays.asList(text)));
	}
	
	public static ItemStack createExitButton(Material type, List<String> text) {
		return Button.create(type, text, new ButtonHandler() {
			@Override
			public void onPress(HumanEntity player, ItemStack item) {
				player.closeInventory();
			}
		});
	}
	
	
	
	
	
	private final int size;
	private final int rows;
	private final int cols = 9;
	
	protected final String name;
	protected Inventory inventory = null;
	protected HashSet<UUID> players = new HashSet<>();
	
	public Menu(String name, int rows) {
		name += "§r";
		while (menus.containsKey(name)) {
			name += "§r";
		}
		
		this.name = name;
		this.rows = rows;
		this.size = rows * cols;
		inventory = Bukkit.createInventory(null, size, name);
		menus.put(name, this);
	}
	
	public ItemStack createButton(Material type, String name) {
		return createButton(type, new String[]{ name });
	}
	
	public ItemStack createButton(Material type, String[] text) {
		return createButton(type, new LinkedList(Arrays.asList(text)));
	}
	
	public ItemStack createButton(Material type, List<String> text) {
		return Button.create(type, text, new ButtonHandler() {
			@Override
			public void onPress(HumanEntity player, ItemStack item) {
				if (inventory == null) {
					player.closeInventory();
				} else {
					setInventory(player);
				}
			}
		});
	}

	public boolean isEmpty(int r, int c) {
		return get(r, c) == null;
	}
	
	public void set(int r, int c, ItemStack item) {
		inventory.setItem(getPosition(r, c), item);
	}
	
	public ItemStack get(int r, int c) {
		return inventory.getItem(cols * r + c);
	}
	
	protected int getPosition(int r, int c) {
		if (r < 0) r += rows;
		if (c < 0) c += cols;
		return 9 * r + c;
	}
	
	protected void setInventory(HumanEntity player) {
		players.add(player.getUniqueId());
		Inventory copyInv = Bukkit.createInventory(player, size, name);
		for (int i = 0; i < size; i++) {
			ItemStack item = inventory.getItem(i);
			copyInv.setItem(i, item);
		}
		player.openInventory(copyInv);
	}
	
	protected void update() {
		for (UUID id : players) {
			try {
				setInventory(Bukkit.getPlayer(id));
			} catch (NullPointerException e) {
				players.remove(id);
			}
		}
	}
	
}
