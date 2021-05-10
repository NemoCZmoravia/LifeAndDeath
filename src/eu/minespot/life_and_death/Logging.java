package eu.minespot.life_and_death;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;



public class Logging {
	ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	/**
	 * method for logging (automatically adds prefixes) 
	 * @param prefix [LifeAndDeath](prefix): - sub prefix
	 * @param log content of log
	 */
    public void logger(String prefix, String log) {
    	console.sendMessage(ChatColor.LIGHT_PURPLE + "[LifeAndDeath]("+ prefix + "): " + ChatColor.GRAY + log );
    }
}
