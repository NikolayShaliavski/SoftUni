package implementations;

/**
 * Complexity -> in best, average && worst case is O(n * log(n))
 * Memory -> O(n)
 * Stable -> Yes
 * Method -> Merging
 */
public class MergeSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        this.mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int start, int end) {

        if (start >= end) {
            return;
        }
        /**
         * Partition array always in the middle
         * which guarantees n * log(n) complexity in all cases
         */
        int mid = start + (end - start) / 2;

        this.mergeSort(arr, start, mid);
        this.mergeSort(arr, mid + 1, end);

        /**
         * In post-action of the recursion merge two partitions.
         * Here we sort elements.
         *
         */
        this.merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int left = start;
        int right = mid + 1;
        int mergedIndex = 0;

        while (left <= mid && right <= end) {
            if (arr[left] < arr[right]) {
                temp[mergedIndex++] = arr[left++];
            } else {
                temp[mergedIndex++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[mergedIndex++] = arr[left++];
        }
        while (right <= end) {
            temp[mergedIndex++] = arr[right++];
        }

        for (int i = start; i <= end; i++) {
            arr[i] = temp[i - start];
        }
    }
}
