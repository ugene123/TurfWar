package me.armorofglory.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.utils.LocationUtils;



public class Team {
	
	/*
	 * Static fields
	 */
	
	private static List<Team> allTeams = new ArrayList<>();
	private static HashMap<String, Team> teamNames = new HashMap<>();
	private static HashMap<String, Team> playerTeams = new HashMap<>();
	
	/*
	 * Instance fields
	 */
	
	private String teamName;
	private Color teamColor;
	private int points = 0;
	private Location spawnLocation = null;
	private List<Player> players = new ArrayList<>();
	
	/*
	 * Constructors
	 */
	
	private Team(String teamName, Color teamColor) {
		this.teamName = teamName;
		this.teamColor = teamColor;
	}
	
	public static Team create(String teamName) {
		teamName = teamName.toUpperCase();
		if (hasTeam(teamName))
			return null;
		
		Color color = getColor(teamName);
		if (color == null)
			return null;
		
		Team team = new Team(teamName, color);
		allTeams.add(team);
		teamNames.put(teamName, team);
		return team;
	}
	
	private static Color getColor(String name) {
		switch (name) {
			case "RED":
				return Color.RED;
			case "BLUE":
				return Color.BLUE;
			case "YELLOW":
				return Color.YELLOW;
			case "GREEN":
				return Color.GREEN;
			default:
				return null;
		}
	}
	
	
	
	/*
	 * Getters
	 */
	
	public String getName() {
		return teamName;
	}
	
	public int getPoints() {
		return points;
	}
	
	public Color getColor() {
		return teamColor;
	}
	
	public Location getSpawn() {
		return spawnLocation;
	}

	public int getNumPlayers() {
		return players.size();
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	
	
	/*
	 * Setters
	 */
	
	public void setName(String name) {
		this.teamName = name;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setColor(Color color) {
		this.teamColor = color;
	}
	
	public void setSpawn(Location loc) {
		this.spawnLocation = loc;
	}
	
	
	
	/*
	 * Instance methods
	 */
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void subtractPoints(int points) {
		this.points -= points;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
		playerTeams.put(player.getName(), this);
	}

	public void removePlayer(Player player) {
		if(hasPlayer(player) == true) {
			players.remove(player);
			playerTeams.remove(player.getName());
		}
	}
	
	private void destroy() {
		for (Player p : players) {
			removePlayer(p);
		}
		
		allTeams.remove(this);
		teamNames.remove(this.getName());
	}
	
	
	
	/*
	 * Inherited methods
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Team) {
			Team t = (Team) obj;
			return getName().equals(t.getName());
		
		} else {
			return false;
		}
	}
	
	
	
	/*
	 * Static methods
	 */
	
	public static List<Team> getTeams() {
		return allTeams;
	}
	
	public static List<String> getTeamNames() {
		List<String> names = new ArrayList<>();
		for (Team t : allTeams) {
			names.add(t.getName());
		}
		return names;
	}
	
	public static int getNumTeams() {
		return allTeams.size();
	}
	
	public static Team getTeam(int index) {
		return allTeams.get(index);
	}
	
	public static Team getTeam(String teamName) {
		teamName = teamName.toUpperCase();
		if (teamNames.containsKey(teamName)) {
			return teamNames.get(teamName);
		} else {
			return null;
		}
	}
	
	public static Team getPlayerTeam(Player player) {
		return getPlayerTeam(player.getName());
	}
	
	public static Team getPlayerTeam(String playerName) {
		if (hasPlayer(playerName)) {
			return playerTeams.get(playerName);
		} else {
			return null;
		}
	}
	
	public static boolean hasPlayer(Player player) {
		return hasPlayer(player.getName());
	}
	
	public static boolean hasPlayer(String playerName) {
		return playerTeams.containsKey(playerName);
	}
	
	public static boolean hasTeam(String teamName) {
		return teamNames.containsKey(teamName.toUpperCase());
	}
	
	public static void removeTeam(String teamName) {
		getTeam(teamName.toUpperCase()).destroy();
	}

	public static void removeTeam(Team team) {
		team.destroy();
	}
	
	public static Team getWinner() {
		int maxScore = 0;
		Team winner = null;
		
		for (Team t : allTeams) {
			if (t.getPoints() >= maxScore) {
				maxScore = t.getPoints();
				winner = t;
			}
		}
		
		return winner;
	}
	
	
	
	
	/*
	 * THIS IS ALL TO ACCESS ALLTEAMS LIST 
	 */
	
	public static void registerAllTeams(){
		List<String> teamNames = ConfigAccessor.getList("Settings.allTeams");
		for (String name : teamNames) {
			Team team = Team.create(name);
			team.setSpawn(LocationUtils.getTeamSpawn(name));
		}
	}
	
	public static void backupAllTeams(){
		ConfigAccessor.storeList("Settings.allTeams", getTeamNames());
	}
	
}

