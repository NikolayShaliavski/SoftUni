package variations;

/**
 * Iterative solution to generate variations with repetition
 */
public class VariationsIterative {

    private static int[] elements = {1, 2, 3};

    public static void main(String[] args) {

        int k = 2;
        int[] indexes = new int[k];

        while (true) {
            printVariation(indexes);

            int index = k - 1;

            while (index >= 0 && indexes[index] == elements.length - 1) {
                index--;
            }
            if (index < 0) {
                break;
            }
            indexes[index]++;

            for (int i = index + 1; i < k; i++) {
                indexes[i] = 0;
            }
        }
    }

    private static void printVariation(int[] indexes) {
        System.out.print("( ");
        for (int i = 0; i < indexes.length; i++) {
            System.out.print(elements[indexes[i]] + " ");
        }
        System.out.println(")");
    }
}
