import java.util.Scanner;

public class LargestSubsequenceOfEqualStrings {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] sequence = scn.nextLine().split(" ");
        int start = 0;
        int bestStart = 0;
        int length = 1;
        int maxLength = 0;
        String value = sequence[0];

        for (int i = 1; i < sequence.length; i++) {
            if (value.equals(sequence[i])) {
                length++;
            } else {
                if (length > maxLength) {
                    maxLength = length;
                    bestStart = start;
                }
                length = 1;
                start = i;
                value = sequence[i];
            }
        }
        if (length > maxLength) {
            maxLength = length;
            bestStart = start;
        }

        for (int i = bestStart; i < bestStart + maxLength; i++) {
            System.out.print(sequence[i] + " ");
        }
    }
}
