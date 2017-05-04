package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.ChatUtils;

public class setTeamBlock {
	

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			String team = args[1].toUpperCase();
			
			Player player = (Player) sender;
		
				@SuppressWarnings("deprecation")
				int teamblock = player.getItemInHand().getTypeId();
				ConfigAccessor.storeString("Teams." + team + ".TeamBlock", teamblock + "");
				ChatUtils.msgPlayer(player, teamblock + " has been set as the TeamBlock for Team " + team);
			
			
			
		} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}
		return false;
		
	}
}
