package eu.minespot.life_and_death.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.minespot.life_and_death.Main;



public class DefaultCommand implements CommandExecutor {
	
	// java stuff to be able to call this in Main class
	private Main plugin;
	
	public DefaultCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("lifeanddeath").setExecutor(this);
	}
	// java stuff

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage("Add an argument please.");
			return false;
		}else{
			// info
			if(args[0].equalsIgnoreCase("info")) {
				sender.sendMessage(Main.getPlugin(Main.class).getDescription().getVersion()); 
			}
			
			//reload
			//...
		}
		
		return true;
	}
}

/* Just saving this here for later.
try {
			
} catch (IllegalArgumentException e) {
	p.sendMessage(ChatColor.RED + "Invalid option!");
	return false;
}
*/