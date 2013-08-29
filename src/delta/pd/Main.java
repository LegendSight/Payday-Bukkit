package delta.pd;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.pd.command.PD;

public class Main extends JavaPlugin implements Listener {

	static Main instance;
	
	public static Main getInstance() {
		
		return instance;
		
	}
	
    public File configFile;
    public FileConfiguration Config;

    public File arenaFile;
    public FileConfiguration Arena;

    public File invFile;
    public FileConfiguration Inv;

    public File chestFile;
    public FileConfiguration Chest;

    public File spawnFile;
    public FileConfiguration Spawns;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
        configFile = new File(getDataFolder(), "config.yml");
        arenaFile = new File(getDataFolder(), "arena.yml");
        invFile = new File(getDataFolder(), "inventorys.yml");
        chestFile = new File(getDataFolder(), "chests.yml");
        spawnFile = new File(getDataFolder(), "spawns.yml");

        try {

            ConfigManager.getInstance().firstRun();

        } catch (Exception e) {

            e.printStackTrace();
        }

        this.Config = new YamlConfiguration();
        this.Arena = new YamlConfiguration();
        this.Inv = new YamlConfiguration();
        this.Chest = new YamlConfiguration();
        this.Spawns = new YamlConfiguration();
        ConfigManager.getInstance().loadYamls();
        ConfigManager.getInstance().saveYamls();
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		
		getCommand("pd").setExecutor(new PD());
		
	}
	
}
