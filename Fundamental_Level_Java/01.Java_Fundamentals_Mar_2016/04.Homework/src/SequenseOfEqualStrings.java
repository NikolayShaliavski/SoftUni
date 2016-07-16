import java.util.Arrays;
import java.util.Scanner;

public class SequenseOfEqualStrings {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] sequence = scn.nextLine().split(" ");
        Arrays.sort(sequence);

        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i].equals(sequence[i + 1])) {
                System.out.print(sequence[i] + " ");
            } else {
                System.out.println(sequence[i]);
            }
            if (i == sequence.length - 2) {
                System.out.println(sequence[i + 1]);
            }

        }
    }
}
