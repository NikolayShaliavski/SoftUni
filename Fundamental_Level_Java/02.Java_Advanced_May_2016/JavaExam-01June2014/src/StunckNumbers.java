import java.util.LinkedHashSet;
import java.util.Scanner;

public class StunckNumbers {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        scn.nextLine();

        String[] numbers = scn.nextLine().split(" ");
        int count = 0;
        for (int a = 0; a < numbers.length; a++) {
            for (int b = 0; b < numbers.length; b++) {
                for (int c = 0; c < numbers.length; c++) {
                    for (int d = 0; d < numbers.length; d++) {
                        String left = numbers[a] + numbers[b];
                        String right = numbers[c] + numbers[d];
//                        LinkedHashSet<String> uniqueNums = new LinkedHashSet<>();
//                        uniqueNums.add(numbers[a]);
//                        uniqueNums.add(numbers[b]);
//                        uniqueNums.add(numbers[c]);
//                        uniqueNums.add(numbers[d]);
                        if (left.equals(right)) {
                            if (!numbers[a].equals(numbers[b]) && !numbers[a].equals(numbers[c]) && !numbers[a].equals(numbers[d]) &&
                                    !numbers[b].equals(numbers[c]) && !numbers[b].equals(numbers[d]) &&
                                    !numbers[c].equals(numbers[d])) {
                                count++;
                                System.out.printf("%s|%s==%s|%s\r\n", numbers[a], numbers[b], numbers[c], numbers[d]);
                            }
                        }
                        //uniqueNums.clear();
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("No");
        }
    }
}