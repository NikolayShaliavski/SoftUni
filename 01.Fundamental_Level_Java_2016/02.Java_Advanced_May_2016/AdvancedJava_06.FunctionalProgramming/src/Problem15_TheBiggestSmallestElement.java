import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
interface TriFunction<T1, T2, T3, TR> {

    public TR apply(T1 first, T2 second, T3 third);
}

public class Problem15_TheBiggestSmallestElement {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] integers = reader.readLine().split("[\\s]+");
        List<Integer> numbers = Arrays.stream(integers).map(Integer::valueOf).collect(Collectors.toList());
        String command = reader.readLine();

        Function<List<Integer>, Integer> minElementFunc = createMinFunc();
        Function<List<Integer>, Integer> maxElementFunc = createMaxFunc();
        TriFunction<Function<List<Integer>, Integer>, List<Integer>, Integer, Integer> triFunction = createTriFunction();

        Integer result = 0;
        switch (command) {
            case "minEven":
                result = triFunction.apply(minElementFunc, numbers, 0);
                break;
            case "maxEven":
                result = triFunction.apply(maxElementFunc, numbers, 0);
                break;
            case "minOdd":
                result = triFunction.apply(minElementFunc, numbers, 1);
                break;
            case "maxOdd":
                result = triFunction.apply(maxElementFunc, numbers, 1);
                break;
        }
        if (result == null) {
            System.out.println();
        } else {
            System.out.println(result);
        }
    }

    private static Function<List<Integer>, Integer> createMaxFunc() {
        Function<List<Integer>, Integer> maxElementFunc = numbers -> {
            Integer max = Integer.MIN_VALUE;
            for (Integer number : numbers) {
                if (number > max) {
                    max = number;
                }
            }
            return max;
        };
        return maxElementFunc;
    }

    private static Function<List<Integer>, Integer> createMinFunc() {
        Function<List<Integer>, Integer> minElement = numbers -> {
            Integer min = Integer.MAX_VALUE;
            for (Integer number : numbers) {
                if (number < min) {
                    min = number;
                }
            }
            return min;
        };
        return minElement;
    }

    private static TriFunction<Function<List<Integer>, Integer>, List<Integer>, Integer, Integer> createTriFunction() {
        TriFunction<Function<List<Integer>, Integer>, List<Integer>, Integer, Integer> triFunction = (func, numbers, reminder) -> {
            List<Integer> buffer = new ArrayList<>();

            for (Integer number : numbers) {
                if (number % 2 == reminder) {
                    buffer.add(number);
                }
            }
            if (buffer.size() > 0) {
                return func.apply(buffer);
            }
            return null;
        };
        return triFunction;
    }
}
