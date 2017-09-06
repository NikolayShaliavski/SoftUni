package implementations;

import swapper.Swapper;

/**
 * Complexity -> in average && worst case is O(n2), but in best is O(n) - (already sorted array)
 * In average && worst case there are again many swaps
 * Memory -> O(1)
 * Stable -> Yes
 * Method -> Insertion
 */
public class InsertionSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currIndex = i;
            while (currIndex > 0 && arr[currIndex - 1] > arr[currIndex]) {
                Swapper.swap(arr, currIndex - 1, currIndex);
                currIndex--;
            }
        }
    }
}
