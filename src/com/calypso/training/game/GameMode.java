package com.calypso.training.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.calypso.training.util.ItemBuilder;

public class GameMode {

	private static List<GameMode> modes = new ArrayList<>();
	
	private String name;
	private List<String> desc;
	ItemStack icon;
	List<Map<Integer, ItemStack>> attackInv;
	List<Map<Integer, ItemStack>> defInv;
	
	public GameMode(String name, List<String> desc, ItemStack icon, List<Map<Integer, ItemStack>> attackInv,
			List<Map<Integer, ItemStack>> defInv) {
		
		this.name = name;
		this.desc = desc;
		this.icon = icon;
		this.attackInv = attackInv;
		this.defInv = defInv;
		
		GameMode.modes.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDesc() {
		return desc;
	}

	public void setDesc(List<String> desc) {
		this.desc = desc;
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public List<Map<Integer, ItemStack>> getAttackInv() {
		return attackInv;
	}

	public void setAttackInv(List<Map<Integer, ItemStack>> attackInv) {
		this.attackInv = attackInv;
	}

	public List<Map<Integer, ItemStack>> getDefInv() {
		return defInv;
	}

	public void setDefInv(List<Map<Integer, ItemStack>> defInv) {
		this.defInv = defInv;
	}

	public static List<GameMode> getModes() {
		return modes;
	}

	public static void setModes(List<GameMode> modes) {
		GameMode.modes = modes;
	}
	
	public static GameMode getMode(String mode) {
		
		for(GameMode m : modes) {
			
			if(m.getName().equalsIgnoreCase(mode)) return m;
			
		}
		
		return null;
	}
	
	public static void loadModes() {
	
		
	}
}
