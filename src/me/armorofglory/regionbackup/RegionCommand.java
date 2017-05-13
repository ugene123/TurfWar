package me.armorofglory.regionbackup;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// if sender is not a player
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command must be be executed by a player.");
			return false;
		}
		
		Player player = (Player) sender;
		
		// if the player isn't an OP
		if (!player.isOp()) {
			player.sendMessage("You must be an OP to use this command.");
			
			// if there aren't any arguments
		} else if (args.length == 0) {
			sender.sendMessage("Usage: reg (save | reset | add | set | del | list | show)");
			
			// adding a new region
		} else if (args[0].equals("add")) {
			if (args.length == 1) {
				player.sendMessage("Usage: reg add <name>");
				
			} else {
				Region.add(args[1], player.getWorld());
				player.sendMessage("Added region '" + args[1] + "'");
			}
			
			// deleting a region
		} else if (args[0].equals("del")) {
			if (args.length == 1) {
				player.sendMessage("Usage: reg del <name>");
				
			} else if (Region.has(args[1])) {
				Region.del(args[1]);
				player.sendMessage("Deleted region '" + args[1] + "'");
				
			} else {
				player.sendMessage("Region '" + args[1] + "' does not exist");
			}
			
			// listing all regions
		} else if (args[0].equals("list")) {
			Set<String> names = Region.list();
			
			if (names.size() == 0) {
				player.sendMessage("No regions to list");
				
			} else {
				player.sendMessage("Listing regions:");
				for (String name : names) {
					player.sendMessage("  - " + name);
				}
			}
			
			// show region details
		} else if (args[0].equals("show")) {
			if (args.length == 1) {
				player.sendMessage("Usage: reg show <name>");
				
			} else if (Region.has(args[1])) {
				Region reg = Region.get(args[1]);
				String s;
				
				player.sendMessage(args[1] + ":");
				player.sendMessage("  world: " + reg.getWorld().getName());
				
				s = reg.getCorner1() == null ? "null" : reg.getCorner1().toString();
				player.sendMessage("  corner1: " + s);
				
				s = reg.getCorner2() == null ? "null" : reg.getCorner2().toString();
				player.sendMessage("  corner2: " + s);
				
				s = reg.isActive() ? "true" : "false";
				player.sendMessage("  active: " + s);
				
			} else {
				player.sendMessage("Region '" + args[1] + "' does not exist");
			}
			
			// set region corners
		} else if (args[0].equals("set")) {
			if (args.length < 3) {
				player.sendMessage("Usage: reg set <name> (1 | 2)");
				
			} else if (Region.has(args[1])) {
				Region reg = Region.get(args[1]);
				Location loc = player.getTargetBlock((Set<Material>) null, 5).getLocation();
				
				if (args[2].equals("1")) {
					if (reg.setCorner1(loc)) {
						player.sendMessage("Corner 1 set");
					} else {
						player.sendMessage("Failed to set corner 1");
					}
					
				} else if (args[2].equals("2")) {
					if (reg.setCorner2(loc)) {
						player.sendMessage("Corner 2 set");
					} else {
						player.sendMessage("Failed to set corner 2");
					}
					
				} else {
					player.sendMessage("Usage: reg set <name> (1 | 2)");
				}
				
			} else {
				player.sendMessage("Region '" + args[1] + "' does not exist");
			}
			
			// saving region data
		} else if (args[0].equals("save")) {
			if (args.length == 1) {
				player.sendMessage("Usage: reg save <name>");
				
			} else if (Region.has(args[1])) {
				if (Region.get(args[1]).save()) {
					player.sendMessage("Saved region '" + args[1] + "'");
				} else {
					player.sendMessage("Unable to save region '" + args[1] + "'");
				}
				
			} else {
				player.sendMessage("Region '" + args[1] + "' does not exist");
			}
			
			// resetting a region
		} else if (args[0].equals("reset")) {
			if (args.length == 1) {
				player.sendMessage("Usage: reg reset <name>");
				
			} else if (Region.has(args[1])) {
				int n = Region.get(args[1]).reset();
				player.sendMessage("Reset " + n + " blocks in '" + args[1] + "'");
				
			} else {
				player.sendMessage("Region '" + args[1] + "' does not exist");
			}
			
			// invalid command
		} else {
			sender.sendMessage("Usage: reg (save | reset | add | set | del | list | show)");
			return false;
		}
		
		return false;
	}
	
}
