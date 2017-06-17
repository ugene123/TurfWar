package me.armorofglory.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import me.armorofglory.config.ConfigAccessor;

public class Kit {

	public static void setKit(Player player, String kitName){
		
		
		PlayerInventory inv = player.getInventory();
		
		if(inv.getHelmet() != null) {
			ItemStack helmet = inv.getHelmet();
			ConfigAccessor.storeInt("Kits." + kitName + ".armor.helmet", helmet.getTypeId());	
		}
		
		if(inv.getChestplate() != null) {
			ItemStack chestplate = inv.getChestplate();
			ConfigAccessor.storeInt("Kits." + kitName + ".armor.chestplate", chestplate.getTypeId());	
		}
		
		if(inv.getLeggings() != null) {
			ItemStack leggings = inv.getLeggings();
			ConfigAccessor.storeInt("Kits." + kitName + ".armor.leggings", leggings.getTypeId());	
		}
		
		if(inv.getBoots() != null) {
			
			ItemStack boots = inv.getBoots();
			int bootsID = boots.getTypeId();
			ItemMeta bootsMeta = boots.getItemMeta();
			String name = bootsMeta.getDisplayName();
			
			List<String> lore = bootsMeta.getLore();
			
			if(lore.isEmpty() == false) {
				
			}
			
			Map<Enchantment, Integer> enchants = bootsMeta.getEnchants();
		
			if (enchants.isEmpty() == false){
				
				List<String> allenchants = new ArrayList();
				
				for (Entry<Enchantment, Integer> enchant : enchants.entrySet() ) {
					String enchantName = enchant.getKey().getName();
					int enchantLevel = enchant.getValue();
					allenchants.add(enchantName + ", " + enchantLevel);
			}
 
				ConfigAccessor.storeList("Kits." + kitName + ".armor.boots.enchants", allenchants);
			}
			
			ConfigAccessor.storeInt("Kits." + kitName + ".armor.boots.id", bootsID);	
			ConfigAccessor.storeString("Kits." + kitName + ".armor.boots.displayname", name);	
			ConfigAccessor.storeList("Kits." + kitName + ".armor.boots.lore", lore);
		}
			
		int count = 1;
		
		for(int i=0; i< 36; i++) {
			
			
			if(inv.getItem(i) != null) {
				
				ItemStack item = inv.getItem(i);
				int itemID = item.getTypeId();
				int itemAmount = item.getAmount();
				int slotID = i;
				String itemDataString = "" + item.getData(); 
				int itemData = Integer.parseInt(itemDataString.replaceAll("[\\D]", ""));
				
				ConfigAccessor.storeInt("Kits." + kitName + ".inventory.item" + count + ".id", itemID );
				ConfigAccessor.storeInt("Kits." + kitName + ".inventory.item" + count + ".data", itemData);
				ConfigAccessor.storeInt("Kits." + kitName + ".inventory.item" + count + ".amount", itemAmount);
				ConfigAccessor.storeInt("Kits." + kitName + ".inventory.item" + count + ".slot", slotID);
				
				count++;
			
			}
		}
	}
	
	public static void getKit(Player player, String kitName) {
	
		// Get player inventory
		PlayerInventory inv = player.getInventory();
		
		// Clear player inventory
		inv.clear();
		
		// Set player armor to armor set in kit config
		int helmetID = ConfigAccessor.getInt("Kits." + kitName + ".armor.helmet");
		ItemStack helmet = new ItemStack(Material.getMaterial(helmetID));
		inv.setHelmet(helmet);
		
		int chestplateID = ConfigAccessor.getInt("Kits." + kitName + ".armor.chestplate");
		ItemStack chestplate = new ItemStack(Material.getMaterial(chestplateID));
		inv.setChestplate(chestplate);
		
		int leggingsID = ConfigAccessor.getInt("Kits." + kitName + ".armor.leggings");
		ItemStack leggings = new ItemStack(Material.getMaterial(leggingsID));
		inv.setLeggings(leggings);
		
		int bootsID = ConfigAccessor.getInt("Kits." + kitName + ".armor.boots");
		ItemStack boots = new ItemStack(Material.getMaterial(bootsID));
		inv.setBoots(boots);
		
		// Count number of items to give
		int itemCount = 0;
		for (int i = 0; i < 36 ; i++){
			if (ConfigAccessor.contains("Kits." + kitName + ".inventory.item" + i))
				itemCount++;
		}
		
		
		// Give player each item# set in inventory kit config
		for (int count = 1; count < (itemCount + 1); count++){
			
			int itemID = ConfigAccessor.getInt("Kits." + kitName + ".inventory.item" + count + ".id");
			int itemData = ConfigAccessor.getInt("Kits." + kitName + ".inventory.item" + count + ".data");
			int itemAmount = ConfigAccessor.getInt("Kits." + kitName + ".inventory.item" + count + ".amount");
			int itemSlot = ConfigAccessor.getInt("Kits." + kitName + ".inventory.item" + count + ".slot");
			
			ItemStack item = new ItemStack(Material.getMaterial(itemID), itemAmount, (byte) itemData);
			ItemMeta itemMeta =item.getItemMeta();
			inv.setItem(itemSlot, item);
			
		}
		
	}
	
}
