package variations;

import java.util.Arrays;

public class VariationsWithRepetition {

    private static int[] elements = {1, 2, 3};
    private static int k;

    public static void main(String[] args) {
        k = 2;
        int[] variation = new int[k];

        generateVariations(variation, 0);
    }

    private static void generateVariations(int[] variation, int index) {

        if (index >= k) {
            print(variation);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            variation[index] = elements[i];
            generateVariations(variation, index + 1);
        }
    }

    private static void print(int[] variation) {
        System.out.print("( ");
        Arrays.stream(variation).forEach(element -> System.out.print(element + " "));
        System.out.println(")");
    }
}
