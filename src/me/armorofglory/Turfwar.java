package me.armorofglory;


import java.sql.Connection;
import java.sql.SQLException;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.armorofglory.commands.PluginCommandExecutor;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Arena;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.listeners.entity.EntityDamageByEntity;
import me.armorofglory.listeners.player.BlockBreak;
import me.armorofglory.listeners.player.BlockPlace;
import me.armorofglory.listeners.player.InventoryClick;
import me.armorofglory.listeners.player.AsyncPlayerPreLogin;
import me.armorofglory.listeners.player.PlayerDeath;
import me.armorofglory.listeners.player.PlayerJoin;
import me.armorofglory.listeners.player.PlayerQuit;
import me.armorofglory.listeners.player.PlayerRespawn;
import me.armorofglory.listeners.player.SignChange;
import me.armorofglory.listeners.player.PlayerInteract;
import me.armorofglory.score.ScoreboardManager;
import me.armorofglory.threads.CountdownStarter;
import me.armorofglory.threads.TimerStarter;
import me.armorofglory.mysql.MySQL;

public class Turfwar extends JavaPlugin {
	
	MySQL MySQL = new MySQL("namysql.ggservers.com", "3306", "p82_2591", "p82_2591", "9ff46baad9");
	Connection connection = null;
	
	private static Plugin plugin; 
	
	public static Plugin getPlugin() {
		return plugin;
		
	}
	
	
	
	public void onEnable() {
		plugin = this;
		
		// register Events & Commands 
		registerEvents();
		registerCommands();
		
		// Load config
		ConfigAccessor.loadConfiguration();
		
		// Reset game to lobby and cannot start
		GameState.setState(GameState.LOBBY);
		Game.setCanStart(false);
		
		// Pull allTeams array from config
		Team.registerAllTeams();
		
		// register GameTimer
		new TimerStarter(this);
		
		// register startcountdown and start thread
		new CountdownStarter(this);
		CountdownStarter.start();
		
		ScoreboardManager.updateLobbyboard();
		
		Arena.save();
		
		// Connect to Database
		try {
			connection = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	public void onDisable() {
		Team.backupAllTeams();
		Arena.reset();
		plugin = null;
	}
	
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(new PlayerJoin(), this);
	    pm.registerEvents(new PlayerQuit(), this);
	    pm.registerEvents(new PlayerDeath(), this);
	    pm.registerEvents(new AsyncPlayerPreLogin(), this);
	    pm.registerEvents(new PlayerRespawn(), this);
	    pm.registerEvents(new EntityDamageByEntity(), this);
	    pm.registerEvents(new BlockBreak(), this);
	    pm.registerEvents(new BlockPlace(), this);
	    pm.registerEvents(new PlayerInteract(), this);
	    pm.registerEvents(new SignChange(), this);
	    pm.registerEvents(new InventoryClick(), this);
	}
	
	
	public void registerCommands() {
		this.getCommand("turfwar").setExecutor(new PluginCommandExecutor(this));
	}

	
}
