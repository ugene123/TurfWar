package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.handlers.Arena;
import me.armorofglory.utils.ChatUtils;


public class saveArena {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			Arena.save();
			ChatUtils.msgPlayer(player, "Arena has been saved!");

			
		} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}
		return false;
		
	}
}
