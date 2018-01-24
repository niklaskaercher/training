package com.calypso.training.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.calypso.training.Training;

public class SpecManager {

	public List<Player> specs;
	public boolean activated;
	
	public SpecManager(boolean activated) {
		
		this.activated = activated;
		this.specs = new ArrayList<>();
		
	}

	public List<Player> getSpecs() {
		return specs;
	}

	public void setSpecs(List<Player> specs) {
		this.specs = specs;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isSpec(Player player) {
		
		return specs.contains(player);
		
	}
	
	public void makeSpec(Player player) {
		
		specs.add(player);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			p.hidePlayer(p);
			
		}

		player.setHealth(20);
		player.setSaturation(25);
		player.getInventory().clear();
		player.setAllowFlight(true);
		player.setFlying(true);
		player.spigot().setCollidesWithEntities(false);
		player.sendMessage(Training.prefix + "Du bist nun Spectator.");
		
		//portspecloc
		
		
	}
	
	public void removeSpec(Player player) {
		
		specs.remove(player);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			p.hidePlayer(p);
			
		}
		
		player.setAllowFlight(false);
		player.setFlying(false);
		player.spigot().setCollidesWithEntities(true);
		
		
	}
	
	public void hidePlayers(Player player) {
		
		for(Player p : specs) {
			
			player.hidePlayer(p);
			
		}
		
	}
	
	public void clearSpecs() {
		
		for(Player player : specs) {
			
			removeSpec(player);
			
		}
		
	}
	
	public void updateSpecs() {
		
		for(Player player : specs) {
			
			if(Training.game.getPlayers().contains(player)) {
				
				removeSpec(player);
				
			}
			
		}
		
	}
	
}
