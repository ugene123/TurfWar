package me.armorofglory.threads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.armorofglory.GameState;

import me.armorofglory.handlers.Game;
import me.armorofglory.utils.ChatUtilities;

public class StartCountdown implements Runnable {

	private static int timeUntilStart;

	public void run(){
		while(true){
			
			if (GameState.isState(GameState.LOBBY))
				if(Game.canStart()){
					ChatUtilities.broadcast(" Minimum players reached! Countdown starting!");
					for(timeUntilStart = 60;timeUntilStart>=0;timeUntilStart--){
						if(!Game.canStart()){
							ChatUtilities.broadcast(" Not enough players needed to start the game!");
							break;
						}
						if(timeUntilStart==0){
							Game.start();
							GameState.setState(GameState.IN_GAME);
							ChatUtilities.broadcast(ChatColor.GOLD + " Game has started!" );
							break;
							
						}
						if(timeUntilStart % 10 == 0 || timeUntilStart < 10){
							ChatUtilities.broadcast(ChatColor.DARK_GRAY + " (" + ChatColor.YELLOW + timeUntilStart + ChatColor.DARK_GRAY + ")" + ChatColor.WHITE + " second(s) until the game starts!");
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e){
							e.printStackTrace();
							Bukkit.shutdown();
						}
					} 
				}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e){
				e.printStackTrace();
				Bukkit.shutdown();
			}
		}
	}
}

