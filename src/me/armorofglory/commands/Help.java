package me.armorofglory.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class Help {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
	
		// Help menu for Plugin
		sender.sendMessage(" ");
		sender.sendMessage(ChatColor.GOLD + "--------------------< " + ChatColor.YELLOW + ChatColor.BOLD + "TURF WARS" + ChatColor.GOLD + " >--------------------");
		sender.sendMessage(" ");
		sender.sendMessage(ChatColor.YELLOW + " /tw setlobby " + ChatColor.GRAY + "- Set Lobby spawn point");
		sender.sendMessage(ChatColor.YELLOW + " /tw addteam <team> " + ChatColor.GRAY + "- Add team");
		sender.sendMessage(ChatColor.YELLOW + " /tw delteam <team> " + ChatColor.GRAY + "- Delete team");
		sender.sendMessage(ChatColor.YELLOW + " /tw setteamspawn <team> " + ChatColor.GRAY + "- Set team spawn");
		sender.sendMessage(ChatColor.YELLOW + " /tw setteamblock <team> " + ChatColor.GRAY + "- Set team block");
		sender.sendMessage(ChatColor.YELLOW + " /tw setcorner # " + ChatColor.GRAY + "- Set arena corner");
		sender.sendMessage(ChatColor.YELLOW + " /tw savearena " + ChatColor.GRAY + "- Saves arena");
		sender.sendMessage(ChatColor.YELLOW + " /tw help " + ChatColor.GRAY + "- Help menu");
		sender.sendMessage(" ");
		sender.sendMessage(ChatColor.GOLD + "-----------------------------------------------------");;
		
		return false;
	}
}
