package delta.pd;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.pd.command.PD;
import delta.pd.sql.SQL;

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
	
    Logger log = Bukkit.getLogger();
    
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
		
        try {
            this.checkDatabase();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.severe("Connection failed. Defaulting to SQLite.");
            this.Config.set("MySQL.Enable", false);
        }
		
	}
	
    public void checkDatabase() throws SQLException, ClassNotFoundException {

        Connection con = null;

        con = SQL.getConnection();

        con.createStatement().execute("CREATE TABLE IF NOT EXISTS payday(username VARCHAR(255), kills INTEGER, deaths INTEGER, heists INTEGER, money INTEGER)");

    }
	
}
