package pl.dans.plugins.hunters.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

public class ItemGetListener implements Listener {

    private final AchievementHunters achievementHunters;

    public ItemGetListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPickUp(final PlayerPickupItemEvent playerPickupItemEvent) {

        if (!achievementHunters.getRunning()) {
            return;
        }

        Player player = playerPickupItemEvent.getPlayer();
        if (playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.SADDLE)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_SADDLE, player.getName());
        } else if (playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.ENDER_PEARL)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_ENDERPEARL, player.getName());
        } else if (playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.BOW)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_BOW, player.getName());
        }

    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {

        if (!achievementHunters.getRunning()) {
            return;
        }

        Player player = (Player) inventoryClickEvent.getWhoClicked();
        if (InventoryType.CHEST.equals(inventoryClickEvent.getView().getType())
                && inventoryClickEvent.getCurrentItem().getType().equals(Material.SADDLE)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_SADDLE, player.getName());
        } else if (InventoryType.CHEST.equals(inventoryClickEvent.getView().getType())
                && inventoryClickEvent.getCurrentItem().getType().equals(Material.ENDER_PEARL)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_ENDERPEARL, player.getName());
        }

    }
}
