package variations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Variations without repetition && with memoization
 * to avoid generating same elements
 * 1 2 2 2 -> variations are
 * -----------------------
 * 1 2 2 2
 * 2 1 2 2
 * 2 2 1 2
 * 2 2 2 1
 * Here if we don't use memoization code will
 * generate more variations ->
 * 1 2 2 2 &&
 * 1 2 2 2 again etc.
 */
public class VariationsMemoization {

    private static int[] elements = {1, 2, 2, 2};
    private static int k;

    /**
     * HashSet to store repeating elements -> memoization
     */
    private static Set<Integer> memo;

    public static void main(String[] args) {
        k = 4;
        int[] variation = new int[k];
        generateVariation(variation, 0);
    }

    private static void generateVariation(int[] variation, int index) {
        if (index >= k) {
            printVariation(variation);
            return;
        }
        /**
         * In every stack frame we create new HashSet for that recursive call
         * where we will store repeating elements
         */
        memo = new HashSet<>();
        for (int i = index; i < elements.length; i++) {
            /**
             * Call recursion and perform all another actions
             * if this is new element
             */
            if (!memo.contains(elements[index])) {
                memo.add(elements[index]);

                variation[index] = elements[i];
                swap(index, i);
                generateVariation(variation, index + 1);
                swap(index, i);
            }
        }
    }

    private static void swap(int i, int j) {
        int old = elements[i];
        elements[i] = elements[j];
        elements[j] = old;
    }

    private static void printVariation(int[] variation) {
        System.out.print("( ");
        Arrays.stream(variation).forEach(element -> System.out.print(element + " "));
        System.out.println(")");
    }
}
