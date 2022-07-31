package projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.Forgotten;

import org.bukkit.scheduler.BukkitRunnable;

public class ForgottenCooldown extends BukkitRunnable {

    private CustomForgottenSoldier customForgottenSoldier;

    public ForgottenCooldown(CustomForgottenSoldier customForgottenSoldier) {
        this.customForgottenSoldier = customForgottenSoldier;
    }

    private int cooldown = 5;

    @Override
    public void run() {
        cooldown--;

        if (cooldown <= 0) {
            cancel();
            CustomForgottenSoldier.ClearCool();
        }
    }

}
