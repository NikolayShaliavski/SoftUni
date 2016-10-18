import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem13_SrabskoUnleashed {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^([A-Za-z]+ *[A-Za-z]+ *[A-Za-z]+) @([A-Za-z]+ *[A-Za-z]+ *[A-Za-z]+) (\\d+) (\\d+)$");
        Matcher matcher;
        LinkedHashMap<String, LinkedHashMap<String, Long>> statistic = new LinkedHashMap<>();
        String line = null;

        while (!(line = scan.nextLine()).equals("End")) {
            matcher = pattern.matcher(line);

            if (matcher.find()) {
                String venue = matcher.group(2);
                String singer = matcher.group(1);
                int price = Integer.parseInt(matcher.group(3));
                int tickets = Integer.parseInt(matcher.group(4));
                long allTicketsPrice = price * tickets;

                if (!statistic.containsKey(venue)) {
                    statistic.put(venue, new LinkedHashMap<>());
                }
                if (!statistic.get(venue).containsKey(singer)) {
                    statistic.get(venue).put(singer, allTicketsPrice);
                } else {
                    long oldAmount = statistic.get(venue).get(singer);
                    statistic.get(venue).put(singer, oldAmount + allTicketsPrice);
                }
            }
        }

        for (Map.Entry<String, LinkedHashMap<String, Long>> stat : statistic.entrySet()) {
            System.out.println(stat.getKey());
            LinkedHashMap<String, Long> allSingers = stat.getValue();
            allSingers.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).
                    forEach(k1 -> System.out.printf("#  %s -> %d%n", k1.getKey(), k1.getValue()));
        }
    }
}
