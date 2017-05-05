package me.armorofglory.handlers;

import java.util.List;
import java.util.TreeMap;

import me.armorofglory.config.ConfigAccessor;

public class Points {
	
	private static int TeamCount = ConfigAccessor.getList("Settings.allTeams").size();
	public static TreeMap<String, Integer> Score = new TreeMap<>();
	private static List<String> TeamList = ConfigAccessor.getList("Settings.allTeams");
	
	
	public static void registerTeams() {
			
			// Gets the team from config and stores it into Points TreeMap
			for(int i = 0 ; i < TeamCount ; i++) {
				Score.put(TeamList.get(i), 0);
			}
			
		}
		
		public static void add(String team, int Amount) {
			Score.put(team, Score.get(team) + Amount);
		}
		
		public static void subtract(String team, int Amount) {
			Score.put(team, Score.get(team) - Amount);
		}
		
		public static int getPoints(String teamName) {
			return Score.get(teamName);
		}
		
		
		public static String getWinningTeam() { 
			int red = Score.get("RED");
			int blue =  Score.get("BLUE");
			
			if (red == blue) {
				return "TIE";
			} else if (blue > red)
				return "BLUE";
			else {
				return "RED";
			}
		}
		
		
		/*
		 *  For multiple teams to find highest max value, does support ties.
		 */
//		public static String getMapKeyWithHighestValue(TreeMap<String, Integer> map) {
//		    String keyWithHighestVal = "";
//
//		    // getting the maximum value in the Hashmap
//		    int maxValueInMap = (Collections.max(map.values()));
//
//		    //iterate through the map to get the key that corresponds to the maximum value in the Hashmap
//		    for (Entry<String, Integer> entry : map.entrySet()) {  // Iterate through hashmap
//		        if (entry.getValue() == maxValueInMap) {
//
//		            keyWithHighestVal = entry.getKey();     // this is the key which has the max value
//		        }
//
//		    }
//		    return keyWithHighestVal;
//		}
}
