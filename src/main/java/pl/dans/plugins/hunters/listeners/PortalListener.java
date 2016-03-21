package pl.dans.plugins.hunters.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

public class PortalListener implements Listener {
    private final AchievementHunters achievementHunters;

    public PortalListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onPortalTouch(final PlayerPortalEvent playerPortalEvent)  {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        Player player = playerPortalEvent.getPlayer();
        
        achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_NETHER_PORTAL, player.getName());
        
        
    }
}
