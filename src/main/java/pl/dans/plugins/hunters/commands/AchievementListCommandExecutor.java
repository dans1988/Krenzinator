package pl.dans.plugins.hunters.commands;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

public class AchievementListCommandExecutor implements CommandExecutor{
    
    private final AchievementHunters achievementHunters;

    public AchievementListCommandExecutor(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().compareToIgnoreCase("alist") == 0) {
            
            if (!achievementHunters.getRunning()) {
                sender.sendMessage(ChatColor.RED + "AchievementHunters is not running!");
                return true;
            }
            
            Map<Achievement, Boolean> achievements = achievementHunters.getAchievementController().getOneTimeAchievements();
            
            for (Achievement achievement : achievements.keySet()) {
                String msg;
                
                if (achievements.get(achievement)) {
                    msg = ChatColor.RED + achievement.name();
                } else {
                    msg = ChatColor.GREEN + achievement.name();
                }
                sender.sendMessage(msg);
            }
        }
        
        return true;
    }
    
}
