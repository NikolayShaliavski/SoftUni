import java.util.LinkedHashSet;
import java.util.Scanner;

public class Problem01_UniqueUserNames {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = Integer.parseInt(scan.nextLine());
        LinkedHashSet<String> uniqueNames = new LinkedHashSet<>();

        for (int i = 0; i < lines; i++) {
            uniqueNames.add(scan.nextLine());
        }

        for (String uniqueName : uniqueNames) {
            System.out.println(uniqueName);
        }
    }
}
