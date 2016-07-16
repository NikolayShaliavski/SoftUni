import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class Problem04_FindEvensOrOdds {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split("\\s+");
        String command = reader.readLine();
        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);

        Predicate<Integer> oddOrEven;

        if (command.equals("odd")) {
            oddOrEven = number -> number % 2 != 0;
        } else {
            oddOrEven = number -> number % 2 == 0;
        }

        for (int i = start; i <= end; i++) {
            if (oddOrEven.test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
