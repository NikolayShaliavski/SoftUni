import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Problem06_ReverseAndExclude {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split("[\\s]+");
        int[] nums = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }
        ArrayList<Integer> sorted = new ArrayList<>();
        int divider = Integer.parseInt(reader.readLine());

        Predicate<Integer> howToSort = number -> number % divider != 0;

        for (int num : nums) {
            if (howToSort.test(num)) {
                sorted.add(num);
            }
        }
        for (int i = sorted.size() - 1; i >= 0; i--) {
            System.out.print(sorted.get(i) + " ");
        }
        System.out.println();
    }
}
