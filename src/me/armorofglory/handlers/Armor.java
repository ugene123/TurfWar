package me.armorofglory.handlers;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Armor {

	public static void setArmor(Color color, Player player) {
		
//		switch (teamColor) {
//			case "RED": color = Color.RED;
//				break;
//			case "BLUE": color = Color.BLUE;
//				break;
//			case "YELLOW": color = Color.YELLOW;
//				break;
//			case "GREEN": color = Color.GREEN;
//				break;	
//		}
		
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();
		helmetmeta.setColor(color);
		helmet.setItemMeta(helmetmeta);
		player.getInventory().setHelmet(helmet);
		
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplatemeta.setColor(color);
		chestplate.setItemMeta(chestplatemeta);
		player.getInventory().setChestplate(chestplate);
		
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsemeta = (LeatherArmorMeta) chestplate.getItemMeta();
		leggingsemeta.setColor(color);
		leggings.setItemMeta(leggingsemeta);
		player.getInventory().setLeggings(leggings);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsmeta = (LeatherArmorMeta) chestplate.getItemMeta();
		bootsmeta.setColor(color);
		boots.setItemMeta(bootsmeta);
		player.getInventory().setBoots(boots);
		
	}
	
	public static void strip(Player player) {
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
	}

}


