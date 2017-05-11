package me.armorofglory.handlers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {
	
	public static void giveDefaultItems(Player player){
		
		ItemStack shop = new ItemStack(Material.EMERALD);
		ItemMeta shopMeta = shop.getItemMeta();
		shopMeta.setDisplayName(ChatColor.GREEN + "Shop " + ChatColor.GRAY + "(Right Click)");
		shop.setItemMeta(shopMeta);
		
		ItemStack vote = new ItemStack(Material.NETHER_STAR);
		ItemMeta voteMeta = vote.getItemMeta();
		voteMeta.setDisplayName(ChatColor.GREEN + "Map Selector " + ChatColor.GRAY + "(Right Click)");
		vote.setItemMeta(voteMeta);
		
		Inventory inv = player.getInventory();
		inv.clear();
		inv.setItem(3, shop);
		inv.setItem(5, vote);
	}

	public static void openShop(Player player) {
		
		// Create new inventory for menu
		Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GRAY + "Shop Kits/Upgrades");
		
		
		// Create ItemStacks to go into menu
		ItemStack kit1 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta kit1meta = kit1.getItemMeta();
		kit1meta.setDisplayName(ChatColor.YELLOW + "Kit 1");
		kit1.setItemMeta(kit1meta);
		
		ItemStack kit2 = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta kit2meta = kit2.getItemMeta();
		kit2meta.setDisplayName(ChatColor.YELLOW + "Kit 2");
		kit2.setItemMeta(kit2meta);
		
		ItemStack kit3 = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemMeta kit3meta = kit3.getItemMeta();
		kit3meta.setDisplayName(ChatColor.YELLOW + "Kit 3");
		kit3.setItemMeta(kit3meta);
		
		ItemStack kit4 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta kit4meta = kit4.getItemMeta();
		kit4meta.setDisplayName(ChatColor.YELLOW + "Kit 4");
		kit4.setItemMeta(kit4meta);
		
		// Place Items into inventory
		inv.setItem(10, kit1);
		inv.setItem(12, kit2);
		inv.setItem(14, kit3);
		inv.setItem(16, kit4);
		
		player.openInventory(inv);
	}
	
	public static void openVoteMenu(Player player) {
		
		// Create new inventory for menu
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Map Selector");
				
				
		// Create ItemStacks to go into menu
		ItemStack map1 = new ItemStack(Material.MAP);
		ItemMeta map1meta = map1.getItemMeta();
		map1meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Map 1");
		ArrayList<String> map1lore = new ArrayList<String>();
		map1lore.add(ChatColor.GRAY + "This is a very cool");
		map1lore.add(ChatColor.GRAY + "map description!");
		map1lore.add("");
		map1lore.add(ChatColor.GRAY + "Current Votes: " + ChatColor.YELLOW + "0");
		map1lore.add("");
		map1lore.add(ChatColor.YELLOW + "Click to select!");
		map1meta.setLore(map1lore);
		map1.setItemMeta(map1meta);
				
		ItemStack map2 = new ItemStack(Material.MAP);
		ItemMeta map2meta = map2.getItemMeta();
		map2meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Map 2");
		ArrayList<String> map2lore = new ArrayList<String>();
		map2lore.add(ChatColor.GRAY + "This is a very cool");
		map2lore.add(ChatColor.GRAY + "map description!");
		map2lore.add("");
		map2lore.add(ChatColor.GRAY + "Current Votes: " + ChatColor.YELLOW + "0");
		map2lore.add("");
		map2lore.add(ChatColor.YELLOW + "Click to select!");
		map2meta.setLore(map2lore);
		map2.setItemMeta(map2meta);
				
		ItemStack map3 = new ItemStack(Material.MAP);
		ItemMeta map3meta = map3.getItemMeta();
		map3meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Map 3");
		ArrayList<String> map3lore = new ArrayList<String>();
		map3lore.add(ChatColor.GRAY + "This is a very cool");
		map3lore.add(ChatColor.GRAY + "map description!");
		map3lore.add("");
		map3lore.add(ChatColor.GRAY + "Current Votes: " + ChatColor.YELLOW + "0");
		map3lore.add("");
		map3lore.add(ChatColor.YELLOW + "Click to select!");
		map3meta.setLore(map3lore);
		map3.setItemMeta(map3meta);
		
		ItemStack map4 = new ItemStack(Material.MAP);
		ItemMeta map4meta = map4.getItemMeta();
		map4meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Map 4");
		ArrayList<String> map4lore = new ArrayList<String>();
		map4lore.add(ChatColor.GRAY + "This is a very cool");
		map4lore.add(ChatColor.GRAY + "map description!");
		map4lore.add("");
		map4lore.add(ChatColor.GRAY + "Current Votes: " + ChatColor.YELLOW + "0");
		map4lore.add("");
		map4lore.add(ChatColor.YELLOW + "Click to select!");
		map4meta.setLore(map4lore);
		map4.setItemMeta(map4meta);
		
				
		// Place Items into inventory
		inv.setItem(10, map1);
		inv.setItem(12, map2);
		inv.setItem(14, map3);
		inv.setItem(16, map4);
				
		player.openInventory(inv);
	}
}
