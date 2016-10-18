import java.util.HashMap;
import java.util.Scanner;

public class Problem05_Phonebook {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, String> phonebook = new HashMap<>();
        String line = scan.nextLine();

        while (!line.equals("search")) {
            String[] details = line.split("-");
            String name = details[0];
            String phoneNumber = details[1];

            phonebook.put(name, phoneNumber);
            line = scan.nextLine();
        }
        line = scan.nextLine();
        while (!line.equals("stop")) {
            if (phonebook.containsKey(line)) {
                System.out.printf("%s -> %s%n", line, phonebook.get(line));
            } else {
                System.out.printf("Contact %s does not exist.%n", line);
            }
            line = scan.nextLine();
        }
    }
}
