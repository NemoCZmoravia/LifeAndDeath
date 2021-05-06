package eu.minespot.live_and_death.loginsystem;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

public class login_main implements Listener{
	@EventHandler
	
	public void onPlayerLogin(PlayerLoginEvent event) {
		Bukkit.getServer().broadcastMessage("dsadsadsa!");
		Player player_trying_to_acess = event.getPlayer();
		UUID playeruuid =  player_trying_to_acess.getUniqueId();
		Bukkit.getServer().broadcastMessage(playeruuid.toString());
		 
	}
}