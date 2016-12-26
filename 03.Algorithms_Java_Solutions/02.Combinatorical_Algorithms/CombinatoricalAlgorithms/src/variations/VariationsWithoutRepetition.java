package variations;

import java.util.Arrays;

public class VariationsWithoutRepetition {

    private static int[] elements = {1, 2, 3, 4};
    private static int k;

    /**
     * Here we use boolean array to save if we have used current element
     * Array is with length == elements.length -> (for each of elements)
     */
    private static boolean[] used;

    public static void main(String[] args) {
        k = 3;
        used = new boolean[elements.length];

        int[] variation = new int[k];
        generateVariations(variation, 0);
    }

    private static void generateVariations(int[] variation, int index) {

        if (index >= k) {
            print(variation);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            /**
             * If element haven't been used we include
             * it into current variation
             */
            if (!used[i]) {
                //Mark element as used
                used[i] = true;
                variation[index] = elements[i];
                generateVariations(variation, index + 1);
                /**
                 * In post-action of the recursion unmark element
                 * because we will use it in generating another variations
                 */
                used[i] = false;
            }
        }
    }

    private static void print(int[] variation) {
        System.out.print("( ");
        Arrays.stream(variation).forEach(element -> System.out.print(element + " "));
        System.out.println(")");
    }
}
