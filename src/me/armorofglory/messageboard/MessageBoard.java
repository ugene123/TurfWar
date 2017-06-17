package me.armorofglory.messageboard;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.armorofglory.tools.TextUtils;

public class MessageBoard {
	
	public static int MAX_WIDTH = 20;
	public static int DEFAULT_WIDTH = 16;
	
	private Scoreboard scoreboard;
	private Objective objective;
	private String title;
	
	private int width = 0;
	private int pixelWidth = 0;
	private ArrayList<String> lines = new ArrayList<>();
	
	public MessageBoard(String title) {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective(title, "dummy");
		setTitle(title);
		setWidth(DEFAULT_WIDTH);
	}
	
	/*
	 * Setters
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWidth(int width) {
		if (width <= 0 || width > MAX_WIDTH) {
			width = MAX_WIDTH;
		}
		this.width = width;
		this.pixelWidth = width * TextUtils.AVERAGE_WIDTH;
	}
	
	
	
	/*
	 * Getters
	 */
	
	public String getTitle() {
		return title;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getPixelWidth() {
		return pixelWidth;
	}
	
	
	
	/*
	 * Adding and setting lines
	 */
	
	public void clear() {
		lines = new ArrayList<>();
	}
	
	public void add() {
		add("");
	}
	
	public void add(String text) {
		lines.add(text);
	}
	
	public void set(int index, String text) {
		lines.set(index, text);
	}
	
	public void insert(int index, String text) {
		lines.add(index, text);
	}
	
	
	
	/*
	 * Update this messageboard (rebuilds the scoreboard)
	 */
	
	private String pad(String text) {
		return TextUtils.pixelPadRight(text, pixelWidth);
	}
	
	private void makeLine(String text, int num) {
		// TODO find non-deprecated way of accomplishing this
		
		Team team = scoreboard.getTeam("team" + num);
		if (team != null) {
			for (OfflinePlayer p : team.getPlayers()) {
				team.removePlayer(p);
			}
		} else {
			team = scoreboard.registerNewTeam("team" + num);
		}
		
		OfflinePlayer fakePlayer = Bukkit.getServer().getOfflinePlayer(text);
		team.addPlayer(fakePlayer);
		objective.getScore(fakePlayer).setScore(num);
	}
	
	public void update() {
		objective.unregister();
		objective = scoreboard.registerNewObjective(title, "dummy");
		
//		group.setBoard(this);
//		group.update();
//		ArrayList<String> lines = group.getLines();
		HashSet<String> set = new HashSet<>();
		
		int n = lines.size() - 1;
		for (String line : lines) {
			while (set.contains(line)) {
				line += "§r";
			}
			set.add(line);
			makeLine(pad(line), n);
			n--;
		}
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	
	
	/*
	 * Showing and hiding the messageboard
	 */
	
	public void show(Player player) {
		player.setScoreboard(scoreboard);
	}
	
	public void show(ArrayList<Player> players) {
		for (Player player : players) {
			show(player);
		}
	}
	
	public void show() {
		show(new ArrayList<Player>(Bukkit.getOnlinePlayers()));
	}
	
	public void hide() {
		scoreboard.clearSlot(DisplaySlot.SIDEBAR);
	}
	
}