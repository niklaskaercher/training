package com.calypso.training;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.calypso.training.game.GameManager;
import com.calypso.training.game.GameMode;
import com.calypso.training.game.GameState;
import com.calypso.training.game.GameType;
import com.calypso.training.game.Team;
import com.calypso.training.game.Voting;
import com.calypso.training.listener.BlockBreakListener;
import com.calypso.training.listener.BlockPlaceListener;
import com.calypso.training.listener.PlayerDamageListener;
import com.calypso.training.listener.PlayerDropItemListener;
import com.calypso.training.listener.PlayerJoinListener;
import com.calypso.training.listener.PlayerLeaveListener;
import com.calypso.training.phase.LobbyPhase;

public class Training extends JavaPlugin {
	
	public static Training instance;
	public static GameManager game;
	public static String prefix;

	@Override
	public void onEnable() {
		
		instance = this;
		
		game = new GameManager(GameType.DEFAULT);
		
		registerCommands();
		registerListener();
		
		GameMode.loadModes();
		Team.registerTeams();
		
		LobbyPhase.voting = new Voting(GameState.LOBBY);
		LobbyPhase.voting.startNewRound();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerCommands() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
	}
	
	private void registerListener() {
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerLeaveListener(), this);
		pm.registerEvents(new BlockPlaceListener(), this);
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new PlayerDamageListener(), this);
		pm.registerEvents(new PlayerDropItemListener(), this);
		
	}
	
	
}
