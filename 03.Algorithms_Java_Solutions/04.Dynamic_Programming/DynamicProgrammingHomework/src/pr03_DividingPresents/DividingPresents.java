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

        Map<Integer, Integer> possibleSums = findPossibleSums(presents);
        //find half sum for all presents
        int sumAllPresents = Arrays.stream(presents).sum();
        int halfSum = sumAllPresents / 2;

        int alanSum = 0;
        int bobSum = 0;

        if (possibleSums.containsKey(halfSum)) {
            alanSum = halfSum;
        } else {
            //we put halfSum into TreeMap and it will be placed between two sums with smallest difference
            possibleSums.put(halfSum, 0);
            List<Integer> sumsList = new ArrayList<>();
            for (Integer sum : possibleSums.keySet()) {
                sumsList.add(sum);
            }
            possibleSums.remove(halfSum);//remove it because we don't need it more

            for (int i = 0; i < sumsList.size(); i++) {
                if (sumsList.get(i) == halfSum && i > 0 && i < sumsList.size() - 1) {
                    alanSum = sumsList.get(i - 1);
                    //bobSum = sumsList.get(i + 1);
                    break;
                }
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

    private static Map<Integer, Integer> findPossibleSums(int[] presents) {
        Map<Integer, Integer> possibleSums = new TreeMap<>();
        possibleSums.put(0, 0);

        for (int i = 0; i < presents.length; i++) {
            Map<Integer, Integer> newSums = new HashMap<>();
            for (Integer sum : possibleSums.keySet()) {
                int newSum = sum + presents[i];
                if (!possibleSums.containsKey(newSum)) {
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
