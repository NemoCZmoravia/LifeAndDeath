package eu.minespot.life_and_death.io_file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import eu.minespot.life_and_death.Logging;
import eu.minespot.life_and_death.Main;

public class Io_file {
	private Logging logging = new Logging();
	private Plugin plugin;
	private Map<String, FileConfigurationAndString> configs = new TreeMap<String, FileConfigurationAndString>();
	
	public Io_file(Plugin plugin) {
		this.plugin = plugin;
		
		try {
			if (!plugin.getDataFolder().exists()) {
				plugin.getDataFolder().mkdir();
			}
		}catch (Exception e){
			logging.logger("io", ChatColor.RED + "Failed to create plugin folder!");
		}
	}
	/**
	 * function to preload configs to internal memory (configs var)
	 * @param location location to file (relative to plugin data folder)
	 * @param file_name filename of file
	 * @return
	 */
	public boolean preload_data(String location, String file_name){
		boolean result_var = false;  
		
		File file = new File(plugin.getDataFolder() + location, file_name);
		if(file.exists() && !file.isDirectory() &&  file.canRead() && file.canWrite()){
			add_to_configlist(file, file_name, location);
			result_var = true;
		}else{
			logging.logger("io", ChatColor.RED + "config file " + file_name + " does not exists and will be created.");
			//create file
			result_var = create_config(location, file_name);
			if(!result_var) {
				logging.logger("io", ChatColor.RED + "Failed to create config_file!");
				logging.logger("io", ChatColor.RED + "You should restart server after this error!");
				logging.logger("io", ChatColor.RED + "You should remove config" + file_name + " for default config file before reloading!");
				logging.logger("io", ChatColor.RED + "Disabling from preload_data from Io_file");
				Bukkit.getServer().getPluginManager().disablePlugin(plugin);
			}
			//2. attempt
			file = new File(plugin.getDataFolder() + location, file_name);
			add_to_configlist(file, file_name, location);
			result_var = true;
			
		}
		return result_var;
	}
	
	private void add_to_configlist(File file, String file_name, String loc){
		try {
			FileConfiguration customConfig = YamlConfiguration.loadConfiguration(file);
			FileConfigurationAndString info = new FileConfigurationAndString(customConfig, loc);
			configs.put(file_name, info);
			System.out.println(customConfig.getString("main"));
		}catch(IllegalArgumentException ex){
			logging.logger("io", ChatColor.RED + "Failed to parse config!");
			logging.logger("io", ChatColor.RED + "You should restart server after this error!");
			logging.logger("io", ChatColor.RED + "You should remove config" + file_name + " for default config file before reloading! (or you can repair it)");
			logging.logger("io", ChatColor.RED + "Disabling from add_to_configlist from Io_file");
		}
	}
	
	
	/**
	 * moves files from jar file to plugin dir
	 * @param name name of file
	 * @param loc location to file (relative to plugin data folder)
	 * @return true if the moving was successful and false if not
	 */
	private boolean create_config(String loc, String name){
		boolean result_var = false;
		InputStream stream_in;
        OutputStream stream_out;
		
        try{
        	stream_in = Main.class.getResourceAsStream("/" + name);
        	if(stream_in == null) {
        		throw new Exception("Cannot get file \"" + name + "\" from Jar file.");
        	}
        	
            int readBytes;
            byte[] buffer = new byte[4096];
            stream_out = new FileOutputStream(plugin.getDataFolder() + loc + "/" + name);
            
            while ((readBytes = stream_in.read(buffer)) > 0) {
                stream_out.write(buffer, 0, readBytes);
            }
            stream_in.close();
            stream_out.close();
            result_var = true;
        }catch (Exception ex){
        	result_var = false;
        	System.out.println(ex.getCause());
        }
		
		return result_var;
	}
	
	public void save_all() {
		for(Entry<String, FileConfigurationAndString> entry : configs.entrySet()) {
			  String file_name = entry.getKey();
			  FileConfigurationAndString value = entry.getValue();
			  String loc = value.getlocation();
			  FileConfiguration fileConfiguration = value.getfileConfiguration_value(); 
			  File config = new File(plugin.getDataFolder() + loc + "/" + file_name); 
			  try {
				fileConfiguration.save(config);
			} catch (IOException e) {
				logging.logger("io", ChatColor.RED + "Failed to save config!");
				logging.logger("io", ChatColor.RED + "You should restart server after this error!");
				logging.logger("io", ChatColor.RED + "You should remove config" + file_name + " for default config file before reloading! (or you can repair it)");
			}
			  
			  System.out.println(plugin.getDataFolder() + loc + "/"+ file_name);
			}
	}
}