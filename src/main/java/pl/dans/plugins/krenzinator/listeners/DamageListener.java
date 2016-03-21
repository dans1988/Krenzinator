/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.krenzinator.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.dans.plugins.krenzinator.Krenzinator;

/**
 *
 * @author Dans
 */
public class DamageListener implements Listener{
    private final Krenzinator krenzinator;

    public DamageListener(Krenzinator krenzinator) {
        this.krenzinator = krenzinator;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void death(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        
        if (!krenzinator.getRunning()) {
            return;
        }
        
        if(entityDamageByEntityEvent.getDamager().getType().equals(EntityType.EGG)) {
            entityDamageByEntityEvent.setDamage(1);
        }
        
    }
}
