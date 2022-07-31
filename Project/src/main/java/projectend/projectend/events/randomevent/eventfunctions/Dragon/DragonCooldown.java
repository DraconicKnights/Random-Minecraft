package projectend.projectend.events.randomevent.eventfunctions.Dragon;

import org.bukkit.scheduler.BukkitRunnable;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten.CustomForgottenSoldier;

public class DragonCooldown extends BukkitRunnable {

    private DragonRework dragonRework;

    public DragonCooldown(DragonRework dragonRework) {
        this.dragonRework = dragonRework;
    }

    private int cooldown = 10;

    @Override
    public void run() {
        cooldown--;

        if (cooldown <= 0) {
            cancel();
            DragonRework.ClearCool();
        }
    }

}
