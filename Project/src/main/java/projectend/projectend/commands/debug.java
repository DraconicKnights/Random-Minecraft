package projectend.projectend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import projectend.projectend.events.randomevent.Arrays.DebugList;
import projectend.projectend.utils.ColourCode;

public class debug implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (!DebugList.GetDebugList().contains(player.getUniqueId())) {
            player.sendMessage(ColourCode.colour("&6&L Debug Mode Enabled " + "&a&l" + "Please remember to deactivate when finished"));
            DebugList.AddDebug(player.getUniqueId());

        } else if (DebugList.GetDebugList().contains(player.getUniqueId())) {
            player.sendMessage(ColourCode.colour("&6&L Debug Mode Disabled " + "&a&l" + "You will lose access to debug commands"));
            DebugList.RemoveDebug(player.getUniqueId());
        } else {
            player.sendMessage(ColourCode.colour("&c&l" + "Incorrect Command Usage"));
            player.sendMessage(ColourCode.colour("&6&l" + "/debug"));
        }


        return true;
    }
}
