package eu.minespot.life_and_death.loginsystem;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;


public class login_main implements Listener{
	private Plugin plugin;
	private FileConfiguration config;
	private int waitingRoomPlayerLimit;
	
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
	}
}