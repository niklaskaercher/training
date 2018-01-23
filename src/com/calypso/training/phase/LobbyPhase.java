package com.calypso.training.phase;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.calypso.training.Training;
import com.calypso.training.game.GameMode;
import com.calypso.training.game.GameState;
import com.calypso.training.game.Voting;
import com.calypso.training.util.Countdown;

public class LobbyPhase {
	
	private static Countdown countdown;
	public static Voting voting;

	public static void start() {
		
		String chatMsg = "Das Spiel startet in %secs%.";
		
		countdown = new Countdown(60, true, true, true, false, chatMsg, "");
		
		countdown.setEndCallback(new Runnable() {

			@Override
			public void run() {
				
				GameMode mode = voting.getSelected();
				
				for(Player player : Bukkit.getOnlinePlayers()) {
				
					player.sendMessage(Training.prefix + mode.getName());
					
				}
				
				IngamePhase.start(mode);
				
			}
			
		});
		
		countdown.start();
		
	}
	
	public static void stop() {
		
		if(LobbyPhase.countdown != null) LobbyPhase.countdown.stop();
		
	}
	
	public static boolean isStarted() {
		
		if(LobbyPhase.countdown != null) return LobbyPhase.countdown.isStarted();
		
		return false;
		
	}

	public static Countdown getCountdown() {
		return countdown;
	}
	
	public static Voting getVoting() {
		return voting;
	}
	
	public static void setContents(Player player) {
		
		
		
	}
}
