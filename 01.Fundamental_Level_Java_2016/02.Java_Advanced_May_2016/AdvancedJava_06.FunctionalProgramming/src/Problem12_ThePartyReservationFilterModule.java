import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Problem12_ThePartyReservationFilterModule {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] invitations = reader.readLine().split("[\\s]+");
        List<String> names = new ArrayList<>();
        names.addAll(Arrays.asList(invitations));
        List<String> filtered = new ArrayList<>();

        String line = reader.readLine();
        while (!line.equals("Print")) {
            String[] tokens = line.split("[;]");
            String command = tokens[0];
            String filter = tokens[1];
            String variable = tokens[2];
            Predicate<String> tester = createTester(filter, variable);


            switch (command) {
                case "Add filter":
                    for (String name : names) {
                        if (tester.test(name)) {
                            filtered.add(name);
                        }
                    }
                    break;
                case "Remove filter":
                    List<String> toRemove = new ArrayList<>();
                    for (String name : filtered) {
                        if (tester.test(name)) {
                            toRemove.add(name);
                        }
                    }
                    filtered.removeAll(toRemove);
                    break;
            }
            line = reader.readLine();
        }
        names.removeAll(filtered);
        for (String name : names) {
            System.out.printf("%s ", name);
        }
        System.out.println();
    }

    private static Predicate<String> createTester(String filter, String variable) {
        switch (filter) {
            case "Starts with":
                return name -> name.startsWith(variable);
            case "Ends with":
                return name -> name.endsWith(variable);
            case "Length":
                return name -> name.length() == Integer.valueOf(variable);
            case "Contains":
                return name -> name.contains(variable);
        }
        return null;
    }
}
