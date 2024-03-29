package projectend.projectend.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten.CustomForgottenSoldier;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.SkeletonKing.CustomSkeletonKing;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.ZombieScout.CustomZombieScout;
import projectend.projectend.events.randomevent.eventfunctions.Dragon.DragonRework;
import projectend.projectend.utils.ColourCode;

public class CustomMob implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }

        EnderDragon enderDragon = null;

        if (args.length == 2) {
            try {
                Player player = Bukkit.getPlayerExact(args[1]);
                if (args[0].equalsIgnoreCase("skeletonking")) {
                    CustomSkeletonKing.Creation(player);
                }
                if (args[0].equalsIgnoreCase("zombiescout")) {
                    CustomZombieScout.Creation(player);
                }
                if (args[0].equalsIgnoreCase("forgotten")) {
                    CustomForgottenSoldier.Creation(player);
                }
                
                if (args[0].equalsIgnoreCase("dragon")) {
                    DragonRework.Creation(player);
                }

            } catch (IllegalArgumentException e) {

            }
        } else {
            sender.sendMessage(ColourCode.colour("&6&l" + "Incorrect Use" + "&c&l" + "/custommob Required: <Entity> <Target>"));
            sender.sendMessage(ColourCode.colour("&6&l" + "Current List" + "&c&l" + " <skeletonking, zombiescout, forgotten, dragon> "));
        }

        return true;
    }
}
