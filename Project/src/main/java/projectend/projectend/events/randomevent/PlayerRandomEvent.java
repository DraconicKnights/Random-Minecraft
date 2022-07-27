package projectend.projectend.events.randomevent;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import projectend.projectend.events.randomevent.Arrays.DebugList;
import projectend.projectend.events.randomevent.customspawn.CustomSpiderSpawn;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomSkeletonKing;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomForgottenSoldier;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomZombieScout;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobScare.ZombieEntitySpawn;
import projectend.projectend.events.randomevent.eventfunctions.Random.*;
import projectend.projectend.utils.ColourCode;

public class PlayerRandomEvent implements Listener {

    private static int randomid;

    @EventHandler
    public void PlayerRandomEvent(BlockBreakEvent event) {

        Player player = event.getPlayer();

        randomid = RandomNumGen.Random();

        if (DebugList.GetDebugList().contains(player.getUniqueId())) {
            if (player.hasPermission("end.debug")) {
                player.sendMessage(Component.text(ChatColor.GOLD + "Random ID: " + ChatColor.LIGHT_PURPLE + randomid));
            }
        }

        switch (randomid) {
            case 2:
                player.getFacing().getDirection().multiply(Math.cos(2));
                break;

            case 7:
                player.getFacing().getDirection().multiply(Math.cos(5));
                break;

            case 1:
                player.getFacing().getDirection().multiply(Math.sin(5));
                break;

            case 4:
                player.damage(2.5);
                break;

            case 20:
                player.damage(4);
                break;

            case 40:
                player.sendMessage(ColourCode.colour("&a&l" + "Hello " + "&6&l" + player.getName()));
                break;

            case 12:
                RandomItemDrop.RandomDrop(player);
                break;
            case 5:
                player.getWorld().setSpawnLocation(RandomLocation.RandomLocx(), RandomLocation.RandomLocy(), RandomLocation.RandomLocz());
                break;

            case 30:
                CustomSpiderSpawn.CreateHighSpider(player.getLocation());
                break;

            case 43:
                RandomSound.PlayRandomSound(player);
                break;

            case 25:
                RandomSound.PlayRandomSound(player);
                break;
            case 15:
                RandomDiff.RandomArmourRemove(player);
                break;
            case 23:
                player.getWorld().setThundering(true);
                break;
            case 45:
                player.getWorld().setThundering(false);
                break;
            case 48:
                if (!event.getPlayer().getServer().isHardcore()) {
                    player.getWorld().setHardcore(true);
                    player.sendMessage(ColourCode.colour("&6&l" + "Hardcore " + "&a&l" + "Has been enabled"));
                } else {
                    Bukkit.getLogger().info("Hardcore Already Enabled");
                }
                break;
            case 14:
                if (!event.getPlayer().getServer().isHardcore()) {
                    player.getWorld().setHardcore(false);
                    player.sendMessage(ColourCode.colour("&6&l" + "Hardcore " + "&a&l" + "Has been disabled"));
                    player.sendMessage(ColourCode.colour("&6&l" + "Info: " + "&a&l" + "You may need to leave for it to update"));
                } else {
                    Bukkit.getLogger().info("Hardcore Already Disabled");
                }
                break;
            case 100:
                RandomDiff.RandomDiff(player);
                break;
            case 200:
                RandomMobSpawn.RandomMobSpawn(player);
                break;
            case 140:
                player.teleport(new Location(player.getWorld(), RandomLocation.RandomLocx(), RandomLocation.RandomLocy(), RandomLocation.RandomLocz()));
                break;
            case 150:
                ZombieEntitySpawn.MobObserveSpawn(player);
                break;
            case 170:
                CustomSkeletonKing.Creation(player);
                break;
            case 130:
                CustomZombieScout.Creation(player);
                break;
            case 131:
                CustomForgottenSoldier.Creation(player);
                break;
        }

        }
    }
