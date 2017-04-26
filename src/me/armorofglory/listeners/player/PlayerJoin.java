package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import me.armorofglory.Warfare;
import me.armorofglory.handlers.Game;
import me.armorofglory.listeners.MGListener;
import me.armorofglory.utils.ChatUtilities;

public class PlayerJoin extends MGListener {

	public PlayerJoin(Warfare pl) {
		super(pl);
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Game.setCanStart(Bukkit.getOnlinePlayers().size() >= 2);
		int online = Bukkit.getOnlinePlayers().size();
		ChatUtilities.broadcast(ChatColor.DARK_GRAY + " (" + ChatColor.YELLOW + online + ChatColor.DARK_GRAY + ") " + ChatColor.WHITE + "Player(s) Online");
		if (Bukkit.getOnlinePlayers().size() < 2)
			ChatUtilities.broadcast(ChatColor.DARK_RED + " Insufficient players to start the game!");
	}
}
