package shuffling;

import java.util.concurrent.ThreadLocalRandom;

public class FisherYatesShuffler {

    public void shuffle(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, array.length - 1);
            this.swap(array, i, randomIndex);
        }
    }

    private void swap(int[] array, int i, int randomIndex) {
        int old = array[i];
        array[i] = array[randomIndex];
        array[randomIndex] = old;
    }
}
