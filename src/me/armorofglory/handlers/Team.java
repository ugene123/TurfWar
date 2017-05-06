package me.armorofglory.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;



public class Team {
	
	private static List<String> allTeams = new ArrayList<>();
	
	private static HashMap<String, Location> teamLocations = new HashMap<String, Location>();
	
	private static HashMap<String, String> playerTeams = new HashMap<String, String>();
	
	public String teamname;
	
	
	public Team(String teamName){
		this.teamname = teamName;
		allTeams.add(teamName);
	}
	
	public static void addPlayer(Player player, String team) {
		playerTeams.put(player.getName(), team);
	}
	
	public static void removePlayer(Player player) {
		if(hasTeam(player) == true)
			playerTeams.remove(player.getName());
	}
	
	public static boolean hasTeam(Player player) {
		return playerTeams.containsKey(player.getName());
	}
	
	public static String getTeam(Player player) {
		if(hasTeam(player) == true) {
			return playerTeams.get(player.getName());
		}
		else if (hasTeam(player) == false) {
			return null;
		}
		return null;
	}
	
	public static void addSpawn(String team, Location loc){
		teamLocations.put(team, loc);
	}
	
	public String getTeamName(){
		return teamname;
	}
	
	
	/*
	 * THIS IS ALL TO ACCESS ALLTEAMS LIST 
	 */
	
	public static void registerAllTeams(){
		allTeams = ConfigAccessor.getList("Settings.allTeams");
	}
	
	public static void backupAllTeams(){
		ConfigAccessor.storeList("Settings.allTeams", allTeams);
	}
	
	public static int getAllTeamsSize(){
		return allTeams.size();
	}
	
	public static List<String> getAllTeams() {
		return allTeams;
	}
	
	public static String getAllTeamsIndex(int index) {
		return allTeams.get(index);
	}
	
	public static void removeTeam(String team) {
		allTeams.remove(team);
	}
	
	public static boolean containsInAllTeams(String team) {
		return allTeams.contains(team);
	}
	
	/*
	 *  THIS IS TO ACCESS TEAMLOCATIONS HASHMAP
	 */
	
	public static void addTeamLocation(String team, Location location) {
		teamLocations.put(team, location);
	}
	
}

