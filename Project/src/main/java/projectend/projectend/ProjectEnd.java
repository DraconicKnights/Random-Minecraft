package projectend.projectend;

import org.bukkit.plugin.java.JavaPlugin;
import projectend.projectend.commands.CustomMob;
import projectend.projectend.commands.badum;
import projectend.projectend.commands.debug;
import projectend.projectend.commands.random;
import projectend.projectend.events.PlayerMineEvent;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomSkeletonKing;
import projectend.projectend.events.randomevent.customspawn.CustomSpiderSpawn;
import projectend.projectend.events.randomevent.PlayerRandomEvent;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomForgottenSoldier;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobEvent.CustomZombieScout;
import projectend.projectend.events.randomevent.eventfunctions.CustomMobScare.ZombieEntitySpawn;

public final class ProjectEnd extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

//        getServer().getPluginManager().registerEvents(new PlayerJumpEvent(), this);

        //Main Event

        getServer().getPluginManager().registerEvents(new PlayerRandomEvent(), this);

        //Events

        getServer().getPluginManager().registerEvents(new CustomSpiderSpawn(this), this);

        getServer().getPluginManager().registerEvents(new PlayerMineEvent(), this);

        //CustomMobScare

        getServer().getPluginManager().registerEvents(new ZombieEntitySpawn(), this);

        //CustomMobEvents

        getServer().getPluginManager().registerEvents(new CustomSkeletonKing(this), this);

        getServer().getPluginManager().registerEvents(new CustomZombieScout(this), this);

        getServer().getPluginManager().registerEvents(new CustomForgottenSoldier(this), this);

        getCommand("random").setExecutor(new random());
        getCommand("debug").setExecutor(new debug());
        getCommand("badum").setExecutor(new badum());
        getCommand("custommob").setExecutor(new CustomMob());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
