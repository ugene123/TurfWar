package me.armorofglory.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.ChatUtils;


public class setLobby {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			// Get player location
			Player player = (Player) sender;
			Location loc = player.getLocation();
			String world = loc.getWorld().getName();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			double yaw = loc.getYaw();
			double pitch = loc.getPitch();
			
			// Store it into config as a string
			String lobby = world + ", " + x + ", " + y + ", " + z + ", " + yaw + ", " + pitch;
			ConfigAccessor.storeString("Locations.Lobby", lobby);
			ChatUtils.msgPlayer(player, "Lobby has been set!");
			
			
		} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}
		return false;
		
	}
}
