package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import me.armorofglory.GameState;
import me.armorofglory.Warfare;
import me.armorofglory.handlers.Game;
import me.armorofglory.listeners.MGListener;

public class PlayerQuit extends MGListener{

	public PlayerQuit(Warfare pl) {
		super(pl);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(GameState.isState(GameState.LOBBY)) {
			Game.setCanStart((Bukkit.getOnlinePlayers().size() - 1) >= 2);
		}
	}
}
