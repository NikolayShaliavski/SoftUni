import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem09_UserLogs {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TreeMap<String, LinkedHashMap<String, Integer>> userLogs = new TreeMap<>();

        String line = scan.nextLine();
        while (!line.equals("end")) {
            Pattern pattern = Pattern.compile("IP=([0-9A-Za-z.:]+).*user=([0-9A-Za-z]+)");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String adress = matcher.group(1);
                String user = matcher.group(2);

                if (!userLogs.containsKey(user)) {
                    userLogs.put(user, new LinkedHashMap<>());
                }
                if (!userLogs.get(user).containsKey(adress)) {
                    userLogs.get(user).put(adress, 1);
                } else {
                    userLogs.get(user).put(adress, userLogs.get(user).get(adress) + 1);
                }
            }
            line = scan.nextLine();
        }

        for (Map.Entry<String, LinkedHashMap<String, Integer>> user : userLogs.entrySet()) {
            System.out.printf("%s:%n", user.getKey());
            LinkedHashMap<String, Integer> userInfo = user.getValue();
            int size = userInfo.keySet().size();
            for (Map.Entry<String, Integer> currentUser : userInfo.entrySet()) {
                if (size > 1) {
                    System.out.printf("%s => %d, ", currentUser.getKey(), currentUser.getValue());
                } else {
                    System.out.printf("%s => %d.%n", currentUser.getKey(), currentUser.getValue());
                }
                size--;
            }
        }
    }
}
