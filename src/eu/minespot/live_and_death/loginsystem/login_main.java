package eu.minespot.live_and_death.loginsystem;


import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;


public class login_main implements Listener{
	public login_main(Plugin plugin){
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		System.out.println("player trying to login");
	}
}