package pl.dans.plugins.hunters;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import static pl.dans.plugins.hunters.Achievement.*;

public class AchievementController {
    
    private static final int IRON_MAN_CHECK_TIME = 20*60*60;  //ticks per second * seconds per minutes * 80 minutes

    public static final Achievement[] ONE_TIME_ACHIEVEMENTS = {
        FIRST_DAMAGE,
        FIRST_BLOOD,
        FIRST_ENCHANTMENT_TABLE,
        FIRST_BREWING_STAND,
        FIRST_BOW,
        FIRST_FISHING_ROD,
        FIRST_EMERALD_BLOCK,
        FIRST_REDSTONE_BLOCK,
        FIRST_NETHER_PORTAL,
        FIRST_SADDLE,
        FIRST_ENDERPEARL,
        FIRST_CACTUS_IN_A_FLOWER_POT,
        FIRST_ANVIL,
        FIRST_MINECART,
        FIRST_KILLED_WITCH,
        FIRST_RECORD_IN_JUKEBOX,
        FIRST_CRAFTING_TABLE,
        FIRST_CHEST,
        FIRST_GOLDEN_APPLE,
        FIRST_DIAMOND_SHOVEL
    };

    private final Map<Achievement, Boolean> oneTimeAchievements;
    private final List<String> damagedPlayers;
    private final BukkitTask ironMenTask;

    public AchievementController(AchievementHunters achievementHunters) {
        this.oneTimeAchievements = Maps.newHashMap();
        for (Achievement achievement : ONE_TIME_ACHIEVEMENTS) {
            oneTimeAchievements.put(achievement, false);
        }
        
        damagedPlayers = Lists.newArrayList();
        
        ironMenTask = new BukkitRunnable() {

            public void run() {
                
                List<Player> players = Lists.newArrayList(Bukkit.getServer().getOnlinePlayers());
                
                for (Player player : players) {
                    if (!damagedPlayers.contains(player.getName())) {
                        awardPlayer(IRONMEN, player.getName());
                    }
                }
                
            }
        }.runTaskLater(achievementHunters, IRON_MAN_CHECK_TIME);
    }

    public void awardPlayer(Achievement achievement, String playername) {

        Player player = Bukkit.getPlayer(playername);

        if (Achievement.FIRST_DAMAGE.equals(achievement) && !oneTimeAchievements.get(achievement)) {

            player.setMaxHealth(18);
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_BLOOD.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.GOLD_INGOT, 7));
            addItem(player, new ItemStack(Material.APPLE, 1));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_ENCHANTMENT_TABLE.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.BOOK, 2));
            addItem(player, new ItemStack(Material.EXP_BOTTLE, 10));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_BREWING_STAND.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.SPECKLED_MELON, 1));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_BOW.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.ARROW, 32));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_FISHING_ROD.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.COOKED_FISH, 16));

            ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta esm = (EnchantmentStorageMeta) book.getItemMeta();
            esm.addStoredEnchant(Enchantment.LUCK, 1, true);
            book.setItemMeta(esm);
            addItem(player, book);

            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_EMERALD_BLOCK.equals(achievement) && !oneTimeAchievements.get(achievement)) {

            ItemStack villagerEggStack = new ItemStack(Material.MONSTER_EGG, 6);
            villagerEggStack.setDurability((short)120);
            addItem(player, villagerEggStack);
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_REDSTONE_BLOCK.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.DIAMOND, 1));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_NETHER_PORTAL.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.GOLD_INGOT, 4));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);
            
        } else if (Achievement.FIRST_SADDLE.equals(achievement) && !oneTimeAchievements.get(achievement)) {

            ItemStack horseEggStack = new ItemStack(Material.MONSTER_EGG, 1);
            horseEggStack.setDurability((short)100);
            addItem(player, horseEggStack);
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_ENDERPEARL.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.ENDER_PEARL, 2));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_CACTUS_IN_A_FLOWER_POT.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1);
            playerHead.setDurability((short) 3);
            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
            skullMeta.setOwner("NTBama");
            playerHead.setItemMeta(skullMeta);
            addItem(player, playerHead);
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_ANVIL.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.BOOK, 8));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_MINECART.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.RAILS, 64));
            addItem(player, new ItemStack(Material.POWERED_RAIL, 8));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_KILLED_WITCH.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            ItemStack horseEggStack = new ItemStack(Material.POTION, 2);
            horseEggStack.setDurability((short)16460);
            addItem(player, horseEggStack);
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_RECORD_IN_JUKEBOX.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.WOOD, 8));
            addItem(player, new ItemStack(Material.DIAMOND, 1));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_CRAFTING_TABLE.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.STICK, 1));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_CHEST.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.CHEST, 64));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_GOLDEN_APPLE.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            addItem(player, new ItemStack(Material.GOLDEN_APPLE, 1));
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FIRST_DIAMOND_SHOVEL.equals(achievement) && !oneTimeAchievements.get(achievement)) {
            
            ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1);
            playerHead.setDurability((short) 3);
            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
            skullMeta.setOwner("TommySX");
            playerHead.setItemMeta(skullMeta);
            addItem(player, playerHead);
            
            ItemStack creeperEggStack = new ItemStack(Material.MONSTER_EGG, 1);
            creeperEggStack.setDurability((short)50);
            addItem(player, creeperEggStack);
            
            oneTimeAchievements.put(achievement, true);
            broadcastAchievement(achievement, playername);

        } else if (Achievement.FALL_DAMAGE.equals(achievement)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));

        } else if (Achievement.GOLDEN_APPLES.equals(achievement)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));

        } else if (Achievement.LONGSHOT_100.equals(achievement)) {
            
            double maxHealth = player.getMaxHealth();
            double currentHealth = player.getHealth();
            if (currentHealth + 4 > maxHealth) {
                currentHealth = maxHealth;
            } else {
                currentHealth = currentHealth + 4;
            }
            
            if (player.getHealth() > 0) {
                player.setHealth(currentHealth);
            }

        } else if (Achievement.LONGSHOT_60.equals(achievement)) {
            
            double maxHealth = player.getMaxHealth();
            double currentHealth = player.getHealth();
            if (currentHealth + 2 > maxHealth) {
                currentHealth = maxHealth;
            } else {
                currentHealth = currentHealth + 2;
            }
            if (player.getHealth() > 0) {
                player.setHealth(currentHealth);
            }

        } else if (Achievement.IRONMEN.equals(achievement)) {
            
            player.setMaxHealth(24);
            broadcastAchievement(achievement, playername);

        }

    }

    public Map<Achievement, Boolean> getOneTimeAchievements() {
        return oneTimeAchievements;
    }

    private void broadcastAchievement(Achievement achievement, String playername) {
        Bukkit.broadcastMessage(ChatColor.GOLD + achievement.name() + " was awarded to " + playername);
    }
    
    public void stop() {
        ironMenTask.cancel();
    }
    
    public void playerDamaged(String playername) {
        if (!damagedPlayers.contains(playername)) {
            damagedPlayers.add(playername);
            Bukkit.broadcastMessage(ChatColor.RED + playername + " lost the iron man achievement!");
        }
    }

    public List<String> getDamagedPlayers() {
        return damagedPlayers;
    }
    
    public void addItem(Player player, ItemStack itemStack) {
        if (!player.getInventory().addItem(itemStack).isEmpty()) {
            player.getWorld().dropItem(player.getLocation(), itemStack);
        }
    }

}
