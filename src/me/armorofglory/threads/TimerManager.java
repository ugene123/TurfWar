package me.armorofglory.threads;

import org.bukkit.scheduler.BukkitRunnable;

import me.armorofglory.GameState;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.score.ScoreboardManager;

public class TimerManager extends BukkitRunnable{
	 
	private static int counter = ConfigAccessor.getInt("Settings.gameDuration");
	
	public static void resetCounter(){
		counter = ConfigAccessor.getInt("Settings.gameDuration");
	}
	
	
	public void run(){
		
		if(GameState.isState(GameState.IN_GAME)) {
			
			if(Game.canStart()) {
				
				if(counter >= 0) {
					
					ScoreboardManager.setCounterTitle("Time Left:");
					
					if(counter > 20) {
						ScoreboardManager.setCounter("§r" + counter);
						
					} else if (counter > 10 && counter <= 20) {
						ScoreboardManager.setCounter("§6" + counter);
						
					} else if (counter <= 10 && counter > 0) {
						ScoreboardManager.setCounter("§c" + counter);
						
					} else if (counter == 0) {
						ScoreboardManager.setCounter("§c" + counter);
						Game.stop();
					
					} counter--;
				
				} else {
				
					this.cancel();
				
				}
			}
		}
			
	}
}
