/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.hunters.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;


public class DamageListener implements Listener{
    private final AchievementHunters achievementHunters;

    public DamageListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void death(final EntityDamageEvent entityDamageEvent) {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        if (EntityType.PLAYER.equals(entityDamageEvent.getEntityType())) {
            Player player = (Player) entityDamageEvent.getEntity();
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_DAMAGE, player.getName());
        }
        
        if (EntityType.PLAYER.equals(entityDamageEvent.getEntityType())
                && EntityDamageEvent.DamageCause.FALL.equals(entityDamageEvent.getCause())) {
            Player player = (Player) entityDamageEvent.getEntity();
            achievementHunters.getAchievementController().awardPlayer(Achievement.FALL_DAMAGE, player.getName());
        }
        
        if (EntityType.PLAYER.equals(entityDamageEvent.getEntityType())) {
            Player player = (Player) entityDamageEvent.getEntity();
            achievementHunters.getAchievementController().playerDamaged(player.getName());
        }
        
        
    }
}
