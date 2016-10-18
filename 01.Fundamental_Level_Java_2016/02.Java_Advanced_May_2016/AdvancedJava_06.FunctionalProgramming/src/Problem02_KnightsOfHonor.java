import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class Problem02_KnightsOfHonor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] names = reader.readLine().split("\\s+");

        Consumer<String> print = name -> System.out.println("Sir " + name);

        for (String name : names) {
            print.accept(name);
        }
    }
}
