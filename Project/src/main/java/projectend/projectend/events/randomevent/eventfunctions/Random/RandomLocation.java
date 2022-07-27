package projectend.projectend.events.randomevent.eventfunctions.Random;

import java.util.Random;

public class RandomLocation {

    public static int RandomLocx() {
        Random num = new Random();
        int locx = 0;

        for (int counter = 1; counter <= 1; counter++) {
            locx = num.nextInt(100);
        }
        return locx;
    }

    public static int RandomLocy() {
        Random num = new Random();
        int locy= 0;

        for (int counter = 1; counter <= 1; counter++) {
            locy = num.nextInt(100);
        }
        return locy;
    }

    public static int RandomLocz() {
        Random num = new Random();
        int locz = 0;

        for (int counter = 1; counter <= 1; counter++) {
            locz = num.nextInt(100);
        }
        return locz;
    }

}
