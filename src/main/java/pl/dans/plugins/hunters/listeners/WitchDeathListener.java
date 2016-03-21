package pl.dans.plugins.hunters.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;


public class WitchDeathListener implements Listener {
    private final AchievementHunters achievementHunters;

    public WitchDeathListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onWitchDeath(final EntityDeathEvent entityDeathEvent)  {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        if (EntityType.WITCH.equals(entityDeathEvent.getEntity().getType())
                && entityDeathEvent.getEntity().getKiller() != null) {
            Player player = entityDeathEvent.getEntity().getKiller();
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_KILLED_WITCH, player.getName());
        }
        
        
    }
}
