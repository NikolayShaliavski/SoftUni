import java.io.DataOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prolem14_DragonArmy {

    static final int defaultHealth = 250;
    static final int defaultDamage = 45;
    static final int defaultArmor = 10;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = Integer.parseInt(scan.nextLine());
        LinkedHashMap<String, TreeMap<String, ArrayList<Integer>>> dragonArmy = new LinkedHashMap<>();

        Pattern pattern = Pattern.compile("^([A-Z][A-Za-z]+) ([A-Z][A-Za-z]+) ([\\d]+|null) ([\\d]+|null) ([\\d]+|null)$");
        Matcher matcher;
        for (int i = 0; i < lines; i++) {
            String line = scan.nextLine();
            matcher = pattern.matcher(line);

            if (matcher.find()) {
                String type = matcher.group(1);
                String name = matcher.group(2);

                int damage = checkStats(matcher.group(3), defaultDamage);
                int health = checkStats(matcher.group(4), defaultHealth);
                int armor = checkStats(matcher.group(5), defaultArmor);

                if (!dragonArmy.containsKey(type)) {
                   dragonArmy.put(type, new TreeMap<>());
                }
                if (!dragonArmy.get(type).containsKey(name)) {
                    dragonArmy.get(type).put(name, new ArrayList<>());
                    dragonArmy.get(type).get(name).add(damage);
                    dragonArmy.get(type).get(name).add(health);
                    dragonArmy.get(type).get(name).add(armor);
                } else {
                    dragonArmy.get(type).get(name).clear();
                    dragonArmy.get(type).get(name).add(damage);
                    dragonArmy.get(type).get(name).add(health);
                    dragonArmy.get(type).get(name).add(armor);
                }
            }
        }
        for (Map.Entry<String, TreeMap<String, ArrayList<Integer>>> allTypes : dragonArmy.entrySet()) {
            ArrayList<Double> averages = getAverages(allTypes.getValue());
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", allTypes.getKey(), averages.get(0), averages.get(1), averages.get(2));
            printDragons(allTypes.getValue());
        }
    }

    private static void printDragons(TreeMap<String, ArrayList<Integer>> dragons) {
        for (Map.Entry<String, ArrayList<Integer>> dragon : dragons.entrySet()) {
            System.out.printf("-%s -> ", dragon.getKey());
            System.out.printf("damage: %d, health: %d, armor: %d%n", dragon.getValue().get(0), dragon.getValue().get(1), dragon.getValue().get(2));
        }
    }

    private static ArrayList<Double> getAverages(TreeMap<String, ArrayList<Integer>> dragons) {
        ArrayList<Double> averages = new ArrayList<>();
        double damageAv = 0D;
        double healthAv = 0D;
        double armorAv = 0D;
        for (ArrayList<Integer> average : dragons.values()) {
            damageAv += average.get(0);
            healthAv += average.get(1);
            armorAv += average.get(2);
        }
        int size = dragons.size();
        averages.add(damageAv / size);
        averages.add(healthAv / size);
        averages.add(armorAv / size);
        return averages;
    }

    private static int checkStats(String group, int defaultStat) {
        int stat = 0;
        if (group.equals("null")) {
            stat = defaultStat;
        } else {
            stat = Integer.parseInt(group);
        }
        return stat;
    }
}
