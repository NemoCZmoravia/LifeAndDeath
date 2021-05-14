package eu.minespot.life_and_death;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import eu.minespot.life_and_death.commands.DefaultCommand;
import eu.minespot.life_and_death.loginsystem.login_main;
import eu.minespot.life_and_death.io_file.*;

public class Main extends JavaPlugin implements Listener{
	public Io_file io;
	
	@Override
    public void onEnable() {
		this.io = new Io_file(this);
		new login_main(this);
		new DefaultCommand(this);
		this.saveDefaultConfig();
		
		//preloads test config (if it does not exist it will it copy it from jar)
		io.preload_data("", "testconfig.yml");
    }
	
    @Override
    public void onDisable() {
    	io.save_all();
    }
    
}