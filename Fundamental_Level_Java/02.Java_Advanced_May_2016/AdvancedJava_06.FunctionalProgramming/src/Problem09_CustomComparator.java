import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Problem09_CustomComparator {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split("[\\s+]");
        Integer[] numbers = new Integer[nums.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.valueOf(nums[i]);
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                if (num1 % 2 == 0 && num2 % 2 != 0) {//if first num is even && second num is odd -> return -1 because even numbers must be before odd
                    return -1;                       //so we first rearrange numbers in array
                } else if (num1 % 2 != 0 && num2 % 2 == 0) {//here return 1 -> odd numbers are after even
                    return 1;
                } else {//if numbers are same(both even or odd) we compare them using Integer.compare()
                    return Integer.compare(num1, num2);
                    //we can write our compare() too (if we need to sort numbers descending for example)
//                    if (num1 < num2) {
//                        return -1;
//                    } else if (num1 > num2) {
//                        return 1;
//                    } else {
//                        return 0;
//                    }
                }
            }
        };

        Arrays.sort(numbers, comparator);
        for (Integer number : numbers) {
            System.out.printf("%d ", number);
        }
        System.out.println();
    }
}
