package projectend.projectend.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import projectend.projectend.events.randomevent.Arrays.DebugList;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomSkeletonKing;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomForgottenSoldier;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomZombieScout;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobScare.ZombieEntitySpawn;
import projectend.projectend.utils.ColourCode;

public class PlayerMineEvent implements Listener {

    @EventHandler
    public void MineEvent(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if (DebugList.GetDebugList().contains(player.getUniqueId())) {

            if (event.getBlock().getType().equals(Material.REDSTONE_TORCH) && event.getPlayer().hasPermission("end.debug")) {
                player.getWorld().setHardcore(false);
                player.sendMessage(ColourCode.colour("&6&l" + "Hardcore Mode " + "&a&l" + "Has been disabled"));
                player.sendMessage(ColourCode.colour("&6&l" + "Info: " + "&a&l" + "You may need to leave for it to update"));
            }

            if (event.getBlock().getType().equals(Material.SOUL_TORCH) && event.getPlayer().hasPermission("end.debug")) {
                ZombieEntitySpawn.MobObserveSpawn(player);
            }

            if (event.getBlock().getType().equals(Material.TORCH) && event.getPlayer().hasPermission("end.debug")) {
                CustomSkeletonKing.Creation(player);
                CustomZombieScout.Creation(player);
                CustomForgottenSoldier.Creation(player);
            }

        }

    }

}
