package me.armorofglory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import me.armorofglory.handlers.Game;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.threads.TimerManager;


public class forceStart {
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		Game.setCanStart(true);
		TimerManager.resetCounter();
		Game.start();
		ScoreboardManager.updateGameboard();
		
		return false;
		
	}
}
