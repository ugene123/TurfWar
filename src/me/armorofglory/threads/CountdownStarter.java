package me.armorofglory.threads;

import me.armorofglory.Turfwar;

public class CountdownStarter {

	static Turfwar plugin;
	
	public CountdownStarter(Turfwar plugin) {
		CountdownStarter.plugin = plugin;
	
	}
	
	
	public static void start(){
		CountdownManager manager = new CountdownManager();
		manager.runTaskTimer(plugin, 0, 20);
		}

}
