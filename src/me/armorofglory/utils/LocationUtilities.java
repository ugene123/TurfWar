package me.armorofglory.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.armorofglory.handlers.Team;

public class LocationUtilities {
	
	private static Location spawnLocation = new Location(Bukkit.getWorld("world"), -14.5, 66, 36.5);
	
	public static void teleportToSpawn(Player player) {
		player.teleport(spawnLocation);
	}
	
	public static void teleportAllToSpawn() {
		for (Player p : Bukkit.getOnlinePlayers())
			teleportToSpawn(p);
	}
	
	public static void teleportToGame(Player player, Team team) {
		player.teleport(team.getSpawn());
	}
	
}
