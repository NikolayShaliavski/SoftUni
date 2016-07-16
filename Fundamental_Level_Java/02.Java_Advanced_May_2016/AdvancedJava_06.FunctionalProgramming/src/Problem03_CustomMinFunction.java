import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Problem03_CustomMinFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split("[\\s]+");
        Integer[] numbers = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = Integer.parseInt(nums[i]);
        }

        Function<Integer[], Integer> minFunc = integers -> {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < integers.length; i++) {
                if (integers[i] <= min) {
                    min = integers[i];
                }
            }
            return min;
        };
        System.out.println(minFunc.apply(numbers));
    }
}
