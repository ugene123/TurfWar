package me.armorofglory.listeners.player;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class PlayerDeath implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
//		Team.getTeam(player).remove(player);
		LocationUtils.teleportToLobby(player);
        ChatUtils.msgPlayer(player, ChatColor.RED + "You died!");
	}
}

