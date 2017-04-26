package me.armorofglory;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.armorofglory.listeners.player.PlayerJoin;
import me.armorofglory.listeners.player.PlayerQuit;
import me.armorofglory.threads.StartCountdown;

public class Warfare extends JavaPlugin {
	
	public void onEnable() {
		GameState.setState(GameState.LOBBY);
		new Thread(new StartCountdown()).start();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerQuit(this), this);

	}

}
