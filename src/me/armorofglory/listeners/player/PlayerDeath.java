package me.armorofglory.listeners.player;


import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Points;
import me.armorofglory.handlers.Team;
import me.armorofglory.utils.ChatUtils;

public class PlayerDeath implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if(event.getEntityType() == EntityType.PLAYER) {
			ChatUtils.msgPlayer(player, ChatColor.RED + "You were killed by " + player.getKiller().getName());
			
			Team lootingTeam = Team.getPlayerTeam(player.getKiller());
			
			int pointsPerKill = ConfigAccessor.getInt("Settings.Points.pointsPerKill");
			lootingTeam.addPoints(pointsPerKill);
			ChatUtils.msgPlayer(player.getKiller(), "You scored team " + lootingTeam.getName() + " " + pointsPerKill + " points!" );
			
		}
        
	}
}


