import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class Problem01_ConsumerPrint {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] names = reader.readLine().split("\\s+");

        Consumer<String> print = name -> System.out.println(name);

        for (String name : names) {
            print.accept(name);
        }
    }
}
