package me.armorofglory.messageboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.armorofglory.tools.Timer;

public class Vote {
	
	/*
	 * Class functionality
	 */
	
	private static Vote current = null;
	
	public static Vote get() {
		return current;
	}
	
	public static void set(Vote v) {
		current = v;
	}
	
	public static Vote make(String[] options, int time) {
		return make(new ArrayList<>(Arrays.asList(options)), time);
	}
	
	public static Vote make(ArrayList<String> options, int time) {
		current = new Vote(options, time);
		return current;
	}
	
	
	
	/*
	 * Object fields and methods
	 */
	
	private Timer timer;
	private boolean open = false;

	private List<String> options;
	private ArrayList<Integer> votes;
	private HashMap<Player, Integer> players = new HashMap<>();
	
	private Vote(ArrayList<String> options, int time) {
		this.options = options;
		
		this.timer = new Timer(time, 0) {
			@Override
			protected void check(int time) {
				checkTime(time);
			}
		};
	}
	
	private void checkTime(int time) {
		if (time == 0) {
			open = false;
		}
	}
	
	
	
	public void start() {
		open = true;
		timer.start();
	}
	
	public void addPlayer(Player player) {
		if (!hasPlayer(player)) {
			players.put(player, null);
		}
	}
	
	public void addPlayers(Iterable<? extends Player> players) {
		for (Player p : players) {
			addPlayer(p);
		}
	}
	
	public void addPlayers(Collection<? extends Player> players) {
		players.forEach(new Consumer<Player>() {
			@Override
			public void accept(Player p) {
				addPlayer(p);
			}
		});
	}
	
	public boolean hasPlayer(Player player) {
		return players.containsKey(player);
	}
	
	public void removePlayer(Player player) {
		if (players.containsKey(player)) {
			players.remove(player);
		}
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public boolean hasVoted(Player p) {
		if (!hasPlayer(p)) {
			return false;
		} else if (players.get(p) == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isOption(String str) {
		if (options.contains(str)) {
			return true;
		} else if (str.length() == 1) {
			int n = (int) str.charAt(0) - (int) 'a';
			return n >= 0 && n < options.size();
		} else {
			return false;
		}
	}
	
	private int getOptionIndex(String str) {
		if (options.contains(str)) {
			return options.indexOf(str);
			
		} else if (str.length() == 1) {
			int n = (int) str.charAt(0) - (int) 'a';
			if (n >= 0 && n < options.size()) {
				return n;
			}
		}
		
		return -1;
	}
	
	public void castVote(Player p, String str) {
		if (hasPlayer(p) & isOption(str)) {
			int i = getOptionIndex(str);
			if (hasVoted(p)) {
				votes.set(i, votes.get(i) - 1);
			}
			players.put(p, i);
			votes.set(i, votes.get(i) + 1);
		}
	}
	
}
