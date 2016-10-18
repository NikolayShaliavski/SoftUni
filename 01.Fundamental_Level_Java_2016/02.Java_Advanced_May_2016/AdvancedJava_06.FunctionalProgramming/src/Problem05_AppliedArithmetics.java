import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Function;

public class Problem05_AppliedArithmetics {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String number : nums) {
            numbers.add(Integer.parseInt(number));
        }
        String command = reader.readLine();


        while (!command.equals("end")) {
            switch (command) {
                case "add":
                    Function<ArrayList<Integer>, ArrayList<Integer>> funcAdd = integers -> {
                        ArrayList<Integer> newIntegers = new ArrayList<>();
                        for (Integer integer : integers) {
                            newIntegers.add(integer + 1);
                        }
                        return newIntegers;
                    };
                    numbers = funcAdd.apply(numbers);
                    break;
                case "multiply":
                    Function<ArrayList<Integer>, ArrayList<Integer>> funcMultiply = integers -> {
                        ArrayList<Integer> newIntegers = new ArrayList<>();
                        for (Integer integer : integers) {
                            newIntegers.add(integer * 2);
                        }
                        return newIntegers;
                    };
                    numbers = funcMultiply.apply(numbers);
                    break;
                case "subtract":
                    Function<ArrayList<Integer>, ArrayList<Integer>> funcSubtract = integers -> {
                        ArrayList<Integer> newIntegers = new ArrayList<>();
                        for (Integer integer : integers) {
                            newIntegers.add(integer - 1);
                        }
                        return newIntegers;
                    };
                    numbers = funcSubtract.apply(numbers);
                    break;
                default:
                    for (Integer number : numbers) {
                        System.out.print(number + " ");
                    }
                    System.out.println();
                    break;
            }
            command = reader.readLine();
        }
    }
}
