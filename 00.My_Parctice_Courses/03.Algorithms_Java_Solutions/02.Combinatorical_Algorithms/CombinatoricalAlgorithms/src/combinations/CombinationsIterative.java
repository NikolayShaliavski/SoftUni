package combinations;

/**
 * Combinations iterative solution
 */
public class CombinationsIterative {

    private static int[] elements = {1, 2, 3, 4};;

    public static void main(String[] args) {

        int k = 2;

        int[] indexes = new int[k];

        for (int i = 0; i < k; i++) {
            indexes[i] = i;
        }

        while (true) {
            print(indexes);
            int index = k - 1;
            int lastIndex = elements.length - 1;

            /**
             * If we have reached max possible index for that index position we go left
             * Because this is combination every leftmost index must be lower than previous
             * Variable lastIndex is used for that check
             */
            while (index >= 0 && indexes[index] == lastIndex) {
                index--;
                lastIndex--;
            }

            if (index < 0) {
                break;
            }

            /**
             * Obtain next index in indexes[] ->
             * this is index of next value in combination from elements[]
             */
            indexes[index]++;

            for (int i = index + 1; i < k; i++) {
                /**
                 * Update indexes -> every next index == previous index + 1
                 * Because we generate combinations
                 */
                indexes[i] = indexes[i - 1] + 1;
            }
        }
    }

    private static void print(int[] indexes) {
        System.out.print("( ");
        for (int i = 0; i < indexes.length; i++) {
            System.out.print(elements[indexes[i]] + " ");
        }
        System.out.println(")");
    }
}
