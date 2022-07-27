package projectend.projectend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import projectend.projectend.events.randomevent.Arrays.DebugList;
import projectend.projectend.utils.ColourCode;

public class badum implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (DebugList.GetDebugList().contains(player.getUniqueId())) {
            player.sendMessage(ColourCode.colour("&6&l" + "Cult Members: " + "&a&l" + "Mira, Skyla, Liz"));
        } else if (!DebugList.GetDebugList().contains(player.getUniqueId())) {
            player.sendMessage(ColourCode.colour("&6&l" + "BADUM!" + "&a&l" + " A weird cult that appears to have taken over the Skyblock world in BoxCraft... There is no logic to this cult only pain and misery"));
        } else {
            player.sendMessage(ColourCode.colour("&c&l" + "I honestly don't know how you got the parameters for this command wrong..."));
        }

        return true;
    }
}
