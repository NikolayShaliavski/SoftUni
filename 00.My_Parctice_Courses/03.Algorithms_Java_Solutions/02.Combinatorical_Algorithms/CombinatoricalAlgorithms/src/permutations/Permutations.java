package permutations;

import java.util.Arrays;

/**
 * Permutations are like variations without repetitions ->
 * where k == n(elements.length) but here we don't use boolean used[]
 * therefore swap elements, which is more efficient
 */
public class Permutations {

    private static int[] elements = {1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};//{1, 2, 3, 4, 5};
    private static int counter = 0;

    public static void main(String[] args) {
        generatePermutation(0);
        System.out.println(counter);
    }

    private static void generatePermutation(int startIndex) {
        if (startIndex >= elements.length) {
            counter++;
            //printPermutation();
            return;
        }

        for (int i = startIndex; i < elements.length; i++) {
            /**
             * 1 2 3 4 5
             * 1 2 3 5 4
             * Swap 4 with 5 and call recursion
             * to obtain another permutation
             */
            swap(startIndex, i);
            generatePermutation(startIndex + 1);
            /**
             * Return elements to their origin places
             * before recursive call
             */
            swap(startIndex, i);
        }
    }

    private static void swap(int i, int j) {
        int old = elements[i];
        elements[i] = elements[j];
        elements[j] = old;
    }

    private static void printPermutation() {
        System.out.print("( ");
        Arrays.stream(elements).forEach(element -> System.out.print(element + " "));
        System.out.println(")");
    }
}
