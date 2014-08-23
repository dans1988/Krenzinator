/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.krenzinator.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import pl.dans.plugins.krenzinator.Krenzinator;

/**
 *
 * @author Dans
 */
public class HorseListener implements Listener{

    private final Krenzinator krenzinator;

    public HorseListener(Krenzinator krenzinator) {
        this.krenzinator = krenzinator;
    }
    
    @EventHandler
    public void onMount(final VehicleEnterEvent e) {
        
        if (!krenzinator.isRunning()) {
            return;
        }
        
        if (((e.getVehicle() instanceof Horse)) && ((e.getEntered() instanceof Player))) {
            
            Horse horse = (Horse)e.getVehicle();
            
            if(horse.getVariant().equals(Horse.Variant.DONKEY)) {
                return;
            }

            ((Player)e.getEntered()).sendMessage(ChatColor.RED + "You can't mount horses in this gamemode!");
            e.setCancelled(true);
        }
    }
}
