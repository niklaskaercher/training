package com.calypso.training.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.calypso.training.Training;
import com.calypso.training.phase.LobbyPhase;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
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
	
	private void lobbyActions(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		
		player.setHealth(20);
		player.setSaturation(25);
		player.setScoreboard(null);
		
		LobbyPhase.setContents(player);
		
		if(canStart()) {
			
			LobbyPhase.start();
			
		}
		
	}
	
	private void ingameActions(PlayerJoinEvent e) {
		
	}
	
	private void endingActions(PlayerJoinEvent e) {
		
	}
	
	private boolean canStart() {
		
		return !LobbyPhase.isStarted() && Bukkit.getOnlinePlayers().size() > 1;
		
	}
}
