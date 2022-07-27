package projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import projectend.projectend.ProjectEnd;
import projectend.projectend.utils.ColourCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static projectend.projectend.utils.ColourCode.*;
import static projectend.projectend.utils.ColourCode.colour;

public class CustomZombieScout implements Listener {

    private ProjectEnd plugin;

    public CustomZombieScout(ProjectEnd plugin) {
        this.plugin = plugin;
    }

    private static ArrayList<Player> playercheck = new ArrayList<>();

    BossBar bar = Bukkit.createBossBar(ColourCode.colour("&b&l" + "Zombie Scout"), BarColor.GREEN, BarStyle.SEGMENTED_6, BarFlag.PLAY_BOSS_MUSIC);


    public static void Creation(Player player) {
        Zombie zombie = player.getLocation().getWorld().spawn(player.getLocation(), Zombie.class);

        Location loc = player.getLocation();

        loc = loc.subtract(player.getLocation(), 1,0,1);

        zombie.customName(Component.text(ColourCode.colour("&b&l" + "Zombie Scout")));
        zombie.setCustomNameVisible(true);
        zombie.getEquipment().setItemInHand((createEnchantItem(Material.WOODEN_SWORD, 1, Enchantment.KNOCKBACK, 2, true, true, true, "Destroyed Blade", "", "A blade that was forged from a tree long gone")));
        zombie.getEquipment().setChestplate(enchantItem(new ItemStack(Material.LEATHER_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL, 3));
        zombie.getEquipment().setLeggings(enchantItem(new ItemStack(Material.LEATHER_LEGGINGS), Enchantment.PROTECTION_ENVIRONMENTAL, 3));
        zombie.getEquipment().setHelmet(enchantItem(new ItemStack(Material.IRON_HELMET), Enchantment.MENDING, 2));
        zombie.getEquipment().setItemInMainHandDropChance(DropChance.DropRateCommon());
        zombie.getEquipment().setLeggingsDropChance(DropChance.DropRateCommon());
        zombie.getEquipment().setChestplateDropChance(DropChance.DropRateRare());
        zombie.getEquipment().setHelmetDropChance(DropChance.DropRateRare());
        zombie.setMaxHealth(20);
        zombie.setHealth(20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 2));
        CreationSpawn(player, loc, zombie);

    }

    public static void CreationSpawn(Player player, Location location, Zombie zombie) {
        zombie.spawnAt(location);
    }

    private static void AddP(Player player) {
        playercheck.add(player);
    }

    public static void removeP(Player player) {
        playercheck.remove(player);
    }

    private static List getP() {
        return playercheck;
    }

    private static void removeAll() {
        playercheck.removeAll(playercheck);
    }

    private static ArrayList<Entity> CurrentMob = new ArrayList<>();

    private static List getE() {
        return CurrentMob;
    }


    @EventHandler
    public void OnFound(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Zombie && event.getDamager() instanceof Player) {
            if (event.getEntity().getName().equals(colour("&b&l" + "Zombie Scout"))) {
                int random = ThreadLocalRandom.current().nextInt(20);
                if (random < 10) {
                    event.setCancelled(true);
                    Player player = (Player) event.getDamager();
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
                    player.sendMessage(colour("&b&l" + "Zombie Scout" + "&a&l" + " Has blocked your Attack"));
                }
            }
        }

        if (event.getDamager() instanceof Zombie && event.getEntity() instanceof Player) {
            if (event.getDamager().getName().equals(colour("&b&l" + "Zombie Scout"))) {
                int random = ThreadLocalRandom.current().nextInt(20);
                if (random < 5) {
                    event.setCancelled(true);
                    Player player = (Player) event.getEntity();
                    player.setVelocity(new Vector(2, 0, 0));
                    player.sendMessage(colour("&b&l" + "Zombie Scout" + "&a&l" + " Has used Rage"));
                }
            }
        }


        new BukkitRunnable() {

            @Override
            public void run() {
                if (event.getEntity() instanceof Zombie) {

                    if (event.getEntity().getName().equals(colour("&b&l" + "Zombie Scout"))) {
                        Zombie zombie = (Zombie) event.getEntity();

                        if (!zombie.isDead()) {

                            for (Entity entity :zombie.getNearbyEntities(25,25,25)) {
                                if (entity instanceof Player) {
                                    Player player = (Player) entity;
                                    if (!getP().contains(player)) {
                                        AddP(player);
                                        bar.addPlayer(player);
                                    }
                                }

                            }

                            bar.setProgress(zombie.getHealth()/zombie.getMaxHealth());
                        }


                        if (zombie.isDead()) {
                            removeAll();
                            bar.removeAll();
                        }


                    }
                }
            }

        }.runTaskTimer(plugin, 0, 1);



    }
}
