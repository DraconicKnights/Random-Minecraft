package projectend.projectend.events.randomevent.eventfunctions.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RandomSound {

    private static int numsound;

    public static void PlayRandomSound(Player player) {

        numsound = RandomNumGen.RandomSoundNum();

        switch (numsound) {
            case 2:
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 2, 4);
                break;

            case 4:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2, 4);
                break;

            case 8:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 2, 4);
                break;

            case 3:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_TNT_PRIMED, 2, 4);
                break;

            case 5:
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 4);
                break;

            case 6:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2, 4);
                break;

            case 7:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WARDEN_LISTENING, 2, 4);
                break;

            case 10:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WARDEN_ANGRY, 2, 4);
                break;
            case 12:
                player.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 2, 4);
                break;

            case 15:
                player.getWorld().playSound(player.getLocation(), Sound.MUSIC_DISC_5, 2, 4);
                break;

            case 16:
                player.getWorld().playSound(player.getLocation(), Sound.MUSIC_DISC_11, 2, 4);
                break;

            case 14:
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WARDEN_EMERGE, 2, 4);
                break;
        }
    }

}
