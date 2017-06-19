package me.armorofglory.listeners.player;

import java.sql.ResultSet;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.armorofglory.Turfwar;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.mysql.MySQL;
import me.armorofglory.utils.ChatUtils;

public class BlockPlace implements Listener {
	
	private MySQL mysql = Turfwar.mysql;
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if (Game.hasStarted()) {
	
			Block block = event.getBlock();
			
			Player player = event.getPlayer();
			Team team = Team.getPlayerTeam(player);
		
			// Change to string as in config
			// TODO: use block.getType().name();
			String BlockPlaced = block.getTypeId() + ":" + block.getData();
			
			// If player tries to place their opponents block, prevents exploiting the game
			// If there are more teams, add here!
			if (team.getName().equals("RED")) {
				
				if (BlockPlaced.equals(ConfigAccessor.getString("Teams.BLUE.TeamBlock"))) {
					event.setCancelled(true);
				}  else {
					// track blocks placed
					try {
						ResultSet result = mysql.querySQL("SELECT turf_placed FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
						result.next();
						int turf_placed = result.getInt("turf_placed") + 1;
						mysql.updateSQL("UPDATE turfwar SET turf_placed = " + turf_placed + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} else if (team.getName().equals("BLUE")){
				
				if (BlockPlaced.equals(ConfigAccessor.getString("Teams.RED.TeamBlock"))) {
					event.setCancelled(true);
				} else {
					// track blocks placed
					try {
						ResultSet result = mysql.querySQL("SELECT turf_placed FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'");
						result.next();
						int turf_placed = result.getInt("turf_placed") + 1;
						mysql.updateSQL("UPDATE turfwar SET turf_placed = " + turf_placed + " WHERE uuid = '" + player.getUniqueId().toString() + "'");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} else {
	
//			event.setCancelled(true);
			Player player = event.getPlayer();
			ChatUtils.msgPlayer(player, ChatColor.RED + "You cannot place blocks here!");
		}
	}
	
}
