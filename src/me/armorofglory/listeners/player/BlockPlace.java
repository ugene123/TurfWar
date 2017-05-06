package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.utils.ChatUtils;

public class BlockPlace implements Listener {
	
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if (Game.gethasStarted()) {
	
			Block block = event.getBlock();
			
			Player player = event.getPlayer();
			String team = Team.getTeam(player);
		
			// Change to string as in config
			String BlockPlaced = block.getTypeId() + ":" + block.getData();
			
		
			// If player tries to place their opponents block, prevents exploiting the game
			// If there are more teams, add here!
			if (team.equals("RED")) {
				
				if (BlockPlaced.equals(ConfigAccessor.getString("Teams.BLUE.TeamBlock")))
					event.setCancelled(true);
	
				
			} else if (team.equals("BLUE")){
				
				if (BlockPlaced.equals(ConfigAccessor.getString("Teams.RED.TeamBlock"))) 
					event.setCancelled(true);
				
			}	
			
		} else {
	
//			event.setCancelled(true);
			Player player = event.getPlayer();
			ChatUtils.msgPlayer(player, ChatColor.RED + "You cannot place blocks here!");
		}
	}
	
}
