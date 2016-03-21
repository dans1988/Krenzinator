package pl.dans.plugins.hunters.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

public class ConsumeListener implements Listener {
    private final AchievementHunters achievementHunters;

    public ConsumeListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onConsume(final PlayerItemConsumeEvent playerItemConsumeEvent)  {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        Player player = playerItemConsumeEvent.getPlayer();
        ItemStack consumedItem = playerItemConsumeEvent.getItem();
        
        //Bukkit.broadcastMessage(consumedItem.getType().name());
        //Bukkit.broadcastMessage(consumedItem.getDurability() + "");
        
        if (Material.GOLDEN_APPLE.equals(consumedItem.getType())) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_GOLDEN_APPLE, player.getName());
            achievementHunters.getAchievementController().awardPlayer(Achievement.GOLDEN_APPLES, player.getName());
        }/* else if (Material.POTION.equals(consumedItem.getType()) 
                && (consumedItem.getDurability() == (short) 8201
                || consumedItem.getDurability() == (short) 8233
                || consumedItem.getDurability() == (short) 8265
                || consumedItem.getDurability() == (short) 8297
                || consumedItem.getDurability() == (short) 16393
                || consumedItem.getDurability() == (short) 16425
                || consumedItem.getDurability() == (short) 16457
                || consumedItem.getDurability() == (short) 16489)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.STRENGTH_POTION, player.getName());
            
        } else if (Material.POTION.equals(consumedItem.getType()) 
                && (consumedItem.getDurability() == (short) 8194
                || consumedItem.getDurability() == (short) 8226
                || consumedItem.getDurability() == (short) 8258
                || consumedItem.getDurability() == (short) 8290
                || consumedItem.getDurability() == (short) 16386
                || consumedItem.getDurability() == (short) 16418
                || consumedItem.getDurability() == (short) 16450
                || consumedItem.getDurability() == (short) 16482)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.SPEED_POTION, player.getName());
        }*/
    }
}
