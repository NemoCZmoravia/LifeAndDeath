package eu.minespot.live_and_death;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import eu.minespot.live_and_death.loginsystem.login_main;

public class Main extends JavaPlugin implements Listener{

	
	
	@Override
    public void onEnable() {
		new login_main(this);
		System.out.println("enabled");
    }
	
    @Override
    public void onDisable() {
    	
    }
}