package combinations;

import java.util.Arrays;

/**
 * Combinations with and without repetitions
 */
public class Combinations {

    private static int[] elements = {1, 2, 3, 4, 5};
    private static int k;

    public static void main(String[] args) {
        k = 3;
        int[] combination = new int[k];

        generateCombination(combination, 0, 0);
    }

    private static void generateCombination(int[] combination, int start, int index) {

        if (index >= combination.length) {
            print(combination);
            return;
        }
        for (int i = start; i < elements.length; i++) {
            combination[index] = elements[i];
            /**
             * Generate combination without repetition.
             * If we want to allow repetition we call generateCombination()
             * and pass as param i(instead i + 1)
             */
            generateCombination(combination, i + 1, index + 1);
        }
    }

    private static void print(int[] combination) {
        System.out.print("( ");
        Arrays.stream(combination).forEach(element -> System.out.print(element + " "));
        System.out.println(")");
    }
}
