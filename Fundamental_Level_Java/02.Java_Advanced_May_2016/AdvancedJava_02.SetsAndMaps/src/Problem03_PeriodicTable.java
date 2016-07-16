import java.util.Scanner;
import java.util.TreeSet;

public class Problem03_PeriodicTable {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = Integer.parseInt(scan.nextLine());
        TreeSet<String> uniqueElements = new TreeSet<>();

        for (int i = 0; i < lines; i++) {
            String[] elements = scan.nextLine().split("\\s+");
            for (String element : elements) {
                uniqueElements.add(element);
            }
        }
        for (String uniqueElement : uniqueElements) {
            System.out.printf("%s ", uniqueElement);
        }
    }
}
