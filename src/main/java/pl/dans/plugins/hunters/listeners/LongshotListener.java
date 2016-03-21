/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.hunters.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

/**
 *
 * @author Dans
 */
public class LongshotListener implements Listener {
    private final AchievementHunters achievementHunters;

    public LongshotListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void death(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        if (entityDamageByEntityEvent.getDamager() instanceof Projectile) {
            Projectile arrow = (Projectile) entityDamageByEntityEvent.getDamager();
            if (arrow.getShooter() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
                Player killer = (Player) arrow.getShooter();
                Player victim = (Player) entityDamageByEntityEvent.getEntity();
                
                double killerX = killer.getLocation().getX();
                double killerZ = killer.getLocation().getZ();
                
                double victimX = victim.getLocation().getX();
                double victimZ = victim.getLocation().getZ();
                
                double distance = Math.sqrt((killerX-victimX)*(killerX-victimX) + (killerZ-victimZ)*(killerZ-victimZ));
                
                if (distance >= 100D) {
                    achievementHunters.getAchievementController().awardPlayer(Achievement.LONGSHOT_100, killer.getName());
                } else if (distance >= 60D) {
                    achievementHunters.getAchievementController().awardPlayer(Achievement.LONGSHOT_60, killer.getName());
                }
            }
        }
        
        
    }
}
