package projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten;

import io.papermc.paper.event.entity.EntityInsideBlockEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
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
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import projectend.projectend.ProjectEnd;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.DropChance;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.SkeletonKing.SkeletonKingCooldown;
import projectend.projectend.utils.ColourCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static projectend.projectend.utils.ColourCode.*;
import static projectend.projectend.utils.ColourCode.colour;

public class CustomForgottenSoldier implements Listener {

    private ProjectEnd plugin;

    private ForgottenCooldown forgottenCooldown;

    public CustomForgottenSoldier(ProjectEnd plugin) {
        this.plugin = plugin;
    }

    private static ArrayList<UUID> playercheck = new ArrayList<>();

    BossBar bar = Bukkit.createBossBar(ColourCode.colour("&c&l" + "Forgotten Soldier"), BarColor.BLUE, BarStyle.SEGMENTED_12, BarFlag.DARKEN_SKY);

    //Mob Creation

    public static void Creation(Player player) {
        WitherSkeleton witherSkeleton = player.getLocation().getWorld().spawn(player.getLocation(), WitherSkeleton.class);

        Location loc = player.getLocation();

        loc = loc.subtract(player.getLocation(), 1,0,1);

        witherSkeleton.customName(Component.text(ColourCode.colour("&c&l" + "Forgotten Soldier")));
        witherSkeleton.setCustomNameVisible(true);
        witherSkeleton.getEquipment().setItemInHand((createEnchantItem(Material.NETHERITE_AXE, 1, Enchantment.FIRE_ASPECT, 2, true, true, true, "Flames of the Forgotten", "", "There are temples and heroes that once used this Axe...")));
        witherSkeleton.getEquipment().setChestplate(enchantItem(new ItemStack(Material.NETHERITE_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL, 3));
        witherSkeleton.getEquipment().setBoots(enchantItem(new ItemStack(Material.DIAMOND_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL, 3));
        witherSkeleton.getEquipment().setLeggings(enchantItem(new ItemStack(Material.DIAMOND_LEGGINGS), Enchantment.MENDING, 2));
        witherSkeleton.getEquipment().setItemInMainHandDropChance(DropChance.DropRateRare());
        witherSkeleton.getEquipment().setChestplateDropChance(DropChance.DropRateNoChance());
        witherSkeleton.getEquipment().setBootsDropChance(DropChance.DropRateRare());
        witherSkeleton.getEquipment().setLeggingsDropChance(DropChance.DropRateRare());
        witherSkeleton.setMaxHealth(80);
        witherSkeleton.setHealth(80);
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 2));
        CreationSpawn(player, loc, witherSkeleton);

    }

    public static void CreationSpawn(Player player, Location location, WitherSkeleton witherSkeleton) {
        witherSkeleton.spawnAt(location);
    }

    //Player Array List

    private static void AddP(UUID uuid) {
        playercheck.add(uuid);
    }

    private static List getP() {
        return playercheck;
    }

    private static void removeAll() {
        playercheck.removeAll(playercheck);
    }

    //Entity Hash Map

    private static HashMap<Entity, Integer> CoolDown = new HashMap<Entity, Integer>();

    private static void AddCool(Entity entity, Integer integer) {
        CoolDown.put(entity, integer);
    }

    private void UpdateMap() {
        if (!CoolDown.isEmpty()) {
            this.forgottenCooldown = new ForgottenCooldown(this);
            this.forgottenCooldown.runTaskTimer(plugin, 0, 20);
        }
    }

    public static void ClearCool() {
        if (!CoolDown.isEmpty()) {
            CoolDown.clear();
        }
    }

    //Creation Events


    @EventHandler
    public void OnLook(EntityMoveEvent event) {

        if (event.getEntity() instanceof WitherSkeleton) {
            WitherSkeleton witherSkeleton = (WitherSkeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&c&l" + "Forgotten Soldier"))) {
                if (((WitherSkeleton) event.getEntity()).getTarget() instanceof Player) {
                    Player player = (Player) ((WitherSkeleton) event.getEntity()).getTarget();
                    if (!CoolDown.containsKey(witherSkeleton) && !CoolDown.containsValue(1)) {
                        if (witherSkeleton.getHealth() < 50) {
                            int random = ThreadLocalRandom.current().nextInt(200);
                            if (random < 10) {
                                player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 2, 2);
                                player.sendMessage(colour("&c&l" + "Forgotten Soldier" + "&a&l" + " Has used blitz"));
                                ((WitherSkeleton) event.getEntity()).launchProjectile(Fireball.class, event.getEntity().getFacing().getDirection());
                                ((WitherSkeleton) event.getEntity()).launchProjectile(Fireball.class, event.getEntity().getFacing().getDirection());
                                ((WitherSkeleton) event.getEntity()).launchProjectile(WitherSkull.class, event.getEntity().getFacing().getDirection());
                                AddCool(witherSkeleton, 1);
                                UpdateMap();
                            }
                        }
                    }
                }
            }
        }

        if (event.getEntity() instanceof WitherSkeleton) {
            WitherSkeleton witherSkeleton = (WitherSkeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&c&l" + "Forgotten Soldier"))) {
                for (Entity entity :witherSkeleton.getNearbyEntities(10,10,10)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        if (!CoolDown.containsKey(witherSkeleton) && !CoolDown.containsValue(2)) {
                            if (witherSkeleton.getHealth() < 50) {
                                int random = ThreadLocalRandom.current().nextInt(1000);
                                if (random < 10) {
                                    Location loc = player.getLocation();
                                    loc = loc.subtract(player.getLocation(), 3, 0, 3);
                                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 2);
                                    player.sendMessage(colour("&c&l" + "Forgotten Soldier" + "&a&l" + " Has teleported to You"));
                                    event.getEntity().teleport(loc);
                                    AddCool(witherSkeleton, 2);
                                    UpdateMap();
                                }
                            }
                        }
                    }
                }
            }
        }


        if (event.getEntity() instanceof WitherSkeleton) {
            WitherSkeleton witherSkeleton = (WitherSkeleton) event.getEntity();
            if (event.getEntity().getName().equals(colour("&c&l" + "Forgotten Soldier"))) {
                if (witherSkeleton.getTarget() == null) {
                    for (Entity entity :witherSkeleton.getNearbyEntities(25,25,25)) {
                        if (entity instanceof Player) {
                            Player player = (Player) entity;
                            witherSkeleton.setTarget(player);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void OnFound(EntityDamageByEntityEvent event) {


        if (event.getEntity() instanceof WitherSkeleton && event.getDamager() instanceof Player) {
            if (event.getEntity().getName().equals(colour("&c&l" + "Forgotten Soldier"))) {
                int random = ThreadLocalRandom.current().nextInt(100);
                if (random < 3) {
                    event.setCancelled(true);
                    Player player = (Player) event.getDamager();
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
                    player.sendMessage(colour("&c&l" + "Forgotten Soldier" + "&a&l" + " Has blocked your Attack"));
                }
            }
        }


        if (event.getDamager() instanceof WitherSkeleton && event.getEntity() instanceof Player) {
            if (event.getDamager().getName().equals(colour("&c&l" + "Forgotten Soldier"))) {
                int random = ThreadLocalRandom.current().nextInt(20);
                if (random < 5) {
                    event.setCancelled(true);
                    Player player = (Player) event.getEntity();
                    player.setVelocity(new Vector(0, 0, 1));
                    player.sendMessage(colour("&c&l" + "Forgotten Soldier" + "&a&l" + " Has used Pull"));
                }
            }
        }


        new BukkitRunnable() {

            @Override
            public void run() {
                if (event.getEntity() instanceof WitherSkeleton) {

                    if (event.getEntity().getName().equals(colour("&c&l" + "Forgotten Soldier"))) {
                        WitherSkeleton witherSkeleton = (WitherSkeleton) event.getEntity();

                        if (!witherSkeleton.isDead()) {

                            for (Entity entity :witherSkeleton.getNearbyEntities(25,25,25)) {
                                if (entity instanceof Player) {
                                    Player player = (Player) entity;
                                    UUID uuid = player.getUniqueId();
                                    if (!getP().contains(uuid)) {
                                        AddP(uuid);
                                        bar.addPlayer(player);
                                    }
                                }

                            }

                            bar.setProgress(witherSkeleton.getHealth()/witherSkeleton.getMaxHealth());
                        }


                        if (witherSkeleton.isDead()) {
                            removeAll();
                            bar.removeAll();
                        }


                    }
                }
            }

        }.runTaskTimer(plugin, 0, 1);



    }

}
