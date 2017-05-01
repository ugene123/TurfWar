package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.armorofglory.handlers.Team;
import me.armorofglory.utils.ChatUtils;

public class addTeam {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(sender instanceof Player) {
				
				String team = args[1].toUpperCase();
				if(Team.allTeams.contains(team) != true) {
					new Team(team);
					ChatUtils.msgSender(sender, "Team " + team + " has been created!");
				} else {
					ChatUtils.msgSender(sender, "Team " + team + " already exists!");
				}

		
			} else {
			
			// Sender is not a player
			ChatUtils.msgSender(sender, "You can only use this command in-game!");
		}

		return false;
		
	}
}
