import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestIncreasingSequence {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] sequence = scn.nextLine().split(" ");
        List<Integer> integers = Arrays.stream(sequence).map(num -> Integer.parseInt(num)).collect(Collectors.toList());
        int start = 0;
        int beststart = 0;
        int length = 1;
        int maxlength = 1;

        for (int i = 0; i < integers.size() - 1; i++) {
            if (integers.get(i) < integers.get(i + 1)) {
                System.out.print(integers.get(i) + " ");
                length++;
                if (i == integers.size() - 2) {
                    System.out.println(integers.get(i + 1));
                }
            } else {
                System.out.println(integers.get(i));
                if (length > maxlength) {
                    maxlength = length;
                    beststart = start;
                }
                length = 1;
                start = i + 1;
            }
        }
        if (length > maxlength) {
            maxlength = length;
            beststart = start;
        }
        System.out.print("Longest: ");
        for (int i = beststart; i < beststart + maxlength; i++) {
            System.out.print(integers.get(i) + " ");
        }
    }
}
