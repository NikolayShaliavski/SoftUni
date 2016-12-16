package implementations;

import swapper.Swapper;

/**
 * Complexity -> best, average && worst case is O(n2)
 * Memory -> O(1)
 * Stable -> No
 * Method -> Selection
 */
public class SelectionSorter implements Sorter {

    @Override
    public void sort(int[] arr) {

        for (int left = 0; left < arr.length - 1; left++) {
            int minElementIndex = left;
            for (int i = left + 1; i < arr.length; i++) {
                if (arr[i] < arr[minElementIndex]) {
                    minElementIndex = i;
                }
            }
            if (minElementIndex != left) {
                Swapper.swap(arr, minElementIndex, left);
            }
        }
    }
}
