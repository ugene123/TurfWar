package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.armorofglory.GameState;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.GUI;
import me.armorofglory.handlers.Team;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;

public class PlayerInteract implements Listener {
	
	
	@EventHandler
	public void onP(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(GameState.getState().equals(GameState.IN_GAME)) {
			
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
										
											player.getInventory().remove(item);
										
										
										}
									}
								}
								
								if (TotalBlocks != 0) {
									
									ChatUtils.msgPlayer(player, ChatColor.GREEN + "You've deposited a total of " + TotalBlocks + " turf for " + (TotalBlocks * multiplier) + " points!");
									ScoreboardManager.updateGameboard();
									
								} else {
									
									ChatUtils.msgPlayer(player, ChatColor.RED + "You don't have any blocks to deposit!");
								}
						
							} else {
								
								ChatUtils.msgPlayer(player, ChatColor.RED + "You can only use your team bank!");
						
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
										
											player.getInventory().remove(item);
										
										}
									}
								} 
								
								if (TotalBlocks != 0) {
									
									ChatUtils.msgPlayer(player, ChatColor.GREEN + "You've deposited a total of " + TotalBlocks + " turf for " + (TotalBlocks * multiplier) + " points!");
									ScoreboardManager.updateGameboard();
									
								} else {
									
									ChatUtils.msgPlayer(player, ChatColor.RED + "You don't have any blocks to deposit!");
								}
							
							} else {
							
								ChatUtils.msgPlayer(player, ChatColor.RED + "You can only use your team bank!");
							
							}			
						}
						
					}
				}	
			}
			
		} else {
			
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				if(event.getClickedBlock().getState() instanceof Sign ) {
						ChatUtils.msgPlayer(player, ChatColor.RED + "You can only use the bank in-game!");
				}
			}
		} 
		
		if(GameState.getState().equals(GameState.LOBBY) || GameState.getState().equals(GameState.POST_GAME)) {
			
			ItemStack item = event.getItem();
			
			if(event.getAction() == Action.PHYSICAL || item == null || item.getType().equals(Material.AIR)) {
				return;
			}
			
			if(item.getType() == Material.EMERALD){
				GUI.openShop(player);
			}
			
			if(item.getType() == Material.NETHER_STAR) {
				GUI.openVoteMenu(player);
			}
			
		}
	}
	
}
