package eu.minespot.life_and_death.loginsystem;


import java.lang.reflect.Array;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.Plugin;


public class login_main implements Listener{
	private Plugin plugin;
	private FileConfiguration config;
	private int waitingRoomPlayerLimit;
	private int not_playing_players = 0;
	
	public login_main(Plugin plugin){
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.waitingRoomPlayerLimit = config.getInt("waitingRoomPlayerLimit");
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		System.out.println("player trying to login");
		System.out.println(waitingRoomPlayerLimit);
		if(not_playing_players < waitingRoomPlayerLimit){
			event.allow();
			not_playing_players++;
			System.out.println(String.join("Na hru čeká ", Integer.toString(not_playing_players) , " hráčů"));
		}else {
			event.disallow(Result.KICK_FULL, "too many players waiting for game");
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(not_playing_players > 0) {
			not_playing_players--;
			System.out.println(String.join("Na hru čeká ", Integer.toString(not_playing_players) , " hráčů"));
		}else {
			System.out.println("error counting players");
		}
	}
}