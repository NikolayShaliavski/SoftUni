import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_01 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.valueOf(reader.readLine());
        List<String> lines = new ArrayList<>();
        //LinkedHashMap<String, Integer> bunker = new LinkedHashMap<>();
        List<Integer> bunker = new ArrayList<>();
        Queue<String> allBunkers = new ArrayDeque<>();
        int weapons = 0;
        String myBunker = "";
        String line = reader.readLine();

        while (!line.equals("Bunker Revision")) {
            String[] tokens = line.split("[\\s]+");
            lines.addAll(Arrays.asList(tokens));
            line = reader.readLine();
        }

        for (String current : lines) {
            boolean isDigit = checkCurrent(current);
            if (!isDigit) {
                allBunkers.add(current);
            } else {
                int currentWeapon = Integer.valueOf(current);
                if (allBunkers.size() > 0 && (weapons + currentWeapon) <= capacity) {
                    bunker.add(currentWeapon);
                    weapons += currentWeapon;
                } else if (allBunkers.size() > 1 && (weapons + currentWeapon) >= capacity) {
                    String bunkerToRemove = allBunkers.remove();
                    if (bunker.size() > 0) {
                        System.out.printf("%s -> %s%n", bunkerToRemove, bunker.toString().replace("[", "").replace("]", ""));
                    } else {
                        System.out.printf("%s -> Empty%n", bunkerToRemove);
                    }
                    bunker.clear();
                    weapons = 0;
                    if (currentWeapon > capacity) {
                        continue;
                    }
                    bunker.add(currentWeapon);
                    weapons += currentWeapon;
                } else if ((weapons + currentWeapon) == capacity) {
                    String bunkerToPrint = allBunkers.peek();
                    bunker.add(currentWeapon);
                    weapons += currentWeapon;
                    System.out.printf("%s -> %s%n", bunkerToPrint, bunker.toString().replace("[", "").replace("]", ""));
                } else {
                    while ((weapons + currentWeapon) > capacity) {
                        int toRemove = bunker.get(0);
                        bunker.remove(0);
                        weapons -= toRemove;
                    }
                    bunker.add(currentWeapon);
                    weapons += currentWeapon;
                }
            }
        }
    }

    private static boolean checkCurrent(String current) {
        try {
            int digit = Integer.parseInt(current);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
