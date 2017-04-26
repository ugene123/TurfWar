package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.armorofglory.Warfare;
import me.armorofglory.handlers.Team;
import me.armorofglory.listeners.MGListener;
import me.armorofglory.utils.ChatUtilities;
import me.armorofglory.utils.LocationUtilities;

public class PlayerDeath extends MGListener {

	public PlayerDeath(Warfare pl) {
		super(pl);
	}

	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Team.getTeam(player).remove(player);
		
		LocationUtilities.teleportToSpawn(player);
		ChatUtilities.broadcastPlayer(player, ChatColor.RED + " You died!");
	}
	
}
