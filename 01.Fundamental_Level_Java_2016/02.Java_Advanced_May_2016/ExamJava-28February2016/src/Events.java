import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Events {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numberOfEvents = Integer.parseInt(scan.nextLine());
        TreeMap<String, TreeMap<String, List<String>>> events = new TreeMap<>();

        for (int i = 0; i < numberOfEvents; i++) {
            String currentEvent = scan.nextLine();
            Pattern pattern = Pattern.compile("^#([A-Za-z]+):\\s*@([A-Za-z]+)\\s*([0-1][0-9]|[2][0-3]):([0-5][0-9])$");
            Matcher matcher = pattern.matcher(currentEvent);
            if (matcher.find()) {

                String town = matcher.group(2);
                String name = matcher.group(1);
                String time = matcher.group(3) + ":" + matcher.group(4);

                if (!events.containsKey(town)) {
                    events.put(town, new TreeMap<>());
                }

                if (!events.get(town).containsKey(name)) {
                    events.get(town).put(name, new ArrayList<>());
                }
                events.get(town).get(name).add(time);
            }
        }
        String[] locations = scan.nextLine().split(",");
        Arrays.sort(locations);
        for (int i = 0; i < locations.length; i++) {
            if (events.containsKey(locations[i])) {
                System.out.println(locations[i] + ":");
                int counter = 1;
                for (Map.Entry person : events.get(locations[i]).entrySet()) {

                    List<String> times = (List<String>) person.getValue();
                    Collections.sort(times);
                    String time = times.toString();
                    System.out.printf("%d. %s -> %s\n", counter, person.getKey(), time.substring(1, time.length() - 1));
                    counter++;
                }
            }
        }
    }
}
