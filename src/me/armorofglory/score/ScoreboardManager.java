package me.armorofglory.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreboardManager {
	
	
	private static String GameState = "";
	
	private static String counter = "0";
	
	private static String counterTitle = "Countdown:";
	
	private static int onlinePlayers = 0;
	
	
	public static void update() {
		for(Player player : Bukkit.getOnlinePlayers()){
			// create a new scoreboard with title
		    SimpleScoreboard scoreboard = new SimpleScoreboard("§6-< §e§lTURFWAR §6>-");
		    // text with custom score
		    scoreboard.blankLine();
		    scoreboard.add("§eGameState:");
		    scoreboard.add(" " + GameState);
		    scoreboard.blankLine();
		    scoreboard.add("§e" + counterTitle);
		    scoreboard.add(" " + counter);
		    scoreboard.blankLine();
		    scoreboard.add("§ePlayers:");
		    scoreboard.add(" " + onlinePlayers + " / 10");
		    scoreboard.blankLine();
		    scoreboard.add("§ewww.turfwars.co");
		    // call this to create the scoreboard, nothing will happen if you forget to call this
		    scoreboard.build();
		    // send the scoreboard to the player(s), takes an array
		    scoreboard.send(player);
		}
		
	}
	
	public static void setGameState(String gamestate) {
		GameState = gamestate;
		update();
		
	}
	public static void setCounter(String string) {
		counter = string;
		update();
		
	}
	public static void setCounterTitle(String title) {
		counterTitle = title;
		update();
		
	}
	
	public static void getPlayersOnline() {
		onlinePlayers = Bukkit.getOnlinePlayers().size();
		update();
	}
	
}
