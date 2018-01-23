package com.calypso.training.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

	private ItemStack item;

	public ItemBuilder(ItemStack item) {
		this.item = item;
	}

	public static ItemBuilder item(Material m) {
		return new ItemBuilder(new ItemStack(m));
	}

	public static ItemBuilder item(ItemStack item) {
		return new ItemBuilder(item);
	}

	public ItemStack build() {
		return this.item;
	}

	//
	//  STANDARD
	//

	public ItemBuilder amount(int count) {
		this.item.setAmount(count);
		return this;
	}

	public ItemBuilder damage(int damage) {
		this.item.setDurability((short) damage);
		return this;
	}

	public ItemBuilder name(String name) {
		ItemMeta meta = this.item.getItemMeta();
		meta.setDisplayName(name);
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(String... lore) {
		List<String> l = new ArrayList<>();

		for(String s : lore) {
			l.add(s);
		}

		ItemMeta meta = this.item.getItemMeta();
		meta.setLore(l);
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder enchant(Enchantment e, int level) {
		this.item.addUnsafeEnchantment(e, level);
		return this;
	}

	public ItemBuilder unbreakable() {
		SkullMeta meta = (SkullMeta) this.item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder unbreakable(boolean unbreakable) {
		SkullMeta meta = (SkullMeta) this.item.getItemMeta();
		meta.spigot().setUnbreakable(unbreakable);
		this.item.setItemMeta(meta);

		return this;
	}

	//
	//  SKULLS
	//

	public ItemBuilder skullOwner(String name) {
		SkullMeta meta = (SkullMeta) this.item.getItemMeta();
		meta.setOwner(name);
		this.item.setItemMeta(meta);

		return this;
	}

	//
	//  BOOKS
	//

	public ItemBuilder bookPages(String... pages) {
		BookMeta meta = (BookMeta) this.item.getItemMeta();
		meta.setPages(pages);
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder bookAuthor(String author) {
		BookMeta meta = (BookMeta) this.item.getItemMeta();
		meta.setAuthor(author);
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder bookTitle(String title) {
		BookMeta meta = (BookMeta) this.item.getItemMeta();
		meta.setTitle(title);
		this.item.setItemMeta(meta);

		return this;
	}

	//
	//  LEATHER ARMOR
	//

	public ItemBuilder leatherColor(Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) this.item.getItemMeta();
		meta.setColor(color);
		this.item.setItemMeta(meta);

		return this;
	}

	//
	//  BANNERS
	//

	public ItemBuilder bannerBaseColor(DyeColor color) {
		BannerMeta meta = (BannerMeta) this.item.getItemMeta();
		meta.setBaseColor(color);
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder addBannerPattern(Pattern pattern) {
		BannerMeta meta = (BannerMeta) this.item.getItemMeta();
		meta.addPattern(pattern);
		this.item.setItemMeta(meta);

		return this;
	}
}

