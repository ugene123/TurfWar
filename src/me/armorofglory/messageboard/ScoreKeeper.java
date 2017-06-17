package me.armorofglory.messageboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import me.armorofglory.tools.TextUtils;

public class ScoreKeeper {
	
	private HashMap<String, Integer> scores = new HashMap<>();
	private ArrayList<String> lines = new ArrayList<>();
	private String namePrefix = "";
	private String pointsPrefix = "";
	
	
	
	/*
	 * Getters
	 */
	
	public String getNamePrefix() {
		return namePrefix;
	}
	
	public String getPointsPrefix() {
		return pointsPrefix;
	}
	
	
	
	/*
	 * Setters
	 */
	
	public void setNamePrefix(ChatColor c) {
		this.namePrefix = c + "";
	}
	
	public void setPointsPrefix(ChatColor c) {
		this.pointsPrefix = c + "";
	}

	public void setNamePrefix(String str) {
		this.namePrefix = str;
	}
	
	public void setPointsPrefix(String str) {
		this.pointsPrefix = str;
	}
	
	
	
	/*
	 * Add or remove items
	 */
	
	public void add(String name) {
		add(name, 0);
	}

	public void add(ArrayList<String> names) {
		for (String name : names) {
			add(name, 0);
		}
	}
	
	public void add(String name, int points) {
		scores.put(name, points);
	}
	
	public void remove(String name) {
		scores.remove(name);
	}
	
	
	
	/*
	 * Set, get, or add points
	 */
	
	public void setPoints(String name, int points) {
		scores.put(name, points);
	}
	
	public int getPoints(String name) {
		if (scores.containsKey(name)) {
			return scores.get(name);
		} else {
			return 0;
		}
	}
	
	public void addPoints(String name, int points) {
		setPoints(name, getPoints(name) + points);
	}
	
	
	
	public void update() {
		ArrayList<Entry<String, Integer>> sorted = new ArrayList<Entry<String, Integer>>(scores.entrySet());
		Collections.sort(sorted, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		
		lines = new ArrayList<>();
		for (Entry<String, Integer> entry : sorted) {
			String key = entry.getKey();
			String val = entry.getValue().toString();
			int wkey = TextUtils.pixelWidth(key);
			int wval = TextUtils.pixelWidth(val);
			int wsp = TextUtils.pixelWidth(namePrefix);
			
			int spaces = 16*6;// mb.getPixelWidth() - wkey - wval - wsp;
			if (spaces < 8) spaces = 8; //FIXME
			if (spaces > 0) {
				lines.add(namePrefix + key + TextUtils.pixelRepeat(' ', spaces) + pointsPrefix + val);
			} else {
				lines.add(namePrefix + key);
				lines.add(TextUtils.pixelRepeat(' ', spaces + wkey) + pointsPrefix + val);
			}
		}
	}
	
}
