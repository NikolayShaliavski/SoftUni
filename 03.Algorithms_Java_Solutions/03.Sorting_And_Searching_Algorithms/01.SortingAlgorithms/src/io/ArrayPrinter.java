package io;

import java.util.Arrays;

public class ArrayPrinter {

    public static void print(int[] arr) {
        Arrays.stream(arr).forEach(element -> System.out.print(element + " "));
        System.out.println();
    }
}
