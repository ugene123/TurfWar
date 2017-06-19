package me.armorofglory.listeners.player;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.armorofglory.Turfwar;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.mysql.MySQL;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;

public class BlockBreak implements Listener {
	
	private MySQL mysql = Turfwar.mysql;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		if (Game.hasStarted()) {
			
			Block block = event.getBlock();
			
			Player player = event.getPlayer();
			Team team = Team.getPlayerTeam(player);
			
			// Get block broken in format as store in config
			// TODO: use block.getType().name();
			String BlockBroken = block.getTypeId() + ":" + block.getData();
			
			if (block.getType() == Material.GLASS) {
				ChatUtils.msgPlayer(player, ChatColor.RED + "You're not allowed to exit the arena!");
				event.setCancelled(true);
			}
			
			// If there are more team colors, add them here!
			if (team.getName().equals("RED")) {
				
				if (BlockBroken.equals(ConfigAccessor.getString("Teams.BLUE.TeamBlock"))) {
					team.addPoints(1);
					ScoreboardManager.updateGameboard();
					
					try {
						ResultSet resultturf = mysql.querySQL("SELECT turf_broken FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
						resultturf.next();
						int turf_broken = resultturf.getInt("turf_broken") + 1;
						mysql.updateSQL("UPDATE turfwar SET turf_broken = " + turf_broken + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
						ResultSet resultpoints = mysql.querySQL("SELECT points FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
						resultpoints.next();
						int points = resultpoints.getInt("points") + 1;
						mysql.updateSQL("UPDATE turfwar SET points = " + points + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			} else if (team.getName().equals("BLUE")){
				
				if (BlockBroken.equals(ConfigAccessor.getString("Teams.RED.TeamBlock"))) {
					team.addPoints(1);
					ScoreboardManager.updateGameboard();
					
					try {
						ResultSet resultturf = mysql.querySQL("SELECT turf_broken FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
						resultturf.next();
						int turf_broken = resultturf.getInt("turf_broken") + 1;
						mysql.updateSQL("UPDATE turfwar SET turf_broken = " + turf_broken + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
						ResultSet resultpoints = mysql.querySQL("SELECT points FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
						resultpoints.next();
						int points = resultpoints.getInt("points") + 1;
						mysql.updateSQL("UPDATE turfwar SET points = " + points + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
		} else {
			
//			event.setCancelled(true);
			Player player = event.getPlayer();
			ChatUtils.msgPlayer(player, ChatColor.RED + "You can't break blocks here!");
			
		}
	}
	
}
