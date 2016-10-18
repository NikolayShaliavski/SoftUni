import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem06_AMinerTask {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        LinkedHashMap<String, Integer> resources = new LinkedHashMap<>();

        while (!line.equals("stop")) {
            int quantity = Integer.parseInt(scan.nextLine());
            if (!resources.containsKey(line)) {
                resources.put(line, quantity);
            } else {
                resources.put(line, resources.get(line) + quantity);
            }
            line = scan.nextLine();
        }

        for (Map.Entry<String, Integer> resource : resources.entrySet()) {
            System.out.printf("%s -> %s%n", resource.getKey(), resource.getValue());
        }
    }
}
