package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import me.armorofglory.Warfare;
import me.armorofglory.handlers.Game;
import me.armorofglory.listeners.MGListener;

public class AsyncPlayerPreLogin extends MGListener {

	public AsyncPlayerPreLogin(Warfare pl) {
		super(pl);
	}
	
	@EventHandler
	public void playerPreLogin(AsyncPlayerPreLoginEvent event) {
		if(Game.gethasStarted())
			event.disallow(Result.KICK_OTHER, ChatColor.RED + "The game has already started!");
	}
}
