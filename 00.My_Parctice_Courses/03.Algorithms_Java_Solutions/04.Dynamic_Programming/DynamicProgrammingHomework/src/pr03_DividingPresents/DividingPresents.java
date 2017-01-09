package pr03_DividingPresents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DividingPresents {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] presents =
                Arrays.stream(bf.readLine().split("[\\,]")).mapToInt(Integer::parseInt).toArray();


        //find half sum for all presents
        int sumAllPresents = Arrays.stream(presents).sum();
        int median = sumAllPresents / 2;

        Map<Integer, Integer> possibleSums = findPossibleSums(presents, median);

        int alanSum = Integer.MIN_VALUE;
        int bobSum = 0;

        for (Integer sum : possibleSums.keySet()) {
            if (sum > alanSum) {
                alanSum = sum;
            }
        }

        bobSum = sumAllPresents - alanSum;

        List<Integer> alanPresents = findAlanPresents(possibleSums, alanSum);

        System.out.printf("Difference: %d%n", bobSum - alanSum);
        System.out.printf("Alan: %d Bob: %d%n", alanSum, bobSum);
        System.out.printf("Alan takes: ");
        for (Integer present : alanPresents) {
            System.out.print(present + " ");
        }
        System.out.println();
        System.out.println("Bob takes the rest.");
    }

    private static List<Integer> findAlanPresents(Map<Integer, Integer> presents, int alanSum) {
        List<Integer> alanPresents = new ArrayList<>();

        while (alanSum > 0) {
            int present = presents.get(alanSum);
            alanPresents.add(present);
            alanSum -= present;
        }

        return alanPresents;
    }

    /**
     * Generates all possible sums <= targetSum, which is half sum for all presents
     * We don't need all other sums > targetSum
     */
    private static Map<Integer, Integer> findPossibleSums(int[] presents, int targetSum) {

        Map<Integer, Integer> possibleSums = new HashMap<>();
        possibleSums.put(0, 0);

        for (int i = 0; i < presents.length; i++) {
            Map<Integer, Integer> newSums = new HashMap<>();
            for (Integer sum : possibleSums.keySet()) {
                int newSum = sum + presents[i];
                if (!possibleSums.containsKey(newSum) && newSum <= targetSum) {
                    newSums.put(newSum, presents[i]);
                }

            }
            for (Map.Entry<Integer, Integer> newSum : newSums.entrySet()) {
                possibleSums.put(newSum.getKey(), newSum.getValue());
            }
        }

        return possibleSums;
    }
}
