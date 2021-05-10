package eu.minespot.life_and_death.loginsystem;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.Plugin;
import java.util.LinkedList;
import eu.minespot.life_and_death.Logging;

public class login_main implements Listener{
	private Plugin plugin;
	private FileConfiguration config;
	private int waitingRoomPlayerLimit;
	private LinkedList<Player> not_playing_players = new LinkedList<>();
	private Logging loging = new Logging();
	
	/**
	 * Class constructor
	 * prepares all variables and registers Event handlers
	 * @param plugin plugin in witch it is
	 */
	public login_main(Plugin plugin){
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.waitingRoomPlayerLimit = config.getInt("waitingRoomPlayerLimit");
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	/**
	 * login handling login logic (deciding who should be let in)
	 * putting not playing players in queue (sort of)
	 * should automatically put player in game if conditions are met (if he has somewhere to be born)
	 * should test if player is playing already and let him/her in
	 * @param event event which it was called by
	 */
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		//todo check if player is playing
		if(false) {
			event.allow();
		}else {
			if(not_playing_players.size() < waitingRoomPlayerLimit){
				event.allow();
				not_playing_players.addLast(player);
				loging.logger("loginsystem","players waiting:" + Integer.toString(not_playing_players.size()));
			}else {
				event.disallow(Result.KICK_FULL, "too many players waiting for game");
			}
		}
	}
	/**
	 * disconnecting players logic
	 * removing them from queue (if they wasn't playing)
	 * should handle not ended timers for player
	 * @param event event which it was called by
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		//todo check if player wasn't playing
		if(true){
			if(not_playing_players.size() > 0) {
				//remove the player from linked list
				not_playing_players.remove(player);
				loging.logger("loginsystem","players waiting:" + Integer.toString(not_playing_players.size()));
			}else {
				loging.logger("loginsystem","players waiting:" + ChatColor.RED + "error counting players");
			}
		}
	}
}