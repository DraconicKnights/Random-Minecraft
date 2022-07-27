package projectend.projectend.events.randomevent.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DebugList {

    private static ArrayList<UUID> Debuglist = new ArrayList<>();

    public static List GetDebugList() {
        return Debuglist;
    }

    public static void AddDebug(UUID uuid) {
        Debuglist.add(uuid);
    }

    public static void RemoveDebug(UUID uuid) {
        Debuglist.remove(uuid);
    }

    public static void RemoveAllDebug() {
        Debuglist.removeAll(Debuglist);
    }

}
