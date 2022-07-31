package projectend.projectend.events.randomevent.eventfunctions.Random;

import org.bukkit.entity.*;

import java.util.Random;

public class RandomMobSpawn {

    private static int amount;

    public static void RandomMobSpawn(Player player) {

        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(20);
        }

        switch (randomnum) {
            case 2:
                amount = RandomNumGen.RandomMobAmountNum();
                for (int i = 0; i < amount; i++) {
                    player.getWorld().spawn(player.getLocation(), Zombie.class);
                }
                break;
            case 7:
                amount = RandomNumGen.RandomMobAmountNum();
                for (int i = 0; i < amount; i++) {
                    player.getWorld().spawn(player.getLocation(), Skeleton.class);
                }
                break;
            case 4:
                amount = RandomNumGen.RandomMobAmountNum();
                for (int i = 0; i < amount; i++) {
                    player.getWorld().spawn(player.getLocation(), Creeper.class);
                }
                break;
            case 8:
                amount = RandomNumGen.RandomMobAmountNum();
                for (int i = 0; i < amount; i++) {
                    player.getWorld().spawn(player.getLocation(), Pillager.class);
                }
                break;
            case 10:
                amount = RandomNumGen.RandomMobAmountNum();
                for (int i = 0; i < amount; i++) {
                    player.getWorld().spawn(player.getLocation(), Vindicator.class);
                }
                break;
        }

    }


}
