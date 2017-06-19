package me.armorofglory.listeners.player;


import java.sql.ResultSet;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.armorofglory.Turfwar;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Team;
import me.armorofglory.mysql.MySQL;
import me.armorofglory.utils.ChatUtils;

public class PlayerDeath implements Listener {
	
	private MySQL mysql = Turfwar.mysql;
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if(event.getEntityType() == EntityType.PLAYER) {
			ChatUtils.msgPlayer(player, ChatColor.RED + "You were killed by " + player.getKiller().getName());
			
			Team lootingTeam = Team.getPlayerTeam(player.getKiller());
			
			int pointsPerKill = ConfigAccessor.getInt("Settings.Points.pointsPerKill");
			lootingTeam.addPoints(pointsPerKill);
			ChatUtils.msgPlayer(player.getKiller(), "You scored team " + lootingTeam.getName() + " " + pointsPerKill + " points!" );
			
			try {
				// record kills
				ResultSet resultk = mysql.querySQL("SELECT kills FROM turfwar WHERE uuid = '" + player.getKiller().getUniqueId().toString() + "'");
				resultk.next();
				int kills = resultk.getInt("kills") + 1;
				mysql.updateSQL("UPDATE turfwar SET kills = " + kills + " WHERE uuid = '" + player.getKiller().getUniqueId().toString() + "'");
				// record deaths 
				ResultSet resultd = mysql.querySQL("SELECT deaths FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
				resultd.next();
				int deaths = resultd.getInt("deaths") + 1;
				mysql.updateSQL("UPDATE turfwar SET deaths = " + deaths + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
				// record points
				ResultSet resultp = mysql.querySQL("SELECT points FROM turfwar WHERE uuid = '" + player.getKiller().getUniqueId().toString() + "'");
				resultp.next();
				int points = resultp.getInt("points") + 10;
				mysql.updateSQL("UPDATE turfwar SET points= " + points + " WHERE uuid = '" + player.getKiller().getUniqueId().toString() + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
        
	}
}


