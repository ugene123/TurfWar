package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Team;
import me.armorofglory.utils.ChatUtils;

public class removeTeam {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			String teamName = args[1].toUpperCase();
			
			// if newTeam is NOT in allTeams and is not in Config
			if(Team.hasTeam(teamName) && (ConfigAccessor.containsPath("Teams." + teamName))) {
					Team.removeTeam(teamName);
					ConfigAccessor.storeList("Settings.allTeams", Team.getTeamNames());
					ConfigAccessor.removePath("Teams." + teamName);
					ChatUtils.msgSender(sender, "Team " + teamName + " has been removed!");
				}  else {
				ChatUtils.msgSender(sender, "Team " + teamName + " is not an existent team!");
			}
	
		} else {
		
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}

		return false;
		
	}
}
