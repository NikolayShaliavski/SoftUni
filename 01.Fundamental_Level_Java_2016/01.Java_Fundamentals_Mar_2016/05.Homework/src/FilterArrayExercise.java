import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FilterArrayExercise {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        List<Integer> numbers = Arrays.stream(input).filter(num -> isNumber(num)).map(Integer::parseInt).collect(Collectors.toList());

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

    }

    private static boolean isNumber(String num) {

        for (char character : num.toCharArray()) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }
}
