import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class Problem_04 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, TreeMap<String, Long>> regions = new TreeMap<>();

        String line = reader.readLine();
        while (!line.equals("Count em all")) {
            String[] tokens = line.split(" \\-> ");
            String region = tokens[0];
            String type = tokens[1];
            Long count = Long.valueOf(tokens[2]);

            if (!regions.containsKey(region)) {
                regions.put(region, new TreeMap<>());
                regions.get(region).put("Black", 0L);
                regions.get(region).put("Red", 0L);
                regions.get(region).put("Green", 0L);
            }
            if (type.equals("Black")) {
                Long amount = regions.get(region).get("Black");
                regions.get(region).put("Black", amount + count);
            } else if (type.equals("Red")) {
                Long amountRed = regions.get(region).get("Red");
                regions.get(region).put("Red", amountRed + count);
            } else if (type.equals("Green")) {
                Long amountGreen = regions.get(region).get("Green");
                regions.get(region).put("Green", amountGreen + count);
            }
            line = reader.readLine();
        }
        rearrangeTypes(regions);
        ArrayList<String> sortedRegions = sortRegions(regions);
        for (int i = 0; i < sortedRegions.size(); i++) {
            String region = sortedRegions.get(i);
            System.out.println(region);
            regions.get(region).entrySet().stream()
                    .sorted((type1, type2) -> Long.compare(type2.getValue(), type1.getValue()))
                    .forEach(type -> System.out.printf("-> %s : %d%n", type.getKey(), type.getValue()));

        }
    }

    private static ArrayList<String> sortRegions(TreeMap<String, TreeMap<String, Long>> regions) {
        ArrayList<String> sortedRegions = new ArrayList<>();
        regions.entrySet().stream()
                .sorted((reg1, reg2) -> {
                    Long reg1Black = reg1.getValue().get("Black");
                    Long reg2Black = reg2.getValue().get("Black");
                    Integer result = Long.compare(reg2Black, reg1Black);
                    if (result == 0) {
                        result = Integer.compare(reg1.getKey().length(), reg2.getKey().length());
                    }
                    return result;
                }).forEach(reg -> sortedRegions.add(reg.getKey()));

        return sortedRegions;
    }

    private static void rearrangeTypes(TreeMap<String, TreeMap<String, Long>> regions) {
        for (String region : regions.keySet()) {
            if (regions.get(region).get("Green") >= 1_000_000) {
                Long amountGreen = regions.get(region).get("Green");
                int toAdd = (int) (amountGreen / 1_000_000);
                int reminder = (int) (amountGreen % 1_000_000);

                Long amountRed = regions.get(region).get("Red");
                regions.get(region).put("Red", amountRed + toAdd);
                regions.get(region).put("Green", (long)reminder);
            }

            if (regions.get(region).get("Red") >= 1_000_000) {
                Long amountGreen = regions.get(region).get("Red");
                int toAdd = (int) (amountGreen / 1_000_000);
                int reminder = (int) (amountGreen % 1_000_000);

                Long amountRed = regions.get(region).get("Black");
                regions.get(region).put("Black", amountRed + toAdd);
                regions.get(region).put("Red", (long)reminder);
            }

        }
    }
}
