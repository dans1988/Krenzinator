/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.krenzinator.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import pl.dans.plugins.krenzinator.Krenzinator;

/**
 *
 * @author Dans
 */
public class CraftListener implements Listener {
    private final Krenzinator krenzinator;

    public CraftListener(Krenzinator krenzinator) {
        this.krenzinator = krenzinator;
    }
    
    @EventHandler
    public void onCraft(final CraftItemEvent craftItemEvent)  {
        
        if (!krenzinator.isRunning() || craftItemEvent.isCancelled()) {
            return;
        }
        
        if(craftItemEvent.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
            craftItemEvent.getWhoClicked().damage(2);
        }
    }
}
