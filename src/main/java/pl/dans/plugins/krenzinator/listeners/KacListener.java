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
    
    private static final String KAC_UUID = "f6eb67da-99f1-4352-b5c5-c0440be575f1";
    private static final String GNOME_UUID = "42d908a4-c270-4059-b796-53d217f9429f";

    public KacListener(Krenzinator krenzinator) {
        this.krenzinator = krenzinator;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void death(final PlayerDeathEvent playerDeathEvent) {
        
        if (!krenzinator.getRunning()) {
            return;
        }
        
        Player victim =  playerDeathEvent.getEntity();
        
        if (victim.getUniqueId().toString().equals(KAC_UUID) || victim.getUniqueId().toString().equals(GNOME_UUID)) {
        
            ItemStack diamonds = new ItemStack(Material.DIAMOND, 10);

            playerDeathEvent.getDrops().add(diamonds);
        }
    }
}
