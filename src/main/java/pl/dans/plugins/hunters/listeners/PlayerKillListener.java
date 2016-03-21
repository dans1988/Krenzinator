/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.hunters.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

/**
 *
 * @author Dans
 */
public class PlayerKillListener implements Listener{
    private final AchievementHunters achievementHunters;

    public PlayerKillListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void death(final PlayerDeathEvent playerDeathEvent) {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        Player victim =  playerDeathEvent.getEntity();
        Player killer = playerDeathEvent.getEntity().getKiller();
        
        if (killer != null && killer.getUniqueId().compareTo(victim.getUniqueId()) != 0) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_BLOOD, killer.getName());
        }
        
        
    }
}
