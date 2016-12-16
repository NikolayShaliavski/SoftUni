import implementations.*;
import randomizer.Randomizer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] selectionTest = Randomizer.randomize(10_000);
        int[] bubbleTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] insertionTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] quickTest = Arrays.copyOf(selectionTest, selectionTest.length);
        int[] mergeTest = Arrays.copyOf(selectionTest, selectionTest.length);

        //Selection sorter
        Sorter selectionSorter = new SelectionSorter();
        long start = System.currentTimeMillis();
        selectionSorter.sort(selectionTest);
        long end = System.currentTimeMillis();

        long selectionTime = end - start;

        //Bubble sorter
        Sorter bubbleSorter = new BubbleSorter();
        start = System.currentTimeMillis();
        bubbleSorter.sort(bubbleTest);
        end = System.currentTimeMillis();

        long bubbleTime = end - start;

        //Insertion sorter
        Sorter insertionSorter = new InsertionSorter();
        start = System.currentTimeMillis();
        insertionSorter.sort(insertionTest);
        end = System.currentTimeMillis();

        long insertionTime = end - start;

        //Quick sorter
        Sorter quickSorter = new QuickSorter();
        start = System.currentTimeMillis();
        quickSorter.sort(quickTest);
        end = System.currentTimeMillis();

        long quickTime = end - start;


        Sorter mergeSorter = new MergeSorter();
        start = System.currentTimeMillis();
        mergeSorter.sort(mergeTest);
        end = System.currentTimeMillis();

        long mergeTime = end - start;

        System.out.println("Selection sort time: " + selectionTime);
        System.out.println("Bubble sort time: " + bubbleTime);
        System.out.println("Insertion sort time: " + insertionTime);
        System.out.println("Quick sort time: " + quickTime);
        System.out.println("Merge sort time: " + mergeTime);

        //ArrayPrinter.print(selectionTest);
    }
}
