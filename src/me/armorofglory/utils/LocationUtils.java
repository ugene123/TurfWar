package me.armorofglory.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Team;

public class LocationUtils {
	
	public static Location lobbySpawn = stringToLocation(ConfigAccessor.getString("Locations.Lobby"));
	
	public static Location getTeamSpawn(String team) {
		
		String StringLocation = ConfigAccessor.getString("Teams." + team.toUpperCase() + ".Location");
		Location TeamSpawn = LocationUtils.stringToLocation(StringLocation);
		return TeamSpawn;
	}
	
	
	
	public static void teleportToLobby(Player player) {
		player.teleport(lobbySpawn);
	}
	
	public static void teleportAllToLobby() {
		for (Player p : Bukkit.getOnlinePlayers())
			teleportToLobby(p);
	}
	
	public static void teleportToGame(Player player, Team team) {
		player.teleport(team.getSpawn());
	}
	
	public static Location stringToLocation(String string) {
        
		// Converts the string in config to a useable bukkit location
		String[] split = string.split(", ");
        
		// Location must include 6 args: world, x, y, z, yaw, pitch
		if(split.length == 6){
        	
			// Create a new loc by splitting up the args with ', '
			Location loc = new Location(Bukkit.getWorld(split[0]), 
        			Double.parseDouble(split[1]), Double.parseDouble(split[2]), 
        			Double.parseDouble(split[3]), Float.parseFloat(split[4]), 
        			Float.parseFloat(split[5]));
        	
			return loc;
			
		} else if(split.length == 4){
			
			// Create a new loc by splitting up the args with ', '
			Location loc = new Location(Bukkit.getWorld(split[0]), 
        			Double.parseDouble(split[1]), Double.parseDouble(split[2]), 
        			Double.parseDouble(split[3]));
			return loc;
			
        } else {
        	return null;
        }
    }
	
}
