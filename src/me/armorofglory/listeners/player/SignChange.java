package me.armorofglory.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.armorofglory.utils.ChatUtils;

public class SignChange implements Listener {

	
	@EventHandler
	public void onSignChange(SignChangeEvent sign) {
		
		Player player = sign.getPlayer();
		if(sign.getLine(0).equalsIgnoreCase("[bank]")) {
			sign.setLine(0, "[" + ChatColor.DARK_BLUE + ChatColor.BOLD + "BANK" + ChatColor.BLACK + "]");
			sign.setLine(1, " > " + ChatColor.BOLD + "Deposit" + ChatColor.RESET + " <");
			sign.setLine(3, "Right Click");
	
		if(sign.getLine(2).equalsIgnoreCase("red")) {
			sign.setLine(2, ChatColor.DARK_RED + "RED TEAM");
		
		} else if(sign.getLine(2).equalsIgnoreCase("blue")) {
			sign.setLine(2, ChatColor.DARK_BLUE + "BLUE TEAM");
		
		} else if(sign.getLine(2).equalsIgnoreCase("green")) {
			sign.setLine(2, ChatColor.DARK_GREEN + "GREEN TEAM");
		
		} else if(sign.getLine(2).equalsIgnoreCase("yellow")) {
			sign.setLine(2, ChatColor.GOLD + "YELLOW TEAM");
		
			} 
		
		}
		
		ChatUtils.msgPlayer(player, "Sign created!");
	}
}
