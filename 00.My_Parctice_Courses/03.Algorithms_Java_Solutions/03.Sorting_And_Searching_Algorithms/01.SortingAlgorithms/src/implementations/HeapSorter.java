package implementations;

import swapper.Swapper;

/**
 * Complexity -> in best, average && worst case is O(n * log(n))
 * Memory -> O(1) -> only the processed array
 * Stable -> No
 * Method -> Selection
 * Uses max-heap data structure to sort elements
 */
public class HeapSorter implements Sorter {

    private int indexCounter;

    @Override
    public void sort(int[] arr) {
        this.indexCounter = arr.length - 1;
        //Build max-heap
        for (int i = arr.length / 2; i >= 0; i--) {
            this.heapifyDown(arr, i);
        }

        while (this.indexCounter > 0) {
            /**
             * Swap first element (which is max element) with element at indexCounter
             * In first iteration indexCounter == arr.length - 1, than == arr.length - 2 etc.
             */
            Swapper.swap(arr, 0, this.indexCounter);
            this.indexCounter--;
            //HeapifyDown to satisfy heap property
            this.heapifyDown(arr, 0);
        }
    }

    private void heapifyDown(int[] arr, int parentIndex) {
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;
        int largest = parentIndex;

        if (leftChild <= this.indexCounter && arr[largest] < arr[leftChild]) {
            largest = leftChild;
        }
        if (rightChild <= this.indexCounter && arr[largest] < arr[rightChild]) {
            largest = rightChild;
        }
        if (largest != parentIndex) {
            Swapper.swap(arr, largest, parentIndex);
            this.heapifyDown(arr, largest);
        }
    }
}
