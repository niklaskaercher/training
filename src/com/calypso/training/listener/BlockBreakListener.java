package com.calypso.training.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.calypso.training.Training;

public class BlockBreakListener implements Listener {
	
	@EventHandler 
	public void onBreak(BlockBreakEvent e) {
		
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
 
	private void lobbyActions(BlockBreakEvent e) {
		
		e.setCancelled(true);
		
	}
	
	private void ingameActions(BlockBreakEvent e) {
		
	}
	
	private void endingActions(BlockBreakEvent e) {
		
	}
}
