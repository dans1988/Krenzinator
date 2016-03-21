/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.krenzinator.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pl.dans.plugins.krenzinator.Krenzinator;

/**
 *
 * @author Dans
 */
public class KacListener implements Listener{
    private final Krenzinator krenzinator;

    public KacListener(Krenzinator krenzinator) {
        this.krenzinator = krenzinator;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void death(final PlayerDeathEvent playerDeathEvent) {
        
        if (!krenzinator.getRunning()) {
            return;
        }
        
        Player victim =  playerDeathEvent.getEntity();
        
        if (victim.getName().equals("kacperrutka26")
                || victim.getName().equals("SergeantGnome")) {
        
            ItemStack diamonds = new ItemStack(Material.DIAMOND, 10);

            playerDeathEvent.getDrops().add(diamonds);
        }
    }
}
