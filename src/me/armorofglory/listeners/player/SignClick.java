package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Team;
import me.armorofglory.utils.ChatUtils;

public class SignClick implements Listener {
	
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		// Cancel player trying to break the sign
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(event.getClickedBlock().getState() instanceof Sign ) {
				if(player.getGameMode().equals(GameMode.SURVIVAL)) {
					event.setCancelled(true);
				}
				
			}
		}
		
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		
			if(event.getClickedBlock().getState() instanceof Sign ) {
				
				Sign sign  = (Sign) event.getClickedBlock().getState();			
			
				Team team = Team.getPlayerTeam(player);
				
				if (sign.getLine(0).equalsIgnoreCase("[" + ChatColor.DARK_BLUE + ChatColor.BOLD + "BANK" + ChatColor.BLACK + "]")) {
					
					PlayerInventory items = player.getInventory();
					int multiplier = ConfigAccessor.getInt("Settings.Points.pointsPerDeposit");
					int TotalBlocks = 0;
					
					
					if (sign.getLine(2).equals(ChatColor.DARK_RED + "RED TEAM")) {
					
						if (team.getName().equals("RED")) {
							for (int i = 0 ; i < 36 ; i++) {
								
								if(items.getItem(i) != null) {
									ItemStack item = items.getItem(i);
									@SuppressWarnings("deprecation")
									String BlockInInventory = item.getTypeId() + ":" + item.getData().getData();
									String OppositeTeamBlock = ConfigAccessor.getString("Teams.BLUE.TeamBlock");
							
									if(BlockInInventory.equals(OppositeTeamBlock)) {
										
										team.addPoints(item.getAmount() * multiplier);
										TotalBlocks += item.getAmount();
									
										player.getInventory().getItem(i).setAmount(0);
									
									}
								}
							}
							
							if (TotalBlocks != 0) {
								
								ChatUtils.msgPlayer(player, "You've deposited a total of " + TotalBlocks + " turf for " + (TotalBlocks * multiplier) + " points!");
							
							} else {
								
								ChatUtils.msgPlayer(player, "You don't have any blocks to deposit!");
							}
					
						} else {
							
							ChatUtils.msgPlayer(player, "You can only use your team bank!");
					
						}
										
				
					} else if (sign.getLine(2).equals(ChatColor.DARK_BLUE + "BLUE TEAM")) {
						
						if (team.getName().equals("BLUE")) {
							
							for (int i = 0 ; i < 36 ; i++) {
								
								if(items.getItem(i) != null) {
									ItemStack item = items.getItem(i);
									@SuppressWarnings("deprecation")
									String BlockInInventory = item.getTypeId() + ":" + item.getData().getData();
									String OppositeTeamBlock = ConfigAccessor.getString("Teams.RED.TeamBlock");
							
									if(BlockInInventory.equals(OppositeTeamBlock)) {
										
										team.addPoints(item.getAmount() * multiplier);
										TotalBlocks += item.getAmount();
									
										player.getInventory().getItem(i).setAmount(0);
									
									}
								}
							} 
							
							if (TotalBlocks != 0) {
								
								ChatUtils.msgPlayer(player, "You've deposited a total of " + TotalBlocks + " turf for " + (TotalBlocks * multiplier) + " points!");
							
							} else {
								
								ChatUtils.msgPlayer(player, "You don't have any blocks to deposit!");
							}
						
						} else {
						
							ChatUtils.msgPlayer(player, "You can only use your team bank!");
						
						}			
					}
					
				}
			}	
		}
		
	}
}
