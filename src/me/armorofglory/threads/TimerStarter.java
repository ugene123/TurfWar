package me.armorofglory.threads;

import me.armorofglory.Turfwar;

public class TimerStarter {

	static Turfwar plugin;
	
	public TimerStarter(Turfwar plugin) {
		TimerStarter.plugin = plugin;
	
	}
	
	
	public static void start(){
		TimerManager manager = new TimerManager();
		manager.runTaskTimer(plugin, 0, 20);
		}

}
