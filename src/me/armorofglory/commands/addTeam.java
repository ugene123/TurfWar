package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Team;
import me.armorofglory.utils.ChatUtils;

public class addTeam {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
			
			String teamName = args[1].toUpperCase();
			
			// if newTeam is NOT in allTeams and is not in Config
			if(!Team.hasTeam(teamName)) {
				Team team = Team.create(teamName);
				
				if (team != null) {
					ConfigAccessor.createPath("Teams." + teamName);
					ConfigAccessor.storeList("Settings.allTeams", Team.getTeamNames());
					ChatUtils.msgSender(sender, "Team " + teamName + " has been created!");
				
				} else {
					// team cannot be created
					ChatUtils.msgSender(sender, "Cannot create team " + teamName + "!");
				}
				
			}  else {
				// team already exists
				ChatUtils.msgSender(sender, "Team " + teamName + " already exists!");
			}
	
		} else {
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}

		return false;
		
	}
}
