/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.hunters.commands;

import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

/**
 *
 * @author Dans
 */
public class StartStopCommandExecutor implements CommandExecutor {

    private final AchievementHunters achievementHunters;

    public StartStopCommandExecutor(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null || args.length == 0) {
            return false;
        }
        
        String subcommand = args[0];
        
        if (subcommand.compareToIgnoreCase("start") == 0) {
            achievementHunters.setRunning(true);
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "AchievementHunters started!");
        } else if (subcommand.compareToIgnoreCase("stop") == 0) {
            achievementHunters.setRunning(false);
            achievementHunters.getAchievementController().stop();
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "AchievementHunters stopped!");
        } else if (subcommand.compareToIgnoreCase("status") == 0) {
            
            if(achievementHunters.getRunning()) {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "AchievementHunters is running!");
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "AchievementHunters is stopped!");
            }
        } else if (subcommand.compareToIgnoreCase("damagedplayers") == 0) {
            
            List<String> damagedPlayers = achievementHunters.getAchievementController().getDamagedPlayers();
            
            for (String player : damagedPlayers) {

                sender.sendMessage(ChatColor.YELLOW + player);
            }
        }
        return true;
    }
    
}
