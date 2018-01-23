package com.calypso.training.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.calypso.training.Training;

public class PlayerDamageListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		switch(Training.game.getState()) {
		case LOBBY:
			lobbyActions(e);
			break;
		case RUNNING:
			ingameActions(e);
			break;
		case ENDING:
			endingActions(e);
			break;
		}
		
	}
	
	private void lobbyActions(EntityDamageEvent e) {
		
		e.setCancelled(true);
		
	}
	
	private void ingameActions(EntityDamageEvent e) {
		
	}
	
	private void endingActions(EntityDamageEvent e) {
		
	}
}
