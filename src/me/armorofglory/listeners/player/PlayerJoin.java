package me.armorofglory.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.GUI;
import me.armorofglory.handlers.Game;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.ChatUtils;
import me.armorofglory.utils.LocationUtils;

public class PlayerJoin implements Listener {
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		
		Player player = event.getPlayer();
	
		
		if (Bukkit.getOnlinePlayers().size() >= Game.getMinPlayersToStart()) {
			
			// Do this if the players online is equal to or greater than minPlayersToStart
			Game.setCanStart(true);
			
		} else {
			
			// Do this if the players online is less than minPlayersToStart
			// ChatUtils.broadcast(ConfigAccessor.getStringWithColor("Messages.Errors.notEnoughPlayersOnline"));
		}
		
		// If no lobby location is set do this
		if(ConfigAccessor.getString("Locations.Lobby").isEmpty() != true) {
		
			// Teleport player to the lobby location
			LocationUtils.teleportToLobby(player);
			
		} else {
			
			ChatUtils.broadcast(ChatColor.RED + "Error: There's no lobby spawn defined!");
		}
		
		// Do this when player first joins server
		player.setHealth(20);
		player.setFoodLevel(20);
		GUI.giveDefaultItems(player);
		
		ScoreboardManager.updateLobbyboard();
				
		// Join message
		event.setJoinMessage(ChatColor.GRAY + player.getName() + ChatColor.GOLD  + 
			" has joined (" + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size() + ChatColor.GOLD + 
			"/" + ChatColor.YELLOW + "10" + ChatColor.GOLD + ")");
				
		
	}
	
		
	
}
