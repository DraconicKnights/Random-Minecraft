package projectend.projectend.events.randomevent.eventfunctions.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import projectend.projectend.utils.ColourCode;

public class RandomItemDrop {

    private static int num;

    public static void RandomDrop(Player player) {
        num = RandomNumGen.RandomItemDropPick();

        switch (num) {
            case 1:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_HELMET));
                GiftDropMessage(player);
                break;
            case 2:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.DIAMOND_CHESTPLATE)).canMobPickup();
                GiftDropMessage(player);
                break;
            case 3:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_SWORD));
                GiftDropMessage(player);
                break;
            case 4:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.LEATHER));
                GiftDropMessage(player);
                break;
            case 5:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.LEATHER_CHESTPLATE)).canMobPickup();
                GiftDropMessage(player);
                break;
            case 6:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.ICE));
                GiftDropMessage(player);
                break;
            case 7:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.COAL));
                GiftDropMessage(player);
                break;
            case 8:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.OAK_LEAVES));
                GiftDropMessage(player);
                break;
            case 9:
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_SHOVEL));
                GiftDropMessage(player);
                break;
        }
    }

    public static void GiftDropMessage(Player player) {
        player.sendMessage(ColourCode.colour("&a&l" + "A gift has appeared near by"));
    }


}
