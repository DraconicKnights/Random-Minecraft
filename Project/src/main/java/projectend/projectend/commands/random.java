package projectend.projectend.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import projectend.projectend.events.randomevent.Arrays.DebugList;
import projectend.projectend.events.randomevent.customspawn.CustomSpiderSpawn;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomSkeletonKing;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobScare.ZombieEntitySpawn;
import projectend.projectend.events.randomevent.eventfunctions.Random.*;
import projectend.projectend.utils.ColourCode;

public class random implements CommandExecutor {

    private static int randomint;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        randomint = RandomNumGen.RandomIntCommand();

        Player player = (Player) sender;

        if (DebugList.GetDebugList().contains(player.getUniqueId())) {
            if (player.hasPermission("end.debug")) {
                player.sendMessage(Component.text(ChatColor.GOLD + "Random ID: " + ChatColor.LIGHT_PURPLE + randomint));
            }

            switch (randomint) {
                case 1:
                    player.getFacing().getDirection().multiply(Math.cos(2));
                    break;

                case 2:
                    player.getFacing().getDirection().multiply(Math.cos(5));
                    break;

                case 3:
                    player.getFacing().getDirection().multiply(Math.sin(5));
                    break;

                case 4:
                    player.damage(2.5);
                    break;

                case 5:
                    player.damage(4);
                    break;

                case 6:
                    player.sendMessage(ColourCode.colour("&a&l" + "Hello " + "&6&l" + player.getName()));
                    break;

                case 7:
                    RandomItemDrop.RandomDrop(player);
                    break;
                case 8:
                    player.getWorld().setSpawnLocation(RandomLocation.RandomLocx(), RandomLocation.RandomLocy(), RandomLocation.RandomLocz());
                    break;

                case 20:
                    CustomSpiderSpawn.CreateHighSpider(player.getLocation());
                    break;

                case 21:
                    RandomSound.PlayRandomSound(player);
                    break;

                case 22:
                    RandomSound.PlayRandomSound(player);
                    break;
                case 23:
                    RandomDiff.RandomDiff(player);
                    break;
                case 24:
                    player.getWorld().setThundering(true);
                    break;
                case 25:
                    player.getWorld().setThundering(false);
                    break;
                case 26:
                    if (!player.getPlayer().getServer().isHardcore()) {
                        player.getWorld().setHardcore(true);
                        player.sendMessage(ColourCode.colour("&6&l" + "Hardcore " + "&a&l" + "Has been enabled"));
                    } else {
                        Bukkit.getLogger().info("Hardcore Already Enabled");
                    }
                    break;
                case 27:
                    if (!player.getPlayer().getServer().isHardcore()) {
                        player.getWorld().setHardcore(false);
                        player.sendMessage(ColourCode.colour("&6&l" + "Hardcore " + "&a&l" + "Has been disabled"));
                        player.sendMessage(ColourCode.colour("&6&l" + "Info: " + "&a&l" + "You may need to leave for it to update"));
                    } else {
                        Bukkit.getLogger().info("Hardcore Already Disabled");
                    }
                    break;
                case 28:
                    RandomDiff.RandomArmourRemove(player);
                    break;
                case 29:
                    RandomMobSpawn.RandomMobSpawn(player);
                    break;
                case 30:
                    player.teleport(new Location(player.getWorld(), RandomLocation.RandomLocx(), RandomLocation.RandomLocy(), RandomLocation.RandomLocz()));
                    break;
                case 31:
                    ZombieEntitySpawn.MobObserveSpawn(player);
                    break;
                case 32:
                    CustomSkeletonKing.Creation(player);
                    break;
            }
        } else {
            player.sendMessage(ColourCode.colour("&c&l" + "You must be in Debug Mode to perform this command"));
        }




        return true;
    }
}
