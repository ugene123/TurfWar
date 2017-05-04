package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.armorofglory.GameState;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.threads.CountdownManager;
import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class PlayerQuit implements Listener{
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		
		if(GameState.isState(GameState.LOBBY)) {
			
			if (Bukkit.getOnlinePlayers().size() - 1 >= Game.getMinPlayersToStart()) {
				
				// Players online is greater than minPlayersToStart
				Game.setCanStart(true);
				
				
			} else {
				
				// Players online is less than minPlayersToStart
				Game.setCanStart(false);
				CountdownManager.resetCounter();
				ChatUtils.broadcast(ConfigAccessor.getString("Messages.Errors.notEnoughPlayersOnline"));
				}
			
		}
		
		if (GameState.isState(GameState.IN_GAME)) {
				
				if (!(Bukkit.getOnlinePlayers().size() - 1 >= Game.getMinPlayersToStart())) {
					for (Player player : Bukkit.getOnlinePlayers()){
						LocationUtils.teleportToLobby(player);
						GameState.setState(GameState.LOBBY);
						ChatUtils.msgPlayer(player, "There's not enough players online!");
					}

				}
		}
		
		Player player = event.getPlayer();
		
		if(GameState.isState(GameState.IN_GAME)){
			if(Team.hasTeam(player))
				Team.removePlayer(player);
		}
		
		ScoreboardManager.getPlayersOnline();
		ScoreboardManager.updateLobbyboard();
	}
}
