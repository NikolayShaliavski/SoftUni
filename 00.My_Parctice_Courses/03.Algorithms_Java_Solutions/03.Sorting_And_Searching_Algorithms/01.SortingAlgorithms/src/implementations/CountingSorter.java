package implementations;

import java.util.Map;
import java.util.TreeMap;

/**
 * Complexity -> in best, average && worst case is O(n + k) ??? or n * log(n)
 * k is the range of the elements to be sorted
 * Memory -> O(n + k) ???
 */
public class CountingSorter implements Sorter {

    /**
     * In this implementation we use TreeMap to store elements as keys and number of their
     * occurrencies as values (element 3 -> 1 time, 4 -> 2 times etc.)
     *
     * Another implementation is possible with array, but we can store only Integers
     * and we have to know the range of elements -> from 0 to 1000 for example.
     * We create array with n == 1001 and map elements to indexes -> element 10 -> to index 10
     * and at that index we count occurrencies of that element
     */
    private Map<Integer, Integer> counterTree;

    public CountingSorter() {
        this.counterTree = new TreeMap<>();
    }

    @Override
    public void sort(int[] arr) {
        this.fillTree(arr);

        int indexCounter = 0;
        for (Map.Entry<Integer,Integer> entry : this.counterTree.entrySet()) {
            int value = entry.getKey();
            int occurrencies = entry.getValue();
            for (int i = 0; i < occurrencies; i++) {
                arr[indexCounter++] = value;
            }
        }
    }

    private void fillTree(int[] arr) {
        for (int element : arr) {
            if (!this.counterTree.containsKey(element)) {
                this.counterTree.put(element, 1);
            } else {
                int previousValue = this.counterTree.get(element);
                this.counterTree.put(element, previousValue + 1);
            }
        }
    }
}
