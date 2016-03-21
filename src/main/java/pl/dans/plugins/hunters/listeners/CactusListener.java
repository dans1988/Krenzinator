/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.hunters.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

/**
 *
 * @author Dans
 */
public class CactusListener implements Listener {
    private final AchievementHunters achievementHunters;

    public CactusListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onCraft(final PlayerInteractEvent playerInteractEvent)  {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        Player player = playerInteractEvent.getPlayer();
        Block block = playerInteractEvent.getClickedBlock();
        
        if (Material.CACTUS.equals(player.getInventory().getItemInHand().getType()) 
                && Material.FLOWER_POT.equals(block.getType())) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_CACTUS_IN_A_FLOWER_POT, player.getName());
        }
    }
}
