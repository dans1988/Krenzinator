/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.krenzinator.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.dans.plugins.krenzinator.Krenzinator;

/**
 *
 * @author Dans
 */
public class StartStopCommandExecutor implements CommandExecutor {

    private final Krenzinator krenzinator;

    public StartStopCommandExecutor(Krenzinator krenzinator) {
        this.krenzinator = krenzinator;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }
        
        String subcommand = args[0];
        
        if (subcommand.equalsIgnoreCase("start")) {
            krenzinator.setRunning(true);
            krenzinator.addDiamondRecipe();
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Krenzinator game started!");
            return true;
        }

        if (subcommand.equalsIgnoreCase("stop")) {
            krenzinator.setRunning(false);
            krenzinator.removeDiamondRecipe();
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Krenzinator game stopped!");
            return true;
        }

        if (subcommand.equalsIgnoreCase("status")) {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Krenzinator game is " + (krenzinator.getRunning() ? "running!" : "stopped!"));
            return true;
        }

        return false;
    }
    
}
