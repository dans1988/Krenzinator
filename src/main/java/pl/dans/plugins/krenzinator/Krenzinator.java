package pl.dans.plugins.krenzinator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dans.plugins.krenzinator.commands.StartStopCommandExecutor;
import pl.dans.plugins.krenzinator.listeners.CraftListener;
import pl.dans.plugins.krenzinator.listeners.DamageListener;
import pl.dans.plugins.krenzinator.listeners.HorseListener;
import pl.dans.plugins.krenzinator.listeners.KacListener;

import java.util.Iterator;
import java.util.logging.Level;

/**
 * Hello world!
 *
 */
public class Krenzinator extends JavaPlugin
{

    private boolean running = false;
    private final ShapelessRecipe diamond;
    
    public Krenzinator() {
        diamond = new ShapelessRecipe(new ItemStack(Material.DIAMOND)).addIngredient(9, Material.REDSTONE_BLOCK);
    }
    
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}onEnable", ChatColor.RED);
        
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new HorseListener(this), this);
        getServer().getPluginManager().registerEvents(new KacListener(this), this);
        getServer().getPluginManager().registerEvents(new CraftListener(this), this);
        
        getCommand("krenz").setExecutor(new StartStopCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}onDisable", ChatColor.RED);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public void addDiamondRecipe() {
        getServer().addRecipe(diamond);
    }
    
    public void removeDiamondRecipe() {
                
        Iterator<Recipe> iterator = getServer().recipeIterator();
        
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getResult().equals(new ItemStack(Material.DIAMOND))) {
                
                iterator.remove();
                break;
            }
        }
    }
}
