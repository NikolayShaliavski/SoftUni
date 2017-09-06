package knapsackProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knapsack {

    public static void main(String[] args) {
        Item[] items = {
                new Item(5, 30),
                new Item(8, 120),
                new Item(7, 10),
                new Item(0, 20),
                new Item(4, 50),
                new Item(5, 80),
                new Item(2, 10)
        };
        int knapsackCapacity = 20;

        List<Item> itemsTaken = fillKnapsack(items, knapsackCapacity);

        System.out.println("Knapsack weight capacity: " + knapsackCapacity);
        System.out.println("Take the following items in the knapsack:");
        for (Item item : itemsTaken) {
            System.out.println(item.toString());
        }
        System.out.println(
                String.format("Total weight: %d",
                        itemsTaken.stream().mapToInt(item -> item.getWeight()).sum()));
        System.out.println(
                String.format("Total price: %d",
                        itemsTaken.stream().mapToInt(item -> item.getPrice()).sum()));
    }

    private static List<Item> fillKnapsack(Item[] items, int capacity) {
        int[][] maxPrices = new int[items.length][capacity + 1];
        boolean[][] isTaken = new boolean[items.length][capacity + 1];

        fillFirstRow(items[0], maxPrices[0], isTaken[0]);

        for (int i = 1; i < maxPrices.length; i++) {
            Item item = items[i];
            for (int c = 0; c < maxPrices[i].length; c++) {

                int remainingCapacity = c - item.getWeight();
                maxPrices[i][c] = maxPrices[i - 1][c];

                if (remainingCapacity >= 0 &&
                        item.getPrice() + maxPrices[i - 1][remainingCapacity] > maxPrices[i][c]) {
                    maxPrices[i][c] = item.getPrice() + maxPrices[i - 1][remainingCapacity];
                    isTaken[i][c] = true;
                }

            }
        }
        List<Item> itemsTaken = takeItems(items, capacity, maxPrices, isTaken);
        return itemsTaken;
    }

    private static List<Item> takeItems(
            Item[] items, int capacity, int[][] maxPrices, boolean[][] isTaken) {
        List<Item> itemsTaken = new ArrayList<>();
        int itemIndex = items.length - 1;

        while (itemIndex >= 0) {
            if (isTaken[itemIndex][capacity]) {
                Item itemToTake = items[itemIndex];
                itemsTaken.add(itemToTake);
                capacity -= itemToTake.getWeight();
            }
            itemIndex--;
        }
        Collections.reverse(itemsTaken);
        return itemsTaken;
    }

    private static void fillFirstRow(Item item, int[] maxPrice, boolean[] isTaken) {
        for (int c = 0; c < maxPrice.length; c++) {
            if (item.getWeight() <= c) {
                maxPrice[c] = item.getPrice();
                isTaken[c] = true;
            }
        }
    }
}
