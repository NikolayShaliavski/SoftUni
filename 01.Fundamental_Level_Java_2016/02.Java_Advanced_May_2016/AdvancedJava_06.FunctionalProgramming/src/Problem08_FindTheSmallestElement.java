import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Problem08_FindTheSmallestElement {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split("[\\s]+");
        Integer[] numbers = new Integer[line.length];

        for (int i = 0; i < line.length; i++) {
            numbers[i] = Integer.parseInt(line[i]);
        }

        Function<Integer[], Integer> minFunc = integers -> {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < integers.length; i++) {
                if (integers[i] <= min) {
                    min = integers[i];
                    index = i;
                }
            }
            return index;
        };
        System.out.println(minFunc.apply(numbers));
    }
}
