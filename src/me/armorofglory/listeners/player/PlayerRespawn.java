package me.armorofglory.listeners.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.armorofglory.GameState;
import me.armorofglory.handlers.Armor;
import me.armorofglory.handlers.Team;
import me.armorofglory.utils.LocationUtils;

public class PlayerRespawn implements Listener {
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = (Player) event.getPlayer();
        
		if(GameState.isState(GameState.IN_GAME)){
        	
        	Team team = Team.getPlayerTeam(player);
        	Location spawn = team.getSpawn();
            event.setRespawnLocation(spawn);
            Armor.setArmor(Team.getPlayerTeam(player).getColor(), player);
        	
        } else {
        	
    		event.setRespawnLocation(LocationUtils.lobbySpawn);
        }
        
	}
}

