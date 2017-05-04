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
				
				String team = args[1].toUpperCase();
				// if newTeam is NOT in allTeams arrayList and is not in Config
				if(Team.containsInAllTeams(team) && (ConfigAccessor.containsPath("Teams." + team))) {
						Team.removeTeam(team);
						ConfigAccessor.storeList("Settings.allTeams", Team.getAllTeams());
						ConfigAccessor.removePath("Teams." + team);
						ChatUtils.msgSender(sender, "Team " + team + " has been removed!");
					}  else {
					ChatUtils.msgSender(sender, "Team " + team + " is not an existent team!");
				}
		
			} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}

		return false;
		
	}
}
