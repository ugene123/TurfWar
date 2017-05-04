package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class PlayerJoin implements Listener {
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	
		
		if (Bukkit.getOnlinePlayers().size() >= Game.getMinPlayersToStart()) {
			
			// Do this if the players online is equal to or greater than minPlayersToStart
			Game.setCanStart(true);
			
		} else {
			
			// Do this if the players online is less than minPlayersToStart
			ChatUtils.broadcast(ConfigAccessor.getString("Messages.Errors.notEnoughPlayersOnline"));
		}
		
		//Reset Player's hunger & hunger levels
		Player player = event.getPlayer();
		player.setHealth(20);
		player.setFoodLevel(20);
		
		// If no lobby location is set do this
		if(ConfigAccessor.getString("Locations.Lobby").isEmpty() != true) {
		
			// Teleport player to the lobby location
			LocationUtils.teleportToLobby(player);
			
		} 
		
		ScoreboardManager.getPlayersOnline();
		ScoreboardManager.updateLobbyboard();

		}
	
}
