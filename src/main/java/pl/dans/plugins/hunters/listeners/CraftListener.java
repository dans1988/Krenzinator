/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.dans.plugins.hunters.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import pl.dans.plugins.hunters.Achievement;
import pl.dans.plugins.hunters.AchievementHunters;

/**
 *
 * @author Dans
 */
public class CraftListener implements Listener {
    private final AchievementHunters achievementHunters;

    public CraftListener(AchievementHunters achievementHunters) {
        this.achievementHunters = achievementHunters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onCraft(final CraftItemEvent craftItemEvent)  {
        
        if (!achievementHunters.getRunning()) {
            return;
        }
        
        Player player = (Player) craftItemEvent.getWhoClicked();
        if (craftItemEvent.getCurrentItem().getType().equals(Material.ENCHANTMENT_TABLE)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_ENCHANTMENT_TABLE, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.BREWING_STAND_ITEM)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_BREWING_STAND, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.BOW)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_BOW, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.FISHING_ROD)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_FISHING_ROD, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_EMERALD_BLOCK, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_REDSTONE_BLOCK, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.ANVIL)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_ANVIL, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.MINECART)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_MINECART, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.DIAMOND_SPADE)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_DIAMOND_SHOVEL, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.CHEST)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_CHEST, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.GOLDEN_APPLE)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_GOLDEN_APPLE, player.getName());
        } else if (craftItemEvent.getCurrentItem().getType().equals(Material.WORKBENCH)) {
            achievementHunters.getAchievementController().awardPlayer(Achievement.FIRST_CRAFTING_TABLE, player.getName());
        }
    }
}
