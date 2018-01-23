package com.calypso.training.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;

public class GameMap {

	private String name;
	private String builder;
	private Location attackSpawn;
	private Location defendSpawn;
	private List<GameMode> modes;
	private static List<GameMap> maps;
	private static java.util.Map<GameMode, List<GameMap>> modeMap;
	
	public GameMap(String name, String builder, Location attackSpawn, Location defendSpawn, List<GameMode> modes) {
		
		this.name = name;
		this.builder = builder;
		this.attackSpawn = attackSpawn;
		this.defendSpawn = defendSpawn;
		this.modes = modes;
		
		addMap(this);
		
		for(GameMode mode : modes) {
			
			if(modeMap.containsKey(mode)) {
				
				List<GameMap> maps = modeMap.get(mode);
				maps.add(this);
				
				modeMap.put(mode, maps);
				
			} else {
				
				List<GameMap> maps = new ArrayList<>();
				maps.add(this);
				
				modeMap.put(mode, maps);
				
			}
			
			
			
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public Location getAttackSpawn() {
		return attackSpawn;
	}

	public void setAttackSpawn(Location attackSpawn) {
		this.attackSpawn = attackSpawn;
	}

	public Location getDefendSpawn() {
		return defendSpawn;
	}

	public void setDefendSpawn(Location defendSpawn) {
		this.defendSpawn = defendSpawn;
	}
	
	

	public List<GameMode> getModes() {
		return modes;
	}

	public void setModes(List<GameMode> modes) {
		this.modes = modes;
	}

	public static List<GameMap> getMaps() {
		return maps;
	}

	public static void setMaps(List<GameMap> maps) {
		GameMap.maps = maps;
	}
	
	public static void addMap(GameMap map) {
		GameMap.maps.add(map);
	}

	public static Map<GameMode, List<GameMap>> getModeMap() {
		return modeMap;
	}

	public static void setModeMap(Map<GameMode, List<GameMap>> modeMap) {
		GameMap.modeMap = modeMap;
	}
	
	public static List<GameMap> getMaps(GameMode mode) {
		return GameMap.getModeMap().get(mode);
	}
	
}
