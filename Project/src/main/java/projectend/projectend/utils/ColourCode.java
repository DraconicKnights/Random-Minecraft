package projectend.projectend.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ColourCode {

    public static @NotNull String colour(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void msgPlayer(Player player, String... strings) {
        for (String string : strings) {
            player.sendMessage(colour(string));
        }
    }

    public static ItemStack enchantItem(ItemStack item, Enchantment enchant, int level) {
        item.addUnsafeEnchantment(enchant, level);
        return item;
    }

    public static ItemStack createItem(Material type, int amount, boolean glow, boolean unbreakable, boolean hideunbreakable, String name, String... lines) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();

        if (glow) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        if (unbreakable) meta.setUnbreakable(true);
        if (hideunbreakable) meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (name != null) meta.displayName(Component.translatable(colour(name)));
        if (lines != null) {
            List<String> lore = new ArrayList<>();
            for (String line : lines) {
                lore.add(colour(line));
            }
            meta.setLore(lore);

        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createEnchantItem(Material type, int amount, Enchantment enchantment, int level, boolean glow, boolean unbreakable, boolean hideunbreakable, String name, String... lines) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();

        if (glow) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        item.addUnsafeEnchantment(enchantment, level);
        if (unbreakable) meta.setUnbreakable(true);
        if (hideunbreakable) meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (name != null) meta.displayName(Component.translatable(colour(name)));
        if (lines != null) {
            List<String> lore = new ArrayList<>();
            for (String line : lines) {
                lore.add(colour(line));
            }
            meta.setLore(lore);

        }
        item.setItemMeta(meta);
        return item;
    }

    public  static ItemStack[] makeArmourSet(ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots) {
        ItemStack[] armour = new ItemStack[4];
        armour[3] = helm;
        armour[2] = chest;
        armour[1] = legs;
        armour[0] = boots;
        return armour;
    }

}
