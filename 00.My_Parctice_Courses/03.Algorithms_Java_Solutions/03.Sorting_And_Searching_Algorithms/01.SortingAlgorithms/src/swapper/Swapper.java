package swapper;

public class Swapper {

    public static void swap(int[] arr, int i, int j) {
        int old = arr[i];
        arr[i] = arr[j];
        arr[j] = old;
    }
}
