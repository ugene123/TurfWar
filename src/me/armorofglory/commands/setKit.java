package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.handlers.Kit;
import me.armorofglory.utils.ChatUtils;

public class setKit {
		
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				String kitName = args[1];
				ChatUtils.msgPlayer(player, "Kit '" + kitName + "' has been set!");
				Kit.setKit(player, kitName);
				
				
			} else {
				
				// Sender is not a player
				ChatUtils.msgSender(sender, "You can only use this command in-game!");
			}
			return false;
			
		}
	}

