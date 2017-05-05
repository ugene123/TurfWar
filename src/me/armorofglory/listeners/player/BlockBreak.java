package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Points;
import me.armorofglory.handlers.Team;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;

public class BlockBreak implements Listener {
	
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		if (Game.gethasStarted()) {
			
			Block block = event.getBlock();
			
			Player player = event.getPlayer();
			String team = Team.getTeam(player);
			
			// Get block broken in format as store in config
			String BlockBroken = block.getTypeId() + ":" + block.getData();
			
			if (block.getType() == Material.GLASS) {
				ChatUtils.msgPlayer(player, ChatColor.RED + "You're not allowed to exit the arena!");
				event.setCancelled(true);
			}
			
			
			// If there are more team colors, add them here!
			if (team.equals("RED")) {
				
				if (BlockBroken.equals(ConfigAccessor.getString("Teams.BLUE.TeamBlock"))) {
					Points.add(team, 1);
					ScoreboardManager.updateGameboard();
				}
				
			} else if (team.equals("BLUE")){
				
				if (BlockBroken.equals(ConfigAccessor.getString("Teams.RED.TeamBlock"))) {
					Points.add(team, 1);
					ScoreboardManager.updateGameboard();
				}
				
			}
			
			
		} else {
	
			event.setCancelled(true);
			Player player = event.getPlayer();
			ChatUtils.msgPlayer(player, ChatColor.RED + "You can't break blocks here!");
		}
	}
	
}
