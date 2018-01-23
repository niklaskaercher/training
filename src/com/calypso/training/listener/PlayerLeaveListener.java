package com.calypso.training.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.calypso.training.Training;
import com.calypso.training.game.Team;
import com.calypso.training.phase.LobbyPhase;

public class PlayerLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
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

	public void lobbyActions(PlayerQuitEvent e) {
		
		Player player = e.getPlayer();
		
		if(Team.getTeam(player) != null) Team.getTeam(player).getPlayers().remove(player);
		
		LobbyPhase.getVoting().removeVote(player);
		
		if(LobbyPhase.isStarted() && Bukkit.getOnlinePlayers().size() < 3) LobbyPhase.stop();
		
		updateScoreboard();
		
	}

	public void ingameActions(PlayerQuitEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void endingActions(PlayerQuitEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateScoreboard() {
		//todo
	}
	
}
