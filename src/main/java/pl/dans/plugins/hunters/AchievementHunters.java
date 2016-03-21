package pl.dans.plugins.hunters;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dans.plugins.hunters.commands.AchievementListCommandExecutor;
import pl.dans.plugins.hunters.commands.StartStopCommandExecutor;
import pl.dans.plugins.hunters.listeners.CactusListener;
import pl.dans.plugins.hunters.listeners.ConsumeListener;
import pl.dans.plugins.hunters.listeners.CraftListener;
import pl.dans.plugins.hunters.listeners.DamageListener;
import pl.dans.plugins.hunters.listeners.ItemGetListener;
import pl.dans.plugins.hunters.listeners.LongshotListener;
import pl.dans.plugins.hunters.listeners.PlayerKillListener;
import pl.dans.plugins.hunters.listeners.PortalListener;
import pl.dans.plugins.hunters.listeners.RecordListener;
import pl.dans.plugins.hunters.listeners.WitchDeathListener;

/**
 * Hello world!
 *
 */
public class AchievementHunters extends JavaPlugin
{
    private AchievementController achievementController;
    private boolean running = false;
    
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}onEnable", ChatColor.RED);
        
        getServer().getPluginManager().registerEvents(new CactusListener(this), this);
        getServer().getPluginManager().registerEvents(new CraftListener(this), this);
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemGetListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKillListener(this), this);
        getServer().getPluginManager().registerEvents(new WitchDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PortalListener(this), this);
        getServer().getPluginManager().registerEvents(new RecordListener(this), this);
        getServer().getPluginManager().registerEvents(new ConsumeListener(this), this);
        getServer().getPluginManager().registerEvents(new LongshotListener(this), this);
        
        getCommand("ahunters").setExecutor(new StartStopCommandExecutor(this));
        getCommand("alist").setExecutor(new AchievementListCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}onDisable", ChatColor.RED);
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        achievementController = new AchievementController(this);
    }

    public AchievementController getAchievementController() {
        return achievementController;
    }
    
    
    
}
