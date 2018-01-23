package com.calypso.training.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.calypso.training.phase.LobbyPhase;

public class GameManager {

	private GameState state;
	private GameType type;
	private GameMode current;
	public SpecManager spec;
	private List<Player> players;

	public GameManager(GameType type) {
		
		state = GameState.LOBBY;
		this.type = type;
		spec = new SpecManager(false);
		players = new ArrayList<>();
		
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
	}

	public GameMode getCurrent() {
		return current;
	}

	public void setCurrent(GameMode current) {
		this.current = current;
	}

	public SpecManager getSpec() {
		return spec;
	}

	public void setSpec(SpecManager spec) {
		this.spec = spec;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	public boolean containsPlayer(Player player) {
		return this.players.contains(player);
	}
	
	public void clearPlayers() {
		this.players.clear();
	}

}
