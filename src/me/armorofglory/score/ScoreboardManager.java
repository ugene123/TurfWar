package me.armorofglory.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.armorofglory.handlers.Team;


public class ScoreboardManager {
	
	
	private static String GameState = "";
	
	private static String counter = "Paused";

	
	
	public static void updateLobbyboard() {
		for(Player player : Bukkit.getOnlinePlayers()){
			// create a new scoreboard with title
		    SimpleScoreboard scoreboard = new SimpleScoreboard("§6-< §e§lTURFWAR §6>-");
		    // text with custom score
		    scoreboard.blankLine();
		    scoreboard.add("§eGameState:");
		    scoreboard.add(" " + GameState);
		    scoreboard.blankLine();
		    scoreboard.add("§eCountdown:");
		    scoreboard.add(" " + counter);
		    scoreboard.blankLine();
		    scoreboard.blankLine();
		    scoreboard.add("§ewww.turfwars.co");
		    // call this to create the scoreboard, nothing will happen if you forget to call this
		    scoreboard.build();
		    // send the scoreboard to the player(s), takes an array
		    scoreboard.send(player);
		}
		
	}
	
	public static void updateGameboard() {
		for(Player player : Bukkit.getOnlinePlayers()){
			// create a new scoreboard with title
		    SimpleScoreboard scoreboard = new SimpleScoreboard("§6-< §e§lTURFWAR §6>-");
		    // text with custom score
		    scoreboard.blankLine();
		    scoreboard.add("§eGameState:");
		    scoreboard.add(" " + GameState);
		    scoreboard.blankLine();
		    scoreboard.add("§eTime Remaining:" );
		    scoreboard.add(" " + counter);
		    scoreboard.blankLine();
		    scoreboard.add("§eScore:" );
		    scoreboard.add(" Blue: " + Team.getTeam("BLUE").getPoints());
		    scoreboard.add(" Red: " + Team.getTeam("RED").getPoints());
		    scoreboard.blankLine();
		    scoreboard.add("§ewww.turfwars.co");
		    // call this to create the scoreboard, nothing will happen if you forget to call this
		    scoreboard.build();
		    // send the scoreboard to the player(s), takes an array
		    scoreboard.send(player);
		}
		
	}
	
	public static void updatePostboard() {
		for(Player player : Bukkit.getOnlinePlayers()){
			// create a new scoreboard with title
		    SimpleScoreboard scoreboard = new SimpleScoreboard("§6-< §e§lTURFWAR §6>-");
		    // text with custom score
		    scoreboard.blankLine();
		    scoreboard.add("§eGameState:");
		    scoreboard.add(" " + GameState);
		    scoreboard.blankLine();
		    scoreboard.add("§eWinning Team:" );
		    scoreboard.add(" " + Team.getWinner().getName());
		    scoreboard.blankLine();
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
		
	}
	public static void setCounter(String string) {
		counter = string;
		
	}
	
}
