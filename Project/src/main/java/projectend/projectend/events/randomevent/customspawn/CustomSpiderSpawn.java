package projectend.projectend.events.randomevent.customspawn;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import projectend.projectend.ProjectEnd;

public class CustomSpiderSpawn implements Listener {

    private static ProjectEnd plugin;

    public CustomSpiderSpawn(ProjectEnd plugin) {
        this.plugin = plugin;
    }

    public static void CreateHighSpider(Location location) {

        CaveSpider cspider = location.getWorld().spawn(location, CaveSpider.class);

        cspider.customName((Component.text(ChatColor.LIGHT_PURPLE + "Low Spider")));
        cspider.setCustomNameVisible(true);

        cspider.setHealth(12);


        new BukkitRunnable() {
            public void run() {

                if (!(cspider.isDead())) {
                    cspider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 4));
                    cspider.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
                    if (cspider.getTarget() == null) {
                        for (Entity entity :cspider.getNearbyEntities(10, 10, 10)) {
                            if (entity instanceof Player) {
                                Player player = (Player) entity;
                                cspider.setTarget(player);
                            }
                        }
                    } else {
                        LivingEntity target = cspider.getTarget();
                        if (target.getLocation().distanceSquared(cspider.getLocation())> 5) {
                            cspider.getWorld().playSound(cspider.getLocation(), Sound.ENTITY_WITHER_SHOOT, 5, 5);
                            cspider.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, cspider.getLocation(), 10);
                            cspider.setVelocity(target.getLocation().add(0, 1, 0).subtract(cspider.getLocation()).toVector().multiply(0.125 ));
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 120);

    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Spider) {
            if (event.getDamager().getName().equals(ChatColor.LIGHT_PURPLE + "Low Spider")) {
                if (event.getEntity() instanceof Player) {
                    Player player = (Player) event.getEntity();
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 2, 2);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 180, 1));
                }
            }
        }
    }

}
