import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiFunction;

public class Problem14_TriFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer number = Integer.valueOf(reader.readLine());
        String[] names = reader.readLine().split("[\\s]+");

        BiFunction<Integer, String[], String> function = createFunction();
        System.out.println(function.apply(number, names));
    }

    private static BiFunction<Integer,String[],String> createFunction() {
        BiFunction<Integer, String[], String> func = (num, names) -> {
            for (String name : names) {
                int sum = 0;
                for (int i = 0; i < name.length(); i++) {
                    sum += name.charAt(i);
                }
                if (sum >= num) {
                    return name;
                }
            }
            return "";
        };
        return func;
    }

}
