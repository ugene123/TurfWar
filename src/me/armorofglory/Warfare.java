package me.armorofglory;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.armorofglory.listeners.player.AsyncPlayerPreLogin;
import me.armorofglory.listeners.player.PlayerDeath;
import me.armorofglory.listeners.player.PlayerJoin;
import me.armorofglory.listeners.player.PlayerQuit;
import me.armorofglory.threads.StartCountdown;

public class Warfare extends JavaPlugin {
	
	public void onEnable() {
		GameState.setState(GameState.LOBBY);
		registerListeners();
		startCountdown();

	}
	
	public void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerQuit(this), this);
		pm.registerEvents(new AsyncPlayerPreLogin(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
	}
	
	public void startCountdown() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new StartCountdown(),201, 201);
	}
}
