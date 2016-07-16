import java.util.*;

public class LegendaryFarming {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        LinkedHashMap<String, Integer> legendary = new LinkedHashMap<>();
        TreeMap<String, Integer> junk = new TreeMap<>();

        legendary.put("fragments", 0);
        legendary.put("motes", 0);
        legendary.put("shards", 0);

        while (legendary.get("fragments").intValue() < 250 &&
                legendary.get("motes").intValue() < 250 &&
                legendary.get("shards").intValue() < 250) {

            String[] line = scn.nextLine().split(" ");

            for (int i = 0; i < line.length - 1; i += 2) {

                String mat = line[i + 1].toLowerCase();
                int value = Integer.parseInt(line[i]);

                if (legendary.containsKey(mat)) {
                    legendary.put(mat, legendary.get(mat) + value);
                }else if (!junk.containsKey(mat)) {
                    junk.put(mat, value);
                }else {
                    junk.put(mat, junk.get(mat) + value);
                }

                if (legendary.get("fragments").intValue() >= 250 ||
                        legendary.get("motes").intValue() >= 250 ||
                        legendary.get("shards").intValue() >= 250) {
                    break;
                }
            }
        }

        if (legendary.get("motes").intValue() >= 250) {
            System.out.println("Dragonwrath obtained!");
            legendary.put("motes", legendary.get("motes") - 250);
        }else if (legendary.get("fragments").intValue() >= 250) {
            System.out.println("Valanyr obtained!");
            legendary.put("fragments", legendary.get("fragments") - 250);
        } else if (legendary.get("shards").intValue() >= 250) {
            System.out.println("Shadowmourne obtained!");
            legendary.put("shards", legendary.get("shards") - 250);
        }

        //sorting map By values -> lambda expressions
        legendary.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).
                forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
        junk.entrySet().stream().forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
    }
}
