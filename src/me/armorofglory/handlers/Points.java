package me.armorofglory.handlers;

import java.util.List;
import java.util.TreeMap;

import me.armorofglory.config.ConfigAccessor;

public class Points {
	
	private static int TeamCount = ConfigAccessor.getList("Settings.allTeams").size();
	public static TreeMap<String, Integer> Points = new TreeMap<>();
	private static List<String> TeamList = ConfigAccessor.getList("Settings.allTeams");
	
	
	public static void registerTeams() {
			
			// Gets the team from config and stores it into Points TreeMap
			for(int i = 0 ; i < TeamCount ; i++) {
				Points.put(TeamList.get(i), 0);
			}
			
		}
		
		public static void addPoint(String team) {
			Points.put(team, Points.get(team) + 1);
		}
		
		public static void subtractPoint(String team) {
			Points.put(team, Points.get(team) - 1);
		}
		
		public static int getPoints(String teamName) {
			return Points.get(teamName);
		}
}
