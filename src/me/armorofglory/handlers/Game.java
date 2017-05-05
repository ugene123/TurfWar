package me.armorofglory.handlers;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
		ChatUtils.broadcast(ChatColor.GOLD + " Game has started!" );
		hasStarted = true; 
		
		
		// Divide players online to the teams enabled
		int i = 0 ;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i > Team.getAllTeamsSize() - 1){
				i = 0;
			}
			String teamname = Team.getAllTeamsIndex(i);
			Team.addPlayer(player, teamname);
			Location spawn = LocationUtils.getTeamSpawn(teamname);
			player.teleport(spawn);
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
		
		
		// Restart Start Countdown
		// CountdownManager.resetCounter();
	}
	
	public static boolean canStart() {
		return canStart;
	}
	
	public static boolean gethasStarted(){
		return hasStarted;
	}
	
	public static void setCanStart(boolean b) {
		canStart = b;
	}
	
	public static int getMinPlayersToStart() {
		return minPlayersToStart;
	}
	
}
