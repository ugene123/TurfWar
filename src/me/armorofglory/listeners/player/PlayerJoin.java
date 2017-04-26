package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import me.armorofglory.Warfare;
import me.armorofglory.handlers.Game;
import me.armorofglory.listeners.MGListener;
import me.armorofglory.utils.ChatUtilities;
import me.armorofglory.utils.LocationUtilities;

public class PlayerJoin extends MGListener {

	public PlayerJoin(Warfare pl) {
		super(pl);
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		int minPlayersToStart = 2;
		Game.setCanStart(Bukkit.getOnlinePlayers().size() >= minPlayersToStart);
		if (Bukkit.getOnlinePlayers().size() < minPlayersToStart)
			ChatUtilities.broadcast(ChatColor.DARK_RED + " Insufficient players to start the game!");
		LocationUtilities.teleportToSpawn(event.getPlayer());
	}
}
