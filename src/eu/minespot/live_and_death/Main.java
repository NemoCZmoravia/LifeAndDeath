package eu.minespot.live_and_death;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import eu.minespot.live_and_death.loginsystem.login_main;

public class Main extends JavaPlugin {
	
	@Override
    public void onEnable() {
		new login_main();
		Bukkit.getServer().broadcastMessage("player joined in!");
    }
	
    @Override
    public void onDisable() {
    	
    }
}