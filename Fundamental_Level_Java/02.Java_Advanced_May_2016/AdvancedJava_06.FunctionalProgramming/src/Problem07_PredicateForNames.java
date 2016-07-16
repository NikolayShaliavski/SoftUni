import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class Problem07_PredicateForNames {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        String[] names = reader.readLine().split("[\\s]+");

        Predicate<String> isLonger = name -> name.length() <= length;

        for (String name : names) {
            if (isLonger.test(name)) {
                System.out.println(name);
            }
        }
    }
}
