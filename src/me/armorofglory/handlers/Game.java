package me.armorofglory.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.armorofglory.utils.LocationUtilities;

public class Game {
	
	private static boolean canStart = false;
	private static boolean hasStarted = false;
	
	public static void start() {
		hasStarted = true; 
		new Team("Red", new Location(Bukkit.getWorld("world"), -19, 61, 48));
		new Team("Blue", new Location(Bukkit.getWorld("world"), -17, 61, 31));
	
		int i = 0 ;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i > Team.getAllTeams().size())
				i = 0;
			Team.getAllTeams().get(i).add(player);
			LocationUtilities.teleportToGame(player,Team.getAllTeams().get(i));
			i++;
		}
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
