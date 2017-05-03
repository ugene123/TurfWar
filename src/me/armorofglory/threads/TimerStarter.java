package me.armorofglory.threads;

import me.armorofglory.Warfare;

public class TimerStarter {

	static Warfare plugin;
	
	public TimerStarter(Warfare plugin) {
		TimerStarter.plugin = plugin;
	
	}
	
	
	public static void start(){
		TimerManager manager = new TimerManager();
		manager.runTaskTimer(plugin, 0, 20);
		}

}
