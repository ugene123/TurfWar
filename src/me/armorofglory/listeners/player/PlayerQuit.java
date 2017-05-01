package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.armorofglory.GameState;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.threads.StartCountdown;
import me.armorofglory.utils.ChatUtils;

public class PlayerQuit implements Listener{
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		
		if(GameState.isState(GameState.LOBBY)) {
			
			if (Bukkit.getOnlinePlayers().size() - 1 >= Game.minPlayersToStart) {
				
				// Players online is greater than minPlayersToStart
				Game.setCanStart(true);
				
			} else {
				
				// Players online is less than minPlayersToStart
				Game.setCanStart(false);
				StartCountdown.resetCounter();
				ChatUtils.broadcast(ConfigAccessor.getString("Messages.Errors.notEnoughPlayersOnline"));
			}
	
		}
		
		Player player = event.getPlayer();
		
		if(Game.gethasStarted()){
//			Team.getTeam(player).remove(player);
		}
	}
}
