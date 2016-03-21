package pl.dans.plugins.hunters.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

public class RecordListener implements Listener {
    private final AchievementHunters achievementHunters;

    public RecordListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onCraft(final PlayerInteractEvent playerInteractEvent)  {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        Player player = playerInteractEvent.getPlayer();
        Block block = playerInteractEvent.getClickedBlock();
        
        if ((Material.RECORD_3.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_3.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_4.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_5.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_6.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_7.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_8.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_9.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_10.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_11.equals(player.getInventory().getItemInHand().getType())
                || Material.RECORD_12.equals(player.getInventory().getItemInHand().getType())
                || Material.GREEN_RECORD.equals(player.getInventory().getItemInHand().getType())
                || Material.GOLD_RECORD.equals(player.getInventory().getItemInHand().getType())) 
                && Material.JUKEBOX.equals(block.getType())) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_RECORD_IN_JUKEBOX, player.getName());
        }
    }
}
