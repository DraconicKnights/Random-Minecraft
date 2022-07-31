package projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.SkeletonKing;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class SkeletonKingCooldown extends BukkitRunnable {

    private CustomSkeletonKing customSkeletonKing;


    public SkeletonKingCooldown(CustomSkeletonKing customSkeletonKing) {
        this.customSkeletonKing = customSkeletonKing;
    }

    private int cooldown = 5;

    @Override
    public void run() {
        cooldown--;

        if (cooldown <= 0) {
            cancel();
            CustomSkeletonKing.ClearCool();
        }

    }

}
