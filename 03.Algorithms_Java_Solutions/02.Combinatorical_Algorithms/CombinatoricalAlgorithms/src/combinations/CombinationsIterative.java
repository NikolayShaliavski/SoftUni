package combinations;

import java.util.Arrays;

/**
 * Combinations iterative solution
 */
public class CombinationsIterative {

    public static void main(String[] args) {
        int[] elements = {1, 2, 3, 4, 5};
        int k = 3;

        int[] indexes = new int[k];
        int[] combination = new int[k];

        for (int i = 0; i < k; i++) {
            indexes[i] = i;
            combination[i] = elements[i];
        }

        while (true) {
            print(combination);
            int index = k - 1;
            int lastElement = elements.length - 1;

            /**
             * If we have reached max possible value for that index we go left
             * Because this is combination every leftmost value must be lower than previous
             * Variable lastElement is used for that check
             */
            while (index >= 0 && combination[index] == elements[lastElement]) {
                index--;
                lastElement--;
            }

            if (index < 0) {
                break;
            }

            indexes[index]++;
            /**
             * Set next value from elements to the combination
             */
            combination[index] = elements[indexes[index]];

            for (int i = index + 1; i < k; i++) {
                /**
                 * Update indexes -> every next index == previous index + 1
                 * Because we generate combinations
                 */
                indexes[i] = indexes[i - 1] + 1;
                combination[i] = elements[indexes[i]];
            }
        }
    }

    private static void print(int[] combination) {
        System.out.print("( ");
        Arrays.stream(combination).forEach(element -> System.out.print(element + " "));
        System.out.println(")");
    }
}
