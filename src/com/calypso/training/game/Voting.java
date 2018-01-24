package com.calypso.training.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.calypso.training.Training;

public class Voting {

	private GameState state;
	private Map<GameMode, Integer> votes;
	private Map<Player, GameMode> playerVotes;
	private GameMode selected;
	private List<Player> voted;
	private Player currentVoter;
	
	public Voting(GameState state) {
		
		this.state = state;
		this.voted = new ArrayList<>();
		
	}
	
	public void startNewRound() {
		
		this.selected = null;
		
		if(this.state == GameState.LOBBY) {
			
			playerVotes = new HashMap<>();
			votes = new HashMap<>();
			
			for(GameMode mode : GameMode.getModes()) {
				
				votes.put(mode, 0);
				
			}
			
		}
		
		if(this.state == GameState.RUNNING) {
			
			List<Player> players = Training.game.getPlayers();
			Random r = new Random();
			
			while(this.currentVoter == null) {
				
				int i = r.nextInt(players.size());
				
				if(!voted.contains(players.get(i))) {
					
					this.currentVoter = players.get(i);
					this.voted.add(players.get(i));
					
				}
				
			}
			
			openVotingInv(currentVoter);
			
		}
		
	}
	
	public GameMode getSelected() {
		
		if(state == GameState.LOBBY) {
			
			if(voted.size() == 0) {
				
				return selectRandom();
				
			}
			
			GameMode mostMode = null;
			int mostVotes = 0;
			
			
			for(Entry<GameMode, Integer> mode : votes.entrySet()) {
				if(mode.getValue() > mostVotes) {
					mostMode = mode.getKey();
					mostVotes = mode.getValue();
				}
			} 
			
			return mostMode;
		}
		
		if(state == GameState.RUNNING) {
			
			if(selected != null) return selected;
			
			return selectRandom();
			
		}
		
		return null; 
		
	}
	
	public GameMode selectRandom() {
		
		Random r = new Random();
		
		int i = r.nextInt(GameMode.getModes().size());
		
		return GameMode.getModes().get(i);
		
	}
	
	public boolean hasVoted(Player player) {
		return voted.contains(player);
	}
	
	public void openVotingInv(Player player) {
		
		int invSize = GameMode.getModes().size();
		
		while(invSize % 9 != 0) {
			invSize++;
		}
		
		Inventory inv = Bukkit.createInventory(player, invSize, "§aVoting");
		
		int i = 0;
		
		for(GameMode mode : GameMode.getModes()) {
			
			ItemStack icon = mode.getIcon();
			icon.getItemMeta().setDisplayName("§a" + mode.getName());
			icon.getItemMeta().setLore(mode.getDesc());
			
			inv.setItem(i, icon);
			
			i++;
		}
		
		player.openInventory(inv);
		
	}
	
	public void vote(Player player, String vote) {
		
		GameMode mode = GameMode.getMode(vote);
		
		if(state == GameState.LOBBY) {
			
			if(!hasVoted(player)) {
				
				playerVotes.put(player, mode);
				votes.put(mode, votes.get(mode)+ 1);
				
			} else {
				
				removeVote(player);
				vote(player, vote);
				
			}
			
		}
		
		if(state == GameState.RUNNING) {
			
			if(currentVoter == player) {
				
				selected = mode;
				
			}
			
		}
		
	}
	
	public void removeVote(Player player) {
		
		if(hasVoted(player)) {
			
			voted.remove(player);
			
			if(playerVotes != null) {
				
				GameMode mode = playerVotes.get(player);
				playerVotes.remove(player);
				votes.put(mode, votes.get(mode) - 1);
				
			}
			
		}
		
	}
	
}
