package projectend.projectend.events.randomevent.eventfunctions.Random;

import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import projectend.projectend.utils.ColourCode;

import java.util.Random;

public class RandomDiff {

    public static void RandomArmourRemove(Player player) {
        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(100);
        }

        switch (randomnum) {
            case 2:
                player.getInventory().remove(new ItemStack(Material.DIAMOND_AXE));
                break;
            case 4:
                player.getInventory().remove(new ItemStack(Material.IRON_CHESTPLATE));
                break;
            case  6:
                player.getInventory().remove(new ItemStack(Material.DIAMOND_CHESTPLATE));
                break;
        }
    }


    public static void RandomDiff(Player player) {

        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(20);
        }

        switch (randomnum) {
            case 10:
                player.getWorld().setDifficulty(Difficulty.NORMAL);
                player.sendMessage(ColourCode.colour("&a&l" + "Difficultly: " + "&6&l" + player.getWorld().getDifficulty()));
                break;
            case 12:
                player.getWorld().setDifficulty(Difficulty.HARD);
                player.sendMessage(ColourCode.colour("&a&l" + "Difficultly: " + "&6&l" + player.getWorld().getDifficulty()));
                break;
        }
    }

}
