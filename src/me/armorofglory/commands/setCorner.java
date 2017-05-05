package me.armorofglory.commands;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.ChatUtils;


public class setCorner {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			String cornerNumber = args[1];
			
			if (cornerNumber.equalsIgnoreCase("1") || cornerNumber.equalsIgnoreCase("2")){
				

				// Get block player is looking at
				Location loc = player.getTargetBlock((Set<Material>) null, 5).getLocation();
				
				String world = loc.getWorld().getName();
				double x = loc.getX();
				double y = loc.getY();
				double z = loc.getZ();
				
				// Store it into config as a string
				String cornerlocation = world + ", " + x + ", " + y + ", " + z;
				ConfigAccessor.storeString("Locations.Arena.Corner" + cornerNumber, cornerlocation);
				ChatUtils.msgPlayer(player, "Corner " + cornerNumber + " has been set!");
		
			} else {
			
				ChatUtils.msgPlayer(player, "'" + cornerNumber + "'" + " is not a valid corner");
				
			}
			

			
		} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}
		return false;
		
	}
}
