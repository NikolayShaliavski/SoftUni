package randomizer;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public static int[] randomize(int length) {
        int[] arr = new int[length];
        int randomValue;
        for (int i = 0; i < length; i++) {
            randomValue = ThreadLocalRandom.current().nextInt(1, length + 1);
            arr[i] = randomValue;
        }
        return arr;
    }
}
