package me.armorofglory.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import me.armorofglory.handlers.Game;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;


public class forceStop {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		Game.forcestop();
		ChatUtils.broadcast(ChatColor.RED + "Game has been stopped!");
		ScoreboardManager.updateLobbyboard();
		
		return false;
		
	}
}
