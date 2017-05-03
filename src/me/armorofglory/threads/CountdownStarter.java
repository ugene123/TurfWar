package me.armorofglory.threads;

import me.armorofglory.Warfare;

public class CountdownStarter {

	static Warfare plugin;
	
	public CountdownStarter(Warfare plugin) {
		CountdownStarter.plugin = plugin;
	
	}
	
	
	public static void start(){
		CountdownManager manager = new CountdownManager();
		manager.runTaskTimer(plugin, 0, 20);
		}

}
