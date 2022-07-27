package projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent;

import io.papermc.paper.event.entity.EntityMoveEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
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

public class CustomSkeletonKing implements Listener {

    private ProjectEnd plugin;

    public CustomSkeletonKing(ProjectEnd plugin) {
        this.plugin = plugin;
    }

    private static ArrayList<Player> playercheck = new ArrayList<>();

    BossBar bar = Bukkit.createBossBar(ColourCode.colour("&6&l" + "Skeleton King"), BarColor.RED, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);

    public static void Creation(Player player) {
        Skeleton skeleton = player.getLocation().getWorld().spawn(player.getLocation(), Skeleton.class);

        Location loc = player.getLocation();

        loc = loc.subtract(player.getLocation(), 1,0,1);

        skeleton.customName(Component.text(ColourCode.colour("&6&l" + "Skeleton King")));
        skeleton.setCustomNameVisible(true);
        skeleton.getEquipment().setItemInHand((createEnchantItem(Material.DIAMOND_AXE, 1, Enchantment.DAMAGE_ALL, 2, true, true, true, "Cross Breaker", "", "Lost Axe from a Forgotten age...")));
        skeleton.getEquipment().setChestplate(enchantItem(new ItemStack(Material.DIAMOND_CHESTPLATE), Enchantment.PROTECTION_FIRE, 2));
        skeleton.getEquipment().setBoots(enchantItem(new ItemStack(Material.DIAMOND_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL, 3));
        skeleton.getEquipment().setLeggings(enchantItem(new ItemStack(Material.NETHERITE_LEGGINGS), Enchantment.THORNS, 2));
        skeleton.getEquipment().setItemInMainHandDropChance(DropChance.DropRateRare());
        skeleton.getEquipment().setChestplateDropChance(DropChance.DropRateRare());
        skeleton.getEquipment().setLeggingsDropChance(DropChance.DropRateNoChance());
        skeleton.setMaxHealth(100);
        skeleton.setHealth(100);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
        CreationSpawn(player, loc, skeleton);

    }

    public static void CreationSpawn(Player player, Location location, Skeleton skeleton) {
        skeleton.spawnAt(location);
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

    @EventHandler
    public void OnTarget(EntityTargetLivingEntityEvent event) {

        if (event.getEntity() instanceof Skeleton && event.getTarget() instanceof Player) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                for (Entity entity :skeleton.getNearbyEntities(20,20,20)) {
                    int random = ThreadLocalRandom.current().nextInt(150);
                    if (random < 10) {
                        Player player = (Player) event.getTarget();
                        Location loc = player.getLocation();
                        loc = loc.subtract(player.getLocation(), 3, 0, 3);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                        player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has teleported to You"));
                        event.getEntity().teleport(loc);
                    }
                }
            }
        }

        if (event.getEntity() instanceof Skeleton && event.getTarget() instanceof Player) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                for (Entity entity :skeleton.getNearbyEntities(20,20,20)) {
                    int random = ThreadLocalRandom.current().nextInt(200);
                    if (random < 10) {
                        Player player = (Player) event.getTarget();
                        Location loc = skeleton.getLocation();
                        loc = loc.subtract(skeleton.getLocation(), 1, 0, 1);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                        player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has teleported You"));
                        player.teleport(loc);
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnMove(EntityMoveEvent event) {

        if (event.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                if (((Skeleton) event.getEntity()).getTarget() instanceof Player) {
                    Player player = (Player) ((Skeleton) event.getEntity()).getTarget();
                    int random = ThreadLocalRandom.current().nextInt(500);
                    if (random < 5) {
                        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 2, 2);
                        player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has used Arrow Storm"));
                        ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                        ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                        ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                        ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                    }
                }
            }
        }
    }


    @EventHandler
    public void OnFound(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Skeleton && event.getDamager() instanceof Player) {
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                int random = ThreadLocalRandom.current().nextInt(30);
                if (random < 5) {
                    event.setCancelled(true);
                    Player player = (Player) event.getDamager();
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
                    player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has blocked your Attack"));
                }
            }
        }

        if (event.getDamager() instanceof Skeleton && event.getEntity() instanceof Player) {
            if (event.getDamager().getName().equals(colour("&6&l" + "Skeleton King"))) {
                int random = ThreadLocalRandom.current().nextInt(20);
                if (random < 5) {
                    event.setCancelled(true);
                    Player player = (Player) event.getEntity();
                    player.setVelocity(new Vector(0, 1, 0));
                    player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has used Break Attack"));
                }
            }
        }

        new BukkitRunnable() {

            @Override
            public void run() {
                if (event.getEntity() instanceof Skeleton) {

                    if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                        Skeleton skeleton = (Skeleton) event.getEntity();

                        if (!skeleton.isDead()) {

                            for (Entity entity :skeleton.getNearbyEntities(25,25,25)) {
                                if (entity instanceof Player) {
                                    Player player = (Player) entity;
                                    if (!getP().contains(player)) {
                                        AddP(player);
                                        bar.addPlayer(player);
                                    }
                                }

                            }

                            bar.setProgress(skeleton.getHealth()/skeleton.getMaxHealth());
                        }


                        if (skeleton.isDead()) {
                            removeAll();
                            bar.removeAll();
                        }


                    }
                }
            }

        }.runTaskTimer(plugin, 0, 1);



    }


}


