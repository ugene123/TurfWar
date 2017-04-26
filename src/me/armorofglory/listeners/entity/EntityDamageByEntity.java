package me.armorofglory.listeners.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.armorofglory.Warfare;
import me.armorofglory.handlers.Game;
import me.armorofglory.handlers.Team;
import me.armorofglory.listeners.MGListener;

public class EntityDamageByEntity extends MGListener {

	public EntityDamageByEntity(Warfare pl) {
		super(pl);
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) {
			event.setCancelled(true);
			return;
		}
		if(!Game.gethasStarted()){
			event.setCancelled(true);
			return;
		}
		Player player = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (Team.getTeam(player) == Team.getTeam(damager)){
			event.setCancelled(true);
		}
	}
}
