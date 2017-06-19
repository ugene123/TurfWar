package me.armorofglory;

import java.sql.Connection;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
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
import me.armorofglory.menu.Button;
import me.armorofglory.menu.Menu;
import me.armorofglory.menu.VoteAction;
import me.armorofglory.menu.VoteMenu;
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
		
		createMenu();
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
	    
		pm.registerEvents(Menu.listener, plugin);
		pm.registerEvents(Button.listener, plugin);
	}
	
	private ItemStack mapVoteButton;
	private VoteMenu mapVote;
	
	private void createMenu() {
		// create the voting menu
		mapVote = new VoteMenu("Vote", 3);
		mapVote.addOption(1, 1, Material.MAP, "Map 1");
		mapVote.addOption(1, 3, Material.MAP, "Map 2");
		mapVote.addOption(1, 5, Material.MAP, "Map 3");
		mapVote.addOption(1, 7, Material.MAP, "Map 4");
		mapVoteButton = mapVote.createButton(Material.NETHER_STAR,
				ChatColor.GREEN + "Map Selector " + ChatColor.GRAY + ChatColor.ITALIC + "(Right click)");
		
		// start the vote
		mapVote.start(30, new VoteAction() {
			@Override
			public void onComplete(String winner) {
				Bukkit.broadcastMessage(winner + " is the winner!");
			}
		});
		
		// give all online players the menu item
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().setItem(5, mapVoteButton);
		}
		
		// newly joined players will receive the menu item
		getServer().getPluginManager().registerEvents(new Listener() {
			@EventHandler
			public void onPlayerJoin(PlayerJoinEvent event) {
				event.getPlayer().getInventory().setItem(5, mapVoteButton);
			}
		}, this);
	}
	
	public void registerCommands() {
		this.getCommand("turfwar").setExecutor(new PluginCommandExecutor(this));
	}

	
}
