package projectend.projectend.events.randomevent.eventfunctions.Random;

import java.util.Random;

public class RandomNumGen {

    public static int Random() {
        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(400);
        }
        return randomnum;
    }

    public static int RandomMobAmountNum() {
        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(5);
        }
        return randomnum;
    }

    public static int RandomSoundNum() {
        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(20);
        }
        return randomnum;
    }

    public static int RandomItemDropPick() {
        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(20);
        }
        return randomnum;
    }

    public static int RandomIntCommand() {
        Random num = new Random();
        int randomnum = 0;

        for (int counter = 1; counter <= 1; counter++) {
            randomnum = num.nextInt(32);
        }
        return randomnum;
    }

}
