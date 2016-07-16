import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class Problem10_ListOfPredicates {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer rangeNumber = Integer.valueOf(reader.readLine());
        String[] lineOfNums = reader.readLine().split("[\\s+]");

        Integer[] numbers = getNumbers(lineOfNums);

        Predicate<Integer> tester = num -> {
            boolean canDivide = true;
            for (int i = 0; i < numbers.length; i++) {
                if (num % numbers[i] != 0) {
                    canDivide = false;
                }
            }
            return canDivide;
        };

        for (int i = 1; i <= rangeNumber; i++) {
            if (tester.test(i)) {
                System.out.printf("%d ", i);
            }
        }
        System.out.println();
    }

    private static Integer[] getNumbers(String[] lineOfNums) {
        Integer[] numbers = new Integer[lineOfNums.length];

        for (int i = 0; i < lineOfNums.length; i++) {
            numbers[i] = Integer.valueOf(lineOfNums[i]);
        }
        return numbers;
    }
}
