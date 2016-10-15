package words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordsKristiqnSolution {

    private static List<char[]> permutations = new ArrayList<>();
    private static int steps = 0;
    public static void main(String[] args) {
        String str = new Scanner(System.in).nextLine();
        char[] chars = str.toCharArray();
        Arrays.sort(chars);

        permuteRep(chars, 0, str.length() - 1);

        int count = 0;
        for (int i = 0; i < permutations.size(); i++) {
            boolean correctSequence = true;
            for (int j = 0; j < permutations.get(i).length - 1; j++) {
                if (permutations.get(i)[j] == permutations.get(i)[j + 1]) {
                    correctSequence = false;
                    break;
                }
            }

            if (correctSequence) {
                count++;
            }
        }

        System.out.println(count);
        System.out.println(steps);
    }

    private static void permuteRep(char[] arr, int start, int end) {
        steps++;
        char[] currentArr = new char[arr.length];
        for (int i = 0; i < currentArr.length; i++) {
            currentArr[i] = arr[i];
        }
        permutations.add(currentArr);
        for (int i = 0; i < currentArr.length; i++) {
            System.out.print(currentArr[i] + " ");
        }
        System.out.println();
        for (int left = end; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (arr[left] != arr[right]) {
                    swap(arr, right, left);
                    permuteRep(arr, left + 1, end);
                }
            }

            // Undo all modifications done by the
            // previous recursive calls and swaps
            char firstElement = arr[left];
            for (int i = left; i <= end - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[end] = firstElement;
        }
    }

    private static void swap(char[] arr, int firstIndex, int secondIndex) {
        char swapValue = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = swapValue;
    }
}
