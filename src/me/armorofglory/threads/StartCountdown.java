package me.armorofglory.threads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.armorofglory.GameState;
import me.armorofglory.Warfare;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.utils.ChatUtils;

public class StartCountdown extends BukkitRunnable {

	Warfare plugin;
	
	public StartCountdown(Warfare plugin) {
		this.plugin = plugin;
	
	}
	
	private static int counter = ConfigAccessor.getInt("Settings.startCountdownTimer");
	
	public static void resetCounter(){
		counter = ConfigAccessor.getInt("Settings.startCountdownTimer");
	}
	

	public void run() {
		if (GameState.isState(GameState.LOBBY)) {
			if(Game.canStart()) {
				
				if(counter >= 0) {
					
					if(counter % 10 == 0 && counter > 0) {
						
						ChatUtils.broadcast(ChatColor.DARK_GRAY + "(" + 
								ChatColor.YELLOW + counter + ChatColor.DARK_GRAY + ")" +
				        	ChatColor.WHITE + " seconds until the game starts!");
						
					} else if (counter > 1 && counter < 10) {
						
						ChatUtils.broadcast(ChatColor.DARK_GRAY + "(" + 
								ChatColor.YELLOW + counter + ChatColor.DARK_GRAY + ")" +
						        ChatColor.WHITE + " seconds until the game starts!");
						
					} else if (counter == 1) {
						ChatUtils.broadcast(ChatColor.DARK_GRAY + "(" + 
								ChatColor.YELLOW + counter + ChatColor.DARK_GRAY + ")" +
						        ChatColor.WHITE + " second until the game starts!");
						
					} else if (counter == 0) {
						Game.start();
					
					} counter--;
				
			} else {
				
				Bukkit.getLogger().info("Countdown stopped");
				this.cancel();
				
			}
				
			}
			
		}
		
	}
}
