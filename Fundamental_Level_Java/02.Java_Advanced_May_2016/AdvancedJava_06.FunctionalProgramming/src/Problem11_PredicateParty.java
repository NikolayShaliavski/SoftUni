import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Problem11_PredicateParty {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split("[\\s]+");
        List<String> people = new ArrayList<String>(Arrays.asList(line));

        String commands = reader.readLine();

        while (!commands.equals("Party!")) {
            String[] tokens = commands.split("[\\s]+");
            String command = tokens[0];
            String cryteria = tokens[1];
            String numOrLetter = tokens[2];
            Predicate<String> tester = createTester(cryteria, numOrLetter);
            List<String> temp = new ArrayList<>();
            switch (command) {
                case "Remove":
                    for (int i = 0; i < people.size(); i++) {
                        if (!tester.test(people.get(i))) {
                            temp.add(people.get(i));
                        }
                    }
                    break;
                case "Double":
                    for (int i = 0; i < people.size(); i++) {
                        if (tester.test(people.get(i))) {
                            temp.add(people.get(i));
                            temp.add(people.get(i));
                        } else {
                            temp.add(people.get(i));
                        }
                    }
                    break;
            }
            people = temp;
            commands = reader.readLine();
        }
        if (people.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            String names = people.toString().replace("[", "").replace("]", "");
            System.out.println(names + " are going to the party!");
        }
    }

    private static Predicate<String> createTester(String cryteria, String numOrLetter) {
        switch (cryteria) {
            case "StartsWith":
                return name -> name.startsWith(numOrLetter);
            case "EndsWith":
                return name -> name.endsWith(numOrLetter);
            case "Length":
                Integer length = Integer.valueOf(numOrLetter);
                return name -> name.length() == length;
        }
        return null;
    }
}
