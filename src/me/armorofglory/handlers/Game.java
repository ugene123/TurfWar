package me.armorofglory.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Game {
	
	private static boolean canStart = false;
	
	public static void start() {
		new Team("Red");
		new Team("Blue");
	
		int i = 0 ;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i > Team.getAllTeams().size())
				i = 0;
			Team.getTeam(Team.getAllTeams().get(i++).getName()).add(player);
		}
	}
	public static void stop() {
		
	}
	
	public static boolean canStart() {
		return canStart;
	}
	
	public static void setCanStart(boolean b) {
		canStart = b;
	}
}
