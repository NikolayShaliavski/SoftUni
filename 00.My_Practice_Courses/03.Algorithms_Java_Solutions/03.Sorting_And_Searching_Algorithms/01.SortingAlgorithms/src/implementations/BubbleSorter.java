package implementations;

import swapper.Swapper;

/**
 * Complexity -> in average && worst case is O(n2), but in best is O(n) - (already sorted array)
 * In average && worst case bubble sort is slower than selection sort
 * because of many swaps -> approximately n2 times
 * Memory -> O(1)
 * Stable -> Yes
 * Method -> Selection
 */
public class BubbleSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        boolean swapped;
        int indexOfLastUnsorted = arr.length - 1;
        do {
            swapped = false;
            for (int i = 1; i <= indexOfLastUnsorted; i++) {
                if (arr[i] < arr[i - 1]) {
                    Swapper.swap(arr, i, i - 1);
                    swapped = true;
                }
            }
            //after this loop biggest element is pushed in the end of the array
            //and we decrement index to avoid iteration over already sorted elements
            indexOfLastUnsorted--;
        } while (swapped);
    }
}
