import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_04_AshesOfRoses {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, TreeMap<String, Long>> ashesOfRoses = new TreeMap<>();

        Pattern pattern = Pattern.compile("^Grow <([A-Z][a-z]*)> <([A-Za-z0-9]*)> (\\d*)$");
        Matcher matcher;
        String line = reader.readLine();

        while (!line.equals("Icarus, Ignite!")) {
            matcher = pattern.matcher(line);
            if (!matcher.find()) {
                line = reader.readLine();
                continue;
            }
            String region = matcher.group(1);
            String color = matcher.group(2);
            long amount = Integer.valueOf(matcher.group(3));

            if (!ashesOfRoses.containsKey(region)) {
                ashesOfRoses.put(region, new TreeMap<>());
            }
            if (!ashesOfRoses.get(region).containsKey(color)) {
                ashesOfRoses.get(region).put(color, amount);
            } else {
                long currentAmount = ashesOfRoses.get(region).get(color);
                ashesOfRoses.get(region).put(color, currentAmount + amount);
            }
            line = reader.readLine();
        }
        List<String> sortedRegions = new ArrayList<>();
        ashesOfRoses.entrySet().stream().
                sorted((reg1, reg2) -> {
                    Long firstRegionRoses = sumRoses(reg1.getValue());
                    Long secondRegionRoses = sumRoses(reg2.getValue());
                    return secondRegionRoses.compareTo(firstRegionRoses);
                }).
                forEach(region -> sortedRegions.add(region.getKey()));

        for (String sortedRegion : sortedRegions) {
            String currentRegion = sortedRegion;
            System.out.println(currentRegion);
            TreeMap<String, Long> currentRegionRoses = ashesOfRoses.get(sortedRegion);
            currentRegionRoses.entrySet().stream().
                    sorted((firstRoses, secondRoses) -> firstRoses.getValue().compareTo(secondRoses.getValue())).
                    forEach(currentRoses -> System.out.printf("*--%s | %d %n", currentRoses.getKey(), currentRoses.getValue()));
        }
    }

    private static Long sumRoses(TreeMap<String, Long> region) {
        return region.values().stream().
                mapToLong(Number::longValue).sum();
    }
}

