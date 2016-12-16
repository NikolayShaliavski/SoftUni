package implementations;

import swapper.Swapper;

/**
 * Complexity -> in best && average case is O(log(n)), in worst is O(n2)
 * Worst case for quick sort is already sorted array (or reversed sorted)
 * Choose a pivot and separate array in two parts
 * After that call recursion for these two parts,
 * separate them again and etc.
 * In this implementation we choose for pivot first element in the current array partition
 * Memory -> O(log(n)) stack space for recursion
 * Stable -> Depends
 * Method -> Partitioning
 */
public class QuickSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        this.quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        //int pivot = this.lomutoPartitioning(arr, lo, hi);
        int pivot = this.hoarePartitioning(arr, lo, hi);

        /**
         * If we use Lomuto partitioning
         * Call left recursion with pivot + 1(exclusive pivot)
         * If we use Hoare partitioning
         * Call left recursion with pivot (inclusive)
         */
        this.quickSort(arr, lo, pivot);
        this.quickSort(arr, pivot + 1, hi);
    }

    private int hoarePartitioning(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int left = lo - 1;
        int right = hi + 1;

        while (true) {

            do {
                left++;
            } while (arr[left] < pivot);
            do {
                right--;
            } while (arr[right] > pivot);
            if (left < right) {
                Swapper.swap(arr, left, right);
            } else {
                return right;
            }
        }
    }

    private int lomutoPartitioning(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int store = lo + 1;

        for (int i = store; i <= hi; i++) {
            if (arr[i] <= pivot) {
                Swapper.swap(arr, i, store);
                store++;
            }
        }
        store--;
        Swapper.swap(arr, lo, store);
        return store;
    }
}
