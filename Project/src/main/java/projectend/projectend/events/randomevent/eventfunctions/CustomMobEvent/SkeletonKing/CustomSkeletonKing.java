package projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.SkeletonKing;

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
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.DropChance;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten.ForgottenCooldown;
import projectend.projectend.utils.ColourCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static projectend.projectend.utils.ColourCode.*;

public class CustomSkeletonKing implements Listener {

    private ProjectEnd plugin;

    private SkeletonKingCooldown skeletonKingCooldown;

    public CustomSkeletonKing(ProjectEnd plugin) {
        this.plugin = plugin;
    }

    private static ArrayList<UUID> playercheck = new ArrayList<>();

    BossBar bar = Bukkit.createBossBar(ColourCode.colour("&6&l" + "Skeleton King"), BarColor.RED, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);

    //Mob Creation

    public static void Creation(Player player) {
        Skeleton skeleton = player.getLocation().getWorld().spawn(player.getLocation(), Skeleton.class);

        Location loc = player.getLocation();

        loc = loc.subtract(player.getLocation(), 1, 0, 1);

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
        skeleton.setShouldBurnInDay(false);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
        CreationSpawn(player, loc, skeleton);

    }

    public static void CreationSpawn(Player player, Location location, Skeleton skeleton) {
        skeleton.spawnAt(location);
    }

    //Player Array List Check

    private static void AddP(UUID uuid) {
        playercheck.add(uuid);
    }

    private static List getP() {
        return playercheck;
    }

    private static void removeAll() {
        playercheck.removeAll(playercheck);
    }

    //Cooldown Hash Map

    private static HashMap<Entity, Integer> CoolDown = new HashMap<Entity, Integer>();

    private static void AddCool(Entity entity, Integer integer) {
        CoolDown.put(entity, integer);
    }

    private void UpdateMap() {
        if (!CoolDown.isEmpty()) {
            this.skeletonKingCooldown = new SkeletonKingCooldown(this);
            this.skeletonKingCooldown.runTaskTimer(plugin, 0, 20);
        }
    }


    public static void ClearCool() {
        if (!CoolDown.isEmpty()) {
            CoolDown.clear();
        }
    }

    //Creation Events

    @EventHandler
    public void OnMove(EntityMoveEvent event) {

        if (event.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                if (((Skeleton) event.getEntity()).getTarget() instanceof Player) {
                    Player player = (Player) ((Skeleton) event.getEntity()).getTarget();
                    if (!CoolDown.containsKey(skeleton) && !CoolDown.containsValue(1)) {
                        int random = ThreadLocalRandom.current().nextInt(500);
                        if (random < 5) {
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 2, 2);
                            player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has used Arrow Storm"));
                            ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                            ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                            ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                            ((Skeleton) event.getEntity()).launchProjectile(Arrow.class, event.getEntity().getFacing().getDirection());
                            AddCool(skeleton, 1);
                            UpdateMap();
                        }
                    }
                }
            }
        }

        if (event.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                for (Entity entity : skeleton.getNearbyEntities(20, 20, 20)) {
                    if (entity instanceof Player) {
                        if (skeleton.getHealth() < 80) {
                            if (!CoolDown.containsKey(skeleton) && !CoolDown.containsValue(2)) {
                                int random = ThreadLocalRandom.current().nextInt(1500);
                                if (random < 10) {
                                    Player player = (Player) entity;
                                    Location loc = player.getLocation();
                                    loc = loc.subtract(player.getLocation(), 3, 0, 3);
                                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                                    player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has teleported to You"));
                                    event.getEntity().teleport(loc);
                                    AddCool(skeleton, 2);
                                    UpdateMap();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (event.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                for (Entity entity : skeleton.getNearbyEntities(20, 20, 20)) {
                    if (entity instanceof Player) {
                        if (skeleton.getHealth() < 50) {
                            if (!CoolDown.containsKey(skeleton) && !CoolDown.containsValue(3)) {
                                int random = ThreadLocalRandom.current().nextInt(1500);
                                if (random < 5) {
                                    Player player = (Player) entity;
                                    Location loc = skeleton.getLocation();
                                    loc = loc.subtract(skeleton.getLocation(), 1, 0, 1);
                                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                                    player.sendMessage(colour("&6&l" + "Skeleton King" + "&a&l" + " Has teleported You"));
                                    player.teleport(loc);
                                    AddCool(skeleton, 3);
                                    UpdateMap();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (event.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&6&l" + "Skeleton King"))) {
                if (skeleton.getTarget() == null) {
                    for (Entity entity :skeleton.getNearbyEntities(25,25,25)) {
                        if (entity instanceof Player) {
                            Player player = (Player) entity;
                            skeleton.setTarget(player);
                        }
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
                                    UUID uuid = player.getUniqueId();
                                    if (!getP().contains(uuid)) {
                                        AddP(uuid);
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


