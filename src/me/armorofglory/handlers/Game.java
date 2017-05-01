package me.armorofglory.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.armorofglory.GameState;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class Game {
	
	
	public static int minPlayersToStart = ConfigAccessor.getInt("Settings.minPlayersToStart");
	private static boolean canStart = false;
	private static boolean hasStarted = false;
	
	public static void start() {
		
		GameState.setState(GameState.IN_GAME);
		ChatUtils.broadcast(ChatColor.GOLD + " Game has started!" );
		hasStarted = true; 
		
		
		
		
		Bukkit.getLogger().info(Integer.toString(Team.allTeams.size()));
	
	//	int i = 0 ;
//		for (Player player : Bukkit.getOnlinePlayers()) {
//			if (i > Team.getAllTeams().size())
//				i = 0;
//			Team.getAllTeams().get(i).add(player);
//			LocationUtils.teleportToGame(player,Team.getAllTeams().get(i));
//			i++;
//		}
		
	}
	public static void stop(Team team) {
		hasStarted = false;
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
}
