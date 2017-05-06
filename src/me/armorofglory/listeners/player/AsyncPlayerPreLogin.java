package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import me.armorofglory.handlers.Game;

public class AsyncPlayerPreLogin implements Listener {
	
	@EventHandler
	public void playerPreLogin(AsyncPlayerPreLoginEvent event) {
		if(Game.hasStarted())
			event.disallow(Result.KICK_OTHER, ChatColor.RED + "The game has already started!");
	}
}
