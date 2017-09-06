import implementations.*;
import randomizer.Randomizer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        int[] selectionTest = Randomizer.randomize(40_000);
        int[] bubbleTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] insertionTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] quickTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] mergeTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] heapTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] countingTest = Arrays.copyOf(selectionTest, selectionTest.length);

        //Selection sorter
        Sorter selectionSorter = new SelectionSorter();
        long start = System.currentTimeMillis();
        selectionSorter.sort(selectionTest);
        long end = System.currentTimeMillis();

        long selectionTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        //Bubble sorter
        Sorter bubbleSorter = new BubbleSorter();
        start = System.currentTimeMillis();
        bubbleSorter.sort(bubbleTest);
        end = System.currentTimeMillis();

        long bubbleTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        //Insertion sorter
        Sorter insertionSorter = new InsertionSorter();
        start = System.currentTimeMillis();
        insertionSorter.sort(insertionTest);
        end = System.currentTimeMillis();

        long insertionTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        //Quick sorter
        Sorter quickSorter = new QuickSorter();
        start = System.currentTimeMillis();
        quickSorter.sort(quickTest);
        end = System.currentTimeMillis();

        long quickTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        //Merge sorter
        Sorter mergeSorter = new MergeSorter();
        start = System.currentTimeMillis();
        mergeSorter.sort(mergeTest);
        end = System.currentTimeMillis();

        long mergeTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        //Heap sorter
        Sorter heapSorter = new HeapSorter();
        start = System.currentTimeMillis();
        heapSorter.sort(heapTest);
        end = System.currentTimeMillis();

        long heapTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        //Counting sorter
        Sorter countingSorter = new CountingSorter();
        start = System.currentTimeMillis();
        countingSorter.sort(countingTest);
        end = System.currentTimeMillis();

        long countingTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        System.out.println("Selection sort time: " + selectionTime + " sec.");
        System.out.println("Bubble sort time: " + bubbleTime + " sec.");
        System.out.println("Insertion sort time: " + insertionTime + " sec.");
        System.out.println("Quick sort time: " + quickTime + " sec.");
        System.out.println("Merge sort time: " + mergeTime + " sec.");
        System.out.println("Heap sort time: " + heapTime + " sec.");
        System.out.println("Counting sort time: " + countingTime + " sec.");
    }
}
