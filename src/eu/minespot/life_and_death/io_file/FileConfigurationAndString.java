package eu.minespot.life_and_death.io_file;

import org.bukkit.configuration.file.FileConfiguration;

public class FileConfigurationAndString {
    private final FileConfiguration fileConfiguration_value;
    private final String location;

    public FileConfigurationAndString(FileConfiguration fileConfiguration_value, String location)
    {
        this. fileConfiguration_value = fileConfiguration_value;
        this.location = location;
    }

    public FileConfiguration getfileConfiguration_value()
    {
        return fileConfiguration_value;
    }

    public String getlocation()
    {
        return location;
    }
}
