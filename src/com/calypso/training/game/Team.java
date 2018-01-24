package com.calypso.training.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.calypso.training.util.ItemBuilder;

public class Team {

	public static List<Team> teams = new ArrayList<>();
	
	private String name;
	private Color color;
	private int size;
	private int points;
	private List<Player> players;
	private List<Player> livingPlayers;
	private boolean hasBed;
	
	public Team(String name, Color color, int size) {
		this.name = name;
		this.color = color;
		this.size = size;
		this.players = new ArrayList<>();
		this.livingPlayers = new ArrayList<>();
		this.hasBed = true;
		this.points = 0;
		
		Team.teams.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean hasBed() {
		return hasBed;
	}

	public void setHasBed(boolean hasBed) {
		this.hasBed = hasBed;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public boolean isInTeam(Player player) {
		return this.players.contains(player);
	}
	
	
	
	public List<Player> getLivingPlayers() {
		return livingPlayers;
	}

	public void setLivingPlayers(List<Player> livingPlayers) {
		this.livingPlayers = livingPlayers;
	}

	public static List<Team> getTeams() {
		return teams;
	}

	public static void setTeams(List<Team> teams) {
		Team.teams = teams;
	}

	public static Team getTeam(Player player) {
		
		for(Team team : Team.getTeams()) {
			
			if(team.players.contains(player)) return team;
			
		}
		
		return null;
		
	}
	
	public static void openTeamInventory(Player player) {
		
		Inventory inv = Bukkit.createInventory(player, 9, "§aTeam wählen");
		
		
	}
	
	public static void registerTeams() {
		
		@SuppressWarnings("unused")
		Team blue = new Team("Blau", Color.BLUE, 2);
		Team red = new Team("Rot", Color.RED, 2);
		
	}
	
}
