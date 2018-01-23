package com.calypso.training.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.calypso.training.Training;

public class PlayerDropItemListener implements Listener {

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		
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
	
	private void lobbyActions(PlayerDropItemEvent e) {
		
		e.setCancelled(true);
		
	}
	
	private void ingameActions(PlayerDropItemEvent e) {
		
	}
	
	private void endingActions(PlayerDropItemEvent e) {
		
	}
} 
