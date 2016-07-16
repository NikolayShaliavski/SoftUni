import java.util.*;

public class Problem07_FixEmails {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, String> dataBase = new LinkedHashMap<>();

        String person = scan.nextLine();
        while (!person.equals("stop")) {
            String email = scan.nextLine();

            if (email.toLowerCase().endsWith(".us") || email.toLowerCase().endsWith(".uk")) {
                person = scan.nextLine();
                continue;
            } else {
                dataBase.put(person, email);
                person = scan.nextLine();
            }
        }
        for (Map.Entry<String, String> personInfo : dataBase.entrySet()) {
            System.out.printf("%s -> %s%n", personInfo.getKey(), personInfo.getValue());
        }
    }
}
