package searching;

public class BinarySearcher {

    public int searchIndex(int[] array, int key) {

        return this.searchRecursive(array, key, 0, array.length);
    }

    public boolean contains(int[] array, int key) {
        return this.searchIterative(array, key, 0, array.length);
    }

    private boolean searchIterative(int[] array, int key, int start, int end) {

        while (end >= start) {
            int mid = (start + end) / 2;

            if (key < array[mid]) {
                end = mid - 1;
            } else if (key > array[mid]) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    private int searchRecursive(int[] array, int key, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int mid = (end + start) / 2;

        if (key < array[mid]) {
            return this.searchRecursive((int[]) array, (int) key, (int) start, (int) mid);
        } else if (key > array[mid]) {
            return this.searchRecursive((int[]) array, (int) key, (int) (mid + 1), (int) end);
        } else {
            return mid;
        }
    }
}
