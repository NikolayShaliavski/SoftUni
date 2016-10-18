import java.util.*;

public class LoggsAgregator {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        int lines = console.nextInt();
        console.nextLine();
        TreeMap<String, TreeMap<String, Integer>> logs = new TreeMap<>();

        for (int i = 0; i < lines; i++) {
            String[] line = console.nextLine().split(" ");
            String user = line[1];
            String adressIP = line[0];
            int time = Integer.parseInt(line[2]);

            if (!logs.containsKey(user)) {
                logs.put(user, new TreeMap<>());
            }
            if (!logs.get(user).containsKey(adressIP)) {
                logs.get(user).put(adressIP, 0);
            }
            logs.get(user).put(adressIP, logs.get(user).get(adressIP) + time);

        }
        for (Map.Entry entry1 : logs.entrySet()) {
            System.out.printf("%s: ", entry1.getKey());
            int sum = 0;
                Collection collection = logs.get(entry1.getKey()).values();
                Iterator itr = collection.iterator();
                while (itr.hasNext()) {
                    String current = itr.next().toString();
                    sum += Integer.parseInt(current);
                }
            System.out.printf("%d ", sum);
            System.out.println(logs.get(entry1.getKey()).keySet());
        }
    }
}
