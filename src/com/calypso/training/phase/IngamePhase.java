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
import com.calypso.training.game.Voting;
import com.calypso.training.util.Countdown;

public class IngamePhase {

	private static GameMode mode;
	private static GameMap map;
	private static Voting voting;
	private static Team attacking;
	private static Team defending;
	private static Runnable endCallBack;
	private static Countdown countdown;
	public static boolean winner = false;
	
	public static void start(GameMode selected) {
		
		mode = selected;
		voting = new Voting(GameState.RUNNING);
		Training.game.setState(GameState.RUNNING);
		
		beginningActions();
		
	}
	
	private static void beginningActions() {
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			if(Team.getTeam(player) == null) {
				
				getFreeTeam().getPlayers().add(player);
				
			}
			
			Training.game.addPlayer(player);
			
			//scoreboard update
			//tp
			
			
		}
		
		loadRunnable();
		startNewRound(true);
		
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
	
	private static void startNewRound(boolean first) {
		
		Training.game.spec.updateSpecs();
		
		if(!first) {
			
			voting.startNewRound();
			Countdown c = new Countdown(15, true, true, true, true, "" , "%secs%");
			c.setEndCallback(new Runnable() {

				@Override
				public void run() {
					
					mode = voting.getSelected();
					
					map = selectMap(mode);
					teamActions();
					
					countdown = new Countdown(120, false, true, false, true, "", "[%secs%]");
					countdown.setEndCallback(endCallBack);
					countdown.start();
					
				}
				
			});
			
			c.start();
			
		} else {
			
			map = selectMap(mode);
			teamActions();
			
			countdown = new Countdown(120, false, true, false, true, "", "[%secs%]");
			countdown.setEndCallback(endCallBack);
			countdown.start();
			
		}
		
		
		
	}
	
	private static void teamActions() {
		
		if(attacking == null) {
			
			Random r = new Random();
			
			int i = r.nextInt(Team.getTeams().size() - 1);
			
			attacking = Team.getTeams().get(i);
			
			for(Team t : Team.getTeams()) {
				
				if(attacking != t) defending = t;
				
			}
			
		} else {
			
			Team team = attacking;
			attacking = defending;
			defending = team;
			
		}
		
		for(Team t : Team.getTeams()) {
			
			t.setLivingPlayers(t.getPlayers());
			t.setHasBed(true);
			
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
	
	public static boolean wonRound(Team team) {
		
		if(team == attacking) {
			
			return !defending.hasBed();
			
		}
		
		return attacking.getLivingPlayers().size() == 0;
		
	}
	
	public static boolean wonGame(Team team) {
		
		if(team.getPoints() > 4) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	private static void loadRunnable() {
		
		endCallBack = new Runnable() {

			@Override
			public void run() {
				
				if(!winner) {
					
					for(Team team : Team.getTeams()) {
						
						team.setPoints(team.getPoints() + 1);
						
					}
					
					for(Player player : Bukkit.getOnlinePlayers()) {
						
						player.sendMessage(Training.prefix + "Da kein Team die Runde für sich entscheiden konnte, erhalten beide einen Punkt.");
						
					}
					
				}
				
				winner = false;
				
				for(Team team : Team.getTeams()) {
					
					if(wonGame(team)) {
						
						Training.game.spec.clearSpecs();
						
						String msg = Training.prefix + "Team " + team.getName() + " hat das Spiel gewonnen.";
						
						for(Player player : Bukkit.getOnlinePlayers()) {
							
							player.sendMessage(msg);
							//tp
							
						}
						
						Countdown end = new Countdown(15, true, true, false, true, "", "");
						end.setEndCallback(new Runnable() {

							@Override
							public void run() {
								
								for(Player player : Bukkit.getOnlinePlayers()) {
									
									player.kickPlayer("");
									
								}
								
								Bukkit.getScheduler().scheduleSyncDelayedTask(Training.instance, new Runnable() {

									@Override
									public void run() {
										
										Bukkit.getServer().shutdown();
										
									}
									
								}, 20);
								
							}
							
						});
						
						end.start();
						
						return;
						
					}
					
				}
				
				startNewRound(false);
				
			}
			
		};
		
	}
}
