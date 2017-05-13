package me.armorofglory.messageboard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String[] help = new String[] {
			"Vote Help",
			"/vote new ... (must be an OP)",
			"/vote stop (must be an OP)",
			"/vote <option>"
		};
		
		// if sender is not a player
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command must be be executed by a player.");
			return false;
		}
		
		Player player = (Player) sender;
		Vote vote = Vote.get();
		
		if (args.length == 0) {
			sender.sendMessage(help);
			
		} else if (args[0].equals("new")) {
			// TODO new vote
			
		} else if (args[0].equals("stop")) {
			// TODO stop vote
			
		} else {
			if (vote == null) {
				player.sendMessage("There are no active votes");
				
			} else if (!vote.isOpen()) {
				player.sendMessage("The vote has been closed");
				
			}else if (!vote.hasPlayer(player)) {
				player.sendMessage("You are not allowed to vote");
				
			} else if (!vote.isOption(args[0])) {
				player.sendMessage("Selection " + args[1] + "is not a valid voting option");
				
			} else {
				vote.castVote(player, args[0]);
				
				if (vote.hasVoted(player)) {
					player.sendMessage("Changed vote to " + args[0]);
				} else {
					player.sendMessage("Casted a vote for " + args[0]);
				}
			}
		}
		
		return false;
	}
	
}
