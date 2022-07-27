package projectend.projectend.events.randomevent.eventfunctions.CustomMobScare;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieEntitySpawn implements Listener {

    public static void MobObserveSpawn(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 700, 1));
        Zombie zombie = player.getLocation().getWorld().spawn(player.getLocation(), Zombie.class);
        Location loc = (player.getLocation());
        zombie.customName((Component.text(ChatColor.LIGHT_PURPLE + "Unknown Zombie")));
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 220, 1));
        player.playSound(player.getLocation(), Sound.MUSIC_DISC_11, 1, 2);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);
        MobSpawnLocations(player, loc, zombie);
    }

    public static void MobSpawnLocations(Player player, Location location, Zombie zombie) {
        zombie.spawnAt(location);
        player.playSound(location, Sound.BLOCK_STONE_BREAK, 1, 2);
        player.playSound(location, Sound.BLOCK_STONE_HIT, 1, 2);

        if (zombie.getTarget() == null) {
            for (Entity entity :zombie.getNearbyEntities(10, 10, 10)) {
                if (entity instanceof Player) {
                    zombie.setTarget(player);
                }
            }
        }

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Zombie) {
            if (event.getDamager().getName().equals(ChatColor.LIGHT_PURPLE + "Unknown Zombie")) {
                if (event.getEntity() instanceof Player) {
                    Player player = (Player) event.getEntity();
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 2);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 1));
                    ((Zombie) event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 1));
                    if (((Zombie) event.getDamager()).hasPotionEffect(PotionEffectType.SLOW)) {
                        event.getDamager().remove();
                    }
                }
            }
        }
    }


}
