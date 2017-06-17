package me.armorofglory.menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.armorofglory.tools.Timer;

public class VoteMenu extends Menu {
	
	private Timer timer;
	private boolean active = false;
	private HashMap<String, String> originals = new HashMap<>();
	private HashMap<String, Integer> options = new HashMap<>();
	private HashMap<String, Integer> positions = new HashMap<>();
	private HashMap<UUID, String> voters = new HashMap<>();
	
	public VoteMenu(String title, int rows) {
		super(title, rows);
	}
	
	public void start(int time, VoteAction action) {
		if (active == true || options.size() == 0)
			return;
		
		this.timer = new Timer(time, 0) {
			@Override
			protected void check(int time) {
				if (time > 5 && time % 10 == 0) {
					Bukkit.broadcastMessage(ChatColor.RED + "" + time + " seconds left!");
				} else if (time > 0 && time <= 5) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "" + time + "...");
				} else if (time == 0) {
					Bukkit.broadcastMessage(ChatColor.GREEN + "" + "Start!");
					active = false;
					action.onComplete(getWinner());
				}
			}
		};
		
		active = true;
		timer.start();
	}
	
	public void addOption(int r, int c, Material type, String name) {
		ItemStack item = Button.create(type, name, new ButtonHandler() {
			@Override
			public void onPress(HumanEntity player, ItemStack item) {
				if (active && isOption(item)) {
					if (voters.containsKey(getUUID(player))) {
						removeVote(player);
					}
					addVote(player, item.getItemMeta().getDisplayName());
				}
			}
		});
		
		String newname = item.getItemMeta().getDisplayName();
		set(r, c, item);
		positions.put(newname, getPosition(r, c));
		originals.put(newname, name);
		options.put(newname, 0);
		setLore(newname, new String[] {
			"Votes: 0"
		});
	}
	
	private String getWinner() {
		int max = 0;
		String winner = null;
		for (String name : options.keySet()) {
			if (options.get(name) >= max) {
				max = options.get(name);
				winner = originals.get(name);
			}
		}
		return winner;
	}
	
	private void addVote(HumanEntity player, String name) {
		int amt = options.get(name) + 1;
		options.put(name, amt);
		
		voters.put(getUUID(player), name);
		setLore(name, new String[] {
			"Votes: " + amt
		});
	}
	
	private void removeVote(HumanEntity player) {
		UUID id = getUUID(player);
		
		String name = voters.get(id);
		int amt = options.get(name) - 1;
		options.put(name, amt);
		
		voters.remove(id);
		setLore(name, new String[] {
			"Votes: " + amt
		});
	}
	
	private void setLore(String name, String[] text) {
		ItemStack item = inventory.getItem(positions.get(name));
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(text));
		item.setItemMeta(meta);
		update();
	}
	
	private boolean isOption(ItemStack item) {
		if (item != null && item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null) {
			return options.containsKey(getName(item));
		} else {
			return false;
		}
	}
	
	private String getName(ItemStack item) {
		return item.getItemMeta().getDisplayName();
	}
	
	private UUID getUUID(HumanEntity player) {
		return ((Player) player).getUniqueId();
	}
	
}
