package projectend.projectend.events.randomevent.eventfunctions.Dragon;

import io.papermc.paper.event.entity.EntityMoveEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.*;
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
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten.CustomForgottenSoldier;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten.ForgottenCooldown;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.SkeletonKing.CustomSkeletonKing;
import projectend.projectend.events.randomevent.eventfunctions.Random.RandomLocation;
import projectend.projectend.events.randomevent.eventfunctions.Random.RandomMobSpawn;
import projectend.projectend.utils.ColourCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static projectend.projectend.utils.ColourCode.*;
import static projectend.projectend.utils.ColourCode.colour;

public class DragonRework implements Listener {

    private ProjectEnd plugin;

    private DragonCooldown dragonCooldown;

    public DragonRework(ProjectEnd plugin) {
        this.plugin = plugin;
    }

    private static ArrayList<UUID> playercheck = new ArrayList<>();

    BossBar bar = Bukkit.createBossBar(ColourCode.colour("&c&l" + "Dragon"), BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.DARKEN_SKY);

    //Mob Creation

    public static void Creation(Player player) {

        EnderDragon enderDragon = player.getLocation().getWorld().spawn(player.getLocation(), EnderDragon.class);

        Location location = new Location(Bukkit.getWorld("World_the_end"), 10, 85, 10);

        enderDragon.customName(Component.text(ColourCode.colour("&c&l" + "Dragon")));
        enderDragon.setMaxHealth(350);
        enderDragon.setHealth(350);
        enderDragon.setVelocity(new Vector(1, 0, 0));

        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 4));
        CreationSpawn(player, location, enderDragon);

        enderDragon.setPhase(EnderDragon.Phase.CHARGE_PLAYER);

    }

    public static void CreationSpawn(Player player, Location location, EnderDragon enderDragon) {
        enderDragon.spawnAt(location);
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
            this.dragonCooldown = new DragonCooldown(this);
            this.dragonCooldown.runTaskTimer(plugin, 0, 20);
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

        if (event.getEntity() instanceof EnderDragon) {
            EnderDragon enderDragon = (EnderDragon) event.getEntity();
            if (event.getEntity().getName().equals(colour("&c&l" + "Dragon"))) {
                if (enderDragon.getTarget() == null) {
                    for (Entity entity :enderDragon.getNearbyEntities(100,100,100)) {
                        if (entity instanceof Player) {
                            Player player = (Player) entity;
                            enderDragon.setTarget(player);
                        }
                    }
                }
            }
        }

        if (event.getEntity() instanceof EnderDragon) {
            EnderDragon enderDragon = (EnderDragon) event.getEntity();
            if (event.getEntity().getName().equals(colour("&c&l" + "Dragon"))) {
                if (enderDragon.getTarget() == null) {
                    for (Entity entity :enderDragon.getNearbyEntities(100,100,100)) {
                        if (entity instanceof Player) {
                            int random = ThreadLocalRandom.current().nextInt(100);
                            switch (random) {
                                case 5:
                                    enderDragon.setPhase(EnderDragon.Phase.HOVER);
                                    break;
                                case 10:
                                    enderDragon.setPhase(EnderDragon.Phase.CIRCLING);
                                    break;
                                case 15:
                                    enderDragon.setPhase(EnderDragon.Phase.BREATH_ATTACK);
                                    break;
                                case 20:
                                    enderDragon.setPhase(EnderDragon.Phase.CHARGE_PLAYER);
                                    break;
                            }
                        }
                    }
                }
            }
        }

    }


    @EventHandler
    public void OnFound(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof EnderDragon && event.getDamager() instanceof Player) {
            if (event.getEntity().getName().equals(colour("&c&l" + "Dragon"))) {
                int random = ThreadLocalRandom.current().nextInt(1000);
                if (random < 5) {
                    event.setCancelled(true);
                    Player player = (Player) event.getDamager();
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
                    player.sendMessage(colour("&c&l" + "Dragon" + "&a&l" + " Has blocked your Attack"));
                }
            }
        }

        if (event.getEntity() instanceof EnderDragon && event.getDamager() instanceof Player) {
            if (event.getEntity().getName().equals(colour("&c&l" + "Dragon"))) {
                EnderDragon enderDragon = (EnderDragon) event.getEntity();
                if (enderDragon.getHealth() < 350) {
                    int random = ThreadLocalRandom.current().nextInt(500);
                    if (random < 5) {
                        event.setCancelled(true);
                        Player player = (Player) event.getDamager();
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2, 2);
                        player.sendMessage(colour("&c&l" + "Dragon" + "&a&l" + " Has commanded mobs to attack you"));
                        int randomspawn = ThreadLocalRandom.current().nextInt(20);
                        switch (randomspawn) {
                            case 5:
                                CustomSkeletonKing.Creation(player);
                                break;
                            case 10:
                                CustomForgottenSoldier.Creation(player);
                                break;
                            case 15:
                                RandomMobSpawn.RandomMobSpawn(player);
                                break;
                        }
                    }
                }
            }
        }

        if (event.getDamager() instanceof EnderDragon && event.getEntity() instanceof Player) {
            if (event.getDamager().getName().equals(colour("&c&l" + "Dragon"))) {
                EnderDragon dragon = (EnderDragon) event.getDamager();
                if (!CoolDown.containsKey(dragon) && !CoolDown.containsValue(2)) {
                    int random = ThreadLocalRandom.current().nextInt(500);
                    if (random < 5) {
                        event.setCancelled(true);
                        Player player = (Player) event.getEntity();
                        player.setVelocity(new Vector(0, 3, 0));
                        player.sendMessage(colour("&c&l" + "Dragon" + "&a&l" + " Has used Shockwave"));
                        AddCool(dragon, 2);
                        UpdateMap();
                    }
                }
            }
        }

        if (event.getEntity() instanceof EnderDragon) {
            if (event.getEntity().getName().equals(colour("&c&l" + "Dragon"))) {
                EnderDragon enderDragon = (EnderDragon)  event.getEntity();
                for (Entity entity :enderDragon.getNearbyEntities(30, 30, 30)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        if (!CoolDown.containsKey(enderDragon) && !CoolDown.containsValue(1)) {
                            if (enderDragon.getHealth() <= 300) {
                                int random = ThreadLocalRandom.current().nextInt(100);
                                if (random < 50) {
                                    Location location = new Location(player.getWorld(), RandomLocation.RandomLocx(), RandomLocation.RandomLocy(), RandomLocation.RandomLocz());

                                    if (location.isGenerated() && location.getBlock().isSolid() && location.clone().add(0, 1, 0).getBlock().isPassable()) {
                                        player.teleport(location);
                                        player.sendMessage(colour("&c&l" + "Dragon" + "&a&l" + " The Dragon has Teleported You"));
                                        AddCool(enderDragon, 1);
                                        UpdateMap();
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }




        new BukkitRunnable() {

            @Override
            public void run() {
                if (event.getEntity() instanceof EnderDragon) {

                    if (event.getEntity().getName().equals(colour("&c&l" + "Dragon"))) {
                        EnderDragon enderDragon = (EnderDragon) event.getEntity();

                        if (!enderDragon.isDead()) {

                            for (Entity entity :enderDragon.getNearbyEntities(25,25,25)) {
                                if (entity instanceof Player) {
                                    Player player = (Player) entity;
                                    UUID uuid = player.getUniqueId();
                                    if (!getP().contains(uuid)) {
                                        AddP(uuid);
                                        bar.addPlayer(player);
                                    }
                                }

                            }

                            bar.setProgress(enderDragon.getHealth()/enderDragon.getMaxHealth());
                        }


                        if (enderDragon.isDead()) {
                            removeAll();
                            bar.removeAll();
                        }


                    }
                }
            }

        }.runTaskTimer(plugin, 0, 1);



    }


}
