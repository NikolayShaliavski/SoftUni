import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

public class Problem13_InfernoIII {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOfNums = reader.readLine().split("[\\s]+");
        List<Integer> numbers = new LinkedList<>();
        numbers.add(0);
        for (int i = 0; i < lineOfNums.length; i++) {
            numbers.add(Integer.valueOf(lineOfNums[i]));
        }
        numbers.add(0);

        String line = reader.readLine();
        Map<String, ArrayList<Integer>> allExcluded = new HashMap<>();

        while (!line.equals("Forge")) {
            String[] tokens = line.split("[;]");
            String command = tokens[0];
            String filterType = tokens[1];
            Integer variable = Integer.valueOf(tokens[2]);
            String keyForMap = filterType + variable;
            Predicate<Integer> tester;

            switch (command) {
                case "Exclude":
                    allExcluded.put(keyForMap, new ArrayList<>());
                    for (int i = 1; i < numbers.size() - 1; i++) {
                        int left = numbers.get(i - 1);
                        int right = numbers.get(i + 1);
                        tester = createTester(filterType, left, right, variable);
                        if (tester.test(numbers.get(i))) {
                            allExcluded.get(keyForMap).add(i);
                        }
                    }
                    break;
                case "Reverse":
                    if (allExcluded.containsKey(keyForMap)) {
                        allExcluded.remove(keyForMap);
                    }
                    break;
            }
            line = reader.readLine();
        }
        Set<Integer> toRemove = new HashSet<>();
        for (ArrayList<Integer> values : allExcluded.values()) {
            toRemove.addAll(values);
        }

        for (int i = 1; i < numbers.size() - 1; i++) {
            if (!toRemove.contains(i)) {
                System.out.printf("%d ", numbers.get(i));
            }
        }
        System.out.println();

    }


    private static Predicate<Integer> createTester(String filterType, int left, int right, int variable) {
        switch (filterType) {
            case "Sum Left":
                return number -> (number + left) == variable;
            case "Sum Right":
                return number -> (number + right) == variable;
            case "Sum Left Right":
                return number -> (number + left + right) == variable;
            default:
                return null;
        }
    }
}
