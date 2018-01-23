package com.calypso.training.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.calypso.training.Training;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class Countdown {

	private int thread;
	private int secsLeft;
	private int started;

	private String chatMessage;
	private String actionbarMessage;
	private boolean actionbar;
	private boolean chat;
	private boolean xpbar;
	private boolean sounds;

	private Runnable changeCallback;
	private Runnable endCallback;

	public Countdown(int seconds, boolean chat, boolean xpbar, boolean sounds, boolean actionbar, String chatMessage, String actionbarMessage) {
		this.secsLeft = seconds;
		this.chat = chat;
		this.xpbar = xpbar;
		this.chatMessage = chatMessage;
		this.started = seconds;
		this.sounds = sounds;
		this.actionbar = actionbar;
		this.actionbarMessage = actionbarMessage;
		this.thread = -1;
	}

	public void start() {
		this.thread = Bukkit.getScheduler().scheduleSyncRepeatingTask(Training.instance, new Runnable() {

			@Override
			public void run() {
				if(Countdown.this.secsLeft == 0) {
					stop();
					return;
				}

				if(Countdown.this.xpbar) {
					if(Countdown.this.xpbar) {
						for(Player player : Bukkit.getOnlinePlayers()) {
							player.setLevel(Countdown.this.secsLeft);
							player.setExp(((float) Countdown.this.secsLeft) / ((float) Countdown.this.started));
						}
					}
				}

				if(Countdown.this.chat) {
					if(Countdown.this.secsLeft % 10 == 0 || Countdown.this.secsLeft <= 5) {
						for(Player player : Bukkit.getOnlinePlayers()) {
							player.sendMessage(Countdown.this.chatMessage.replaceAll("%secs%", Countdown.this.secsLeft + ""));
						}
					}
				}

				if(Countdown.this.sounds) {
					if(Countdown.this.secsLeft % 10 == 0 || Countdown.this.secsLeft <= 5) {
						for(Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
						}
					}
				}

				if(Countdown.this.actionbar) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + Countdown.this.actionbarMessage.replaceAll("%secs%", getStringLeft()) + "\"}");
						PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
						((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
					}
				}

				if(Countdown.this.changeCallback != null) Countdown.this.changeCallback.run();
				Countdown.this.secsLeft--;
			}
		}, 20, 20);
	}

	public void stop() {
		Bukkit.getScheduler().cancelTask(this.thread);

		if(this.xpbar) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				player.setLevel(0);
				player.setExp(0F);
			}
		}

		if(this.endCallback != null) {
			this.endCallback.run();
		}
	}

	public void stopWithoutCallback() {
		Bukkit.getScheduler().cancelTask(this.thread);

		if(this.xpbar) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				player.setLevel(0);
				player.setExp(0F);
			}
		}
	}

	public void setTo(int secs) {
		this.secsLeft = secs;
	}

	public int getSecondsLeft() {
		return this.secsLeft;
	}

	public String getStringLeft() {
		int secs = this.secsLeft;
		int minutes = 0;

		if(secs >= 60) {
			minutes = secs / 60;
			secs = secs - (minutes * 60);
		}

		String secString = secs + "";
		String minString = minutes + "";

		if(secString.length() == 1) secString = "0" + secString;
		if(minString.length() == 1) minString = "0" + minString;

		return minString + ":" + secString;
	}

	public void setChangeCallback(Runnable runnable) {
		this.changeCallback = runnable;
	}

	public void setEndCallback(Runnable runnable) {
		this.endCallback = runnable;
	}

	public boolean isStarted() {
		return this.thread != -1;
	}
}
