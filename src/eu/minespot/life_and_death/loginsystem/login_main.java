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
import java.util.LinkedList;
import java.util.UUID;


public class login_main implements Listener{
	private Plugin plugin;
	private FileConfiguration config;
	private int waitingRoomPlayerLimit;
	private LinkedList<UUID> not_playing_players = new LinkedList<>();
	
	
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
		UUID player_uuid = event.getPlayer().getUniqueId();
		if(not_playing_players.size() < waitingRoomPlayerLimit){
			event.allow();
			not_playing_players.addLast(player_uuid);
			System.out.println(player_uuid);
			System.out.println(String.join("Na hru čeká ", Integer.toString(not_playing_players.size()) , " hráčů"));
		}else {
			event.disallow(Result.KICK_FULL, "too many players waiting for game");
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		UUID player_uuid = event.getPlayer().getUniqueId();
		if(not_playing_players.size() > 0) {
			//should remove the player from linked list
			not_playing_players.remove(player_uuid);
			System.out.println(String.join("Na hru čeká ", Integer.toString(not_playing_players.size()) , " hráčů"));
		}else {
			System.out.println("error counting players");
		}
	}
}