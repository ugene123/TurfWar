package me.armorofglory;

import java.util.ArrayList;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.armorofglory.commands.PluginCommandExecutor;
import me.armorofglory.config.ConfigAccessor;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.listeners.player.AsyncPlayerPreLogin;
import me.armorofglory.listeners.player.PlayerDeath;
import me.armorofglory.listeners.player.PlayerJoin;
import me.armorofglory.listeners.player.PlayerQuit;
import me.armorofglory.threads.StartCountdown;

public class Warfare extends JavaPlugin {
	
	private static Plugin plugin; 
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	StartCountdown startcountdown;
	
	public void onEnable() {
		plugin = this;
		registerEvents();
		registerCommands();
		ConfigAccessor.loadConfiguration();
		GameState.setState(GameState.LOBBY);
		Game.setCanStart(false);
		startcountdown = new StartCountdown(this);
		startcountdown.runTaskTimer(this, 1 * 20, 1 * 20);
		ArrayList<String> allTeams = ConfigAccessor.getString("Teams.allTeams"));
	}
	
	public void onDisable() {
		plugin = null;
	}
	
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(new PlayerJoin(), this);
	    pm.registerEvents(new PlayerQuit(), this);
	    pm.registerEvents(new PlayerDeath(), this);
	    pm.registerEvents(new AsyncPlayerPreLogin(), this);
	}
	
	
	public void registerCommands() {
		this.getCommand("turfwar").setExecutor(new PluginCommandExecutor(this));
	}

	
}
