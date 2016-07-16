import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SortArrayStreamAPI {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String[] line = scn.nextLine().split(" ");

        List<Integer> numbers = Arrays.asList(line).stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());

        String command = scn.nextLine();
        List<Integer> sorted = new ArrayList<>();

        if (command.equals("Ascending")) {
            sorted = sortAscending(numbers);
        } else if (command.equals("Descending")) {
            sorted = sortDescending(numbers);
        }
        for (Integer number : sorted) {
            System.out.print(number + " ");
        }
    }

    private static List<Integer> sortDescending(List<Integer> sorted) {
        return sorted.stream().sorted((num1, num2) -> num2.compareTo(num1)).collect(Collectors.toList());
    }

    private static List<Integer> sortAscending(List<Integer> sorted) {
        return sorted.stream().sorted((num1, num2) -> num1.compareTo(num2)).collect(Collectors.toList());
    }
}
