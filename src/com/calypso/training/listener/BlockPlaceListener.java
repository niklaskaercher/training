package com.calypso.training.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.calypso.training.Training;

public class BlockPlaceListener implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		
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
	
	private void lobbyActions(BlockPlaceEvent e) {
		
		e.setCancelled(true);
		
	}
	
	private void ingameActions(BlockPlaceEvent e) {
		
	}
	
	private void endingActions(BlockPlaceEvent e) {
		
	}
}
