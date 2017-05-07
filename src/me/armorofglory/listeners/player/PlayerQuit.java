package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			
			// If players online falls under threshold, forcestop the game
			if (Bukkit.getOnlinePlayers().size() - 1 < 2) {
				Game.forcestop();
				ChatUtils.broadcast(ChatColor.RED + "Game has been stopped!");
				ScoreboardManager.updateLobbyboard();
				
			}
			
		}
		
		Player player = event.getPlayer();

		// Remove player from team when he leaves the server
		if(GameState.isState(GameState.IN_GAME)) {
			if(Team.hasPlayer(player)) {
				Team.getPlayerTeam(player).removePlayer(player);
			}
		}
		
	}
}
