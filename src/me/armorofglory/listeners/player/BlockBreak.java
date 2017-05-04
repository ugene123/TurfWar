package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Arena;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Points;
import me.armorofglory.handlers.Team;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;

public class BlockBreak implements Listener {
	
	
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		if (Game.gethasStarted()) {
			
			Location loc = event.getBlock().getLocation();
			Material material = event.getBlock().getType();
			Arena.addChangedBlock(loc, material);
			
			
			Player player = event.getPlayer();
			String team = Team.getTeam(player);
			
			if (team.equals("RED")) {
				
				if (event.getBlock().getType().toString().equals(ConfigAccessor.getString("Teams.BLUE.TeamBlock"))) {
					Points.addPoint(team);
					ScoreboardManager.updateGameboard();
				}
				
			} else if (team.equals("BLUE")){
				
				if (event.getBlock().getType().toString().equals(ConfigAccessor.getString("Teams.RED.TeamBlock"))) {
					Points.addPoint(team);
					ScoreboardManager.updateGameboard();
				}
				
			}
			
		} else {
	
			event.setCancelled(true);
			Player player = event.getPlayer();
			ChatUtils.msgPlayer(player, ChatColor.RED + "You cannot break blocks here!");
		}
	}
	
}
