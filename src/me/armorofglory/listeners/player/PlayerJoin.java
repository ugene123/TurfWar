package me.armorofglory.listeners.player;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import me.armorofglory.Turfwar;
import me.armorofglory.handlers.GUI;
import me.armorofglory.handlers.Game;
import me.armorofglory.mysql.MySQL;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.utils.LocationUtils;

public class PlayerJoin implements Listener {
	
	private MySQL mysql = Turfwar.mysql;
	
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
		try {
			// Teleport player to the lobby location
			LocationUtils.teleportToLobby(player);
			
		} catch (Exception e) {
			
			Bukkit.getLogger().info("[TurfWar] " + player.getDisplayName() + " could not be teleported to "
					+ "spawn because there's no spawn point defined!");
		}
		
		// Do this when player first joins server
		player.setHealth(20);
		player.setFoodLevel(20);
		GUI.giveDefaultItems(player);
		Date date = new java.sql.Date(System.currentTimeMillis());
		
		try {
			// if player is not in table
			if (!mysql.querySQL("SELECT uuid FROM turfwar WHERE uuid = '" + player.getUniqueId().toString() + "'").next()){
				// create new row for table with default inputs	
				mysql.updateSQL("INSERT INTO turfwar (uuid,displayname,wins,defeats,points,kills,deaths,turf_broken,turf_placed,turf_deposited,last_played,games_played) VALUES "
						+ "('"+ player.getUniqueId() +"','" + player.getDisplayName() +"','0','0','0','0','0','0','0','0','" + date + "','0')"); 
			} 
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		
		
		ScoreboardManager.updateLobbyboard();
				
		// Join message
		event.setJoinMessage(ChatColor.GRAY + player.getName() + ChatColor.GOLD  + 
			" has joined (" + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size() + ChatColor.GOLD + 
			"/" + ChatColor.YELLOW + "10" + ChatColor.GOLD + ")");
				
		
	}
	
		
	
}
