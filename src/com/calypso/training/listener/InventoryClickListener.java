package com.calypso.training.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.calypso.training.Training;
import com.calypso.training.phase.LobbyPhase;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
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
	
	private void lobbyActions(InventoryClickEvent e) {
		
		e.setCancelled(true);
		
		Player player = (Player) e.getWhoClicked();
		
		if(e.getClickedInventory().getName().contains("Voting")) {
			
			if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
				
				String s = e.getCurrentItem().getItemMeta().getDisplayName();
				s.replaceAll("§a", "");
				LobbyPhase.voting.vote(player, s);
				player.closeInventory();
				
			}
			
		}
		
	}
	
	private void ingameActions(InventoryClickEvent e) {
		
	}
	
	private void endingActions(InventoryClickEvent e) {
		
	}
}
