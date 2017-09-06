package pr06_ShopKeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShopKeeper {

    static Set<Integer> storage;
    static int[] orders;
    static Map<Integer, Integer> needed;
    static Set<Integer> notNeeded;

    public static void main(String[] args) throws IOException {
        readInput();
        int changes = 0;
        int freeProducts = notNeeded.size();

        for (int i = 0; i < orders.length - 1; i++) {
            int order = orders[i];
            if (!storage.contains(order)) {
                System.out.println("impossible");
                return;
            }
            needed.put(order, needed.get(order) - 1);
            if (needed.get(order) <= 0) {
                //notNeeded.add(order);
                freeProducts++;
                needed.remove(order);
                storage.remove(order);
            }

            int nextOrder = orders[i + 1];
            if (!storage.contains(nextOrder)) {
                changes++;
                if (freeProducts > 0) {
                    storage.add(nextOrder);
                    freeProducts--;
                    //notNeeded.remove(nextOrder);
                } else {
                    for (Integer product : storage) {
                        storage.remove(product);
                        storage.add(nextOrder);
                        break;
                    }
                }
            }
        }
        System.out.println(changes);
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        storage = new HashSet<>();
        String[] line = bf.readLine().split("[\\s]+");

        for (String s : line) {
            storage.add(Integer.valueOf(s));
        }

        needed = new HashMap<>();
        notNeeded = new HashSet<>();
        line = bf.readLine().split("[\\s]+");
        orders = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            int order = Integer.valueOf(line[i]);
            orders[i] = order;

            if (!needed.containsKey(order)) {
                needed.put(order, 0);
            }
            needed.put(order, needed.get(order) + 1);
        }

        for (Integer product : storage) {
            if (!needed.containsKey(product)) {
                notNeeded.add(product);
            }
        }
        storage.removeAll(notNeeded);
    }
}
