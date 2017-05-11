package me.armorofglory.handlers;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.armorofglory.GameState;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.threads.TimerStarter;
import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class Game {
	
	private static int minPlayersToStart = ConfigAccessor.getInt("Settings.minPlayersToStart");
	private static boolean canStart = false;
	private static boolean hasStarted = false;
	
	public static void start() {
		
		
		// Change game state to in-game
		GameState.setState(GameState.IN_GAME);
		ChatUtils.broadcast(ChatColor.GOLD + "Game has started!" );
		hasStarted = true; 
		
		Bukkit.getWorld("world").setTime(0);
		
		// Divide players online to the teams enabled
		int i = 0 ;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i > Team.getNumTeams() - 1){
				i = 0;
			}
			
			Team team = Team.getTeam(i);
			team.addPlayer(player);
			
			try {
				player.teleport(team.getSpawn());
			} catch (Exception e) {
				ChatUtils.broadcast("Unable to teleport player on team " + team.getName());
				e.printStackTrace();
			}
			
			player.getInventory().clear();
			Armor.setArmor(team.getColor(), player);
			i++;
		}
		
		TimerStarter.start();
		
	}
	
	public static void stop() {
		// Change hasStarted and GameState back to Lobby
		hasStarted = false;
		setCanStart(true);
		GameState.setState(GameState.POST_GAME);
		ScoreboardManager.updatePostboard();
		
		// Teleport players back to Lobby
		LocationUtils.teleportAllToLobby();
		ChatUtils.broadcast(ChatColor.GOLD + " Game Over!");
		
		// Reset Arena
		Arena.reset();
		
		for(Player player : Bukkit.getOnlinePlayers()){
			Armor.strip(player);
			GUI.giveDefaultItems(player);
		}	
			
	}
	
	public static void forcestop() {
		// Change hasStarted and GameState back to Lobby
		hasStarted = false;
		setCanStart(false);
		GameState.setState(GameState.LOBBY);
		ScoreboardManager.setCounter("Paused");
				
		// Teleport players back to Lobby
		LocationUtils.teleportAllToLobby();
		
		// Reset Arena
		Arena.reset();
				
		for(Player player : Bukkit.getOnlinePlayers()){
			Armor.strip(player);
			GUI.giveDefaultItems(player);
		}
		
				
		// Restart Start Countdown
		// CountdownManager.resetCounter();
	}
	
	public static boolean canStart() {
		return canStart;
	}
	
	public static boolean hasStarted(){
		return hasStarted;
	}
	
	public static void setCanStart(boolean b) {
		canStart = b;
	}
	
	public static int getMinPlayersToStart() {
		return minPlayersToStart;
	}
	
}
