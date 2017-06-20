package me.armorofglory.handlers;


import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.armorofglory.GameState;
import me.armorofglory.Turfwar;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.mysql.MySQL;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.threads.TimerStarter;
import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class Game {
	
	private static MySQL mysql = Turfwar.mysql;
	
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
	
			try {
			
				Team team = Team.getTeam(i);
				team.addPlayer(player);
				
				try {
					player.teleport(team.getSpawn());
					player.getInventory().clear();
					Armor.setArmor(team.getColor(), player);
					i++;
					
				} catch (Exception e) {
					Bukkit.getLogger().info("[TurfWar] " + player.getDisplayName() + 
							" could not be teleported to " + team.getName() + 
							" spawn because the team spawn has not been set!");
				}

			} catch (Exception e) {
				Bukkit.getLogger().info("[TurfWar] No teams are defined!");
			
			}
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
			
			if(Team.getPlayerTeam(player) == Team.getWinner()) {
				
				try {
					ResultSet result = mysql.querySQL("SELECT wins FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
					result.next();
					int wins = result.getInt("wins") + 1;
					mysql.updateSQL("UPDATE turfwar SET wins = " + wins + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				
				try {
					ResultSet result = mysql.querySQL("SELECT defeats FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
					result.next();
					int defeats = result.getInt("defeats") + 1;
					mysql.updateSQL("UPDATE turfwar SET defeats = " + defeats + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				ResultSet result = mysql.querySQL("SELECT games_played FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
				result.next();
				int games_played = result.getInt("games_played") + 1;
				mysql.updateSQL("UPDATE turfwar SET games_played = " + games_played + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
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
