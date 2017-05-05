package me.armorofglory.commands;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.ChatUtils;

public class setTeamBlock {
	

	
	@SuppressWarnings("deprecation")
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			String team = args[1].toUpperCase();
			
			Player player = (Player) sender;
		
			// Get the block player is looking at and store it into config
			Block teamBlock =  player.getTargetBlock((Set<Material>) null, 5);
			ConfigAccessor.storeString("Teams." + team + ".TeamBlock", teamBlock.getTypeId() + ":" + teamBlock.getData());
			
			ChatUtils.msgSender(player, "Team " + team + "'s block has been set!");
			
			
		} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}
		return false;
		
	}
}

