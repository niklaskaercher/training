package com.calypso.training.phase;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.calypso.training.Training;
import com.calypso.training.game.GameMap;
import com.calypso.training.game.GameMode;
import com.calypso.training.game.GameState;
import com.calypso.training.game.Team;

public class IngamePhase {

	private static GameMode mode;
	private static GameMap map;
	
	public static void start(GameMode selected) {
		
		mode = selected;
		map = selectMap(mode);
		Training.game.setState(GameState.RUNNING);
		
		beginningActions();
		
	}
	
	private static void beginningActions() {
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			if(Team.getTeam(player) == null) {
				
				getFreeTeam().getPlayers().add(player);
				
			}
			
			player.sendMessage(Training.prefix + "Map: " + map.getName() + " von " + map.getBuilder());
			
			Training.game.addPlayer(player);
			
			//scoreboard update
			//tp
			
			
		}
		
		teamActions();
		
		
		
	}
	
	private static GameMap selectMap(GameMode mode) {
		
		List<GameMap> maps = GameMap.getMaps(mode);
		
		if(maps.size() < 2) return maps.get(0);
		
		Random r = new Random();
		
		int i = r.nextInt(maps.size() - 1);
		
		return maps.get(i);
		
	}
	
	private static Team getFreeTeam() {
		
		Team lessPlayers = null;
		int size = 0;
		
		for(Team team : Team.getTeams()) {
			
			if(team.getPlayers().size() > size) {
				
				if(team.getPlayers().size() < team.getSize()) lessPlayers = team;
				
			}
			
		}
		
		return lessPlayers;
	}
	
	private static void teamActions() {
		
		Random r = new Random();
		
		int i = r.nextInt(Team.getTeams().size() - 1);
		
		Team attacking = Team.getTeams().get(i);
		Team defending = null;
		
		for(Team t : Team.getTeams()) {
			
			if(attacking != t) defending = t;
			
		}
		
		for(Team t : Team.getTeams()) {
			
			if(t == attacking) {
				
				int j = 0;
				
				for(Player player : t.getPlayers()) {
					
					Map<Integer, ItemStack> contents = mode.getAttackInv().get(j);
					
					for(Entry<Integer, ItemStack> entry : contents.entrySet()) {
						
						player.getInventory().setItem(entry.getKey(), entry.getValue());
						
					}
					
					player.teleport(map.getAttackSpawn());
					
					j++;
					
				}
				
			}
			
			if(t == defending) {
				
				int j = 0;
				
				for(Player player : t.getPlayers()) {
					
					Map<Integer, ItemStack> contents = mode.getDefInv().get(j);
					
					for(Entry<Integer, ItemStack> entry : contents.entrySet()) {
						
						player.getInventory().setItem(entry.getKey(), entry.getValue());
						
					}
					
					player.teleport(map.getDefendSpawn());
					
					j++;
					
				}
				
			}
			
		}
		
	}
}
