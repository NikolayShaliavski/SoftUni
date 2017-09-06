package subsetSums;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given set of integers -> find one sum == targetSum.
 * We store sums in a Map, so we can recover numbers used to obtain that sum.
 * If there are several combinations of numbers we recover only one.
 * Generates subset with no repeats
 */
public class SubsetTargetSum {

    public static void main(String[] args) {
        int[] set = {1, 2, 3};
        int targetSum = 6;

        Map<Integer, Integer> possibleSums = clacPossibleSums(set);

        if (possibleSums.containsKey(targetSum)) {
            List<Integer> subset = recoverSubset(targetSum, possibleSums);
            List<String> joined = subset.stream().map(num -> String.valueOf(num)).
                    collect(Collectors.toList());
            System.out.println(targetSum + " = " + String.join(" + ", joined));
        } else {
            System.out.println("Not possible sum: " + targetSum);
        }
    }

    private static List<Integer> recoverSubset(
            int targetSum, Map<Integer, Integer> possibleSums) {
        List<Integer> subset = new ArrayList<>();

        while (targetSum > 0) {
            int prevNum = possibleSums.get(targetSum);
            subset.add(prevNum);
            targetSum -= prevNum;
        }

        Collections.reverse(subset);
        return subset;
    }

    private static Map<Integer, Integer> clacPossibleSums(int[] set) {
        Map<Integer, Integer> possibleSums = new HashMap<>();
        possibleSums.put(0, 0);

        for (int i = 0; i < set.length; i++) {
            Map<Integer, Integer> newSums = new HashMap<>();

            for (Integer sum : possibleSums.keySet()) {
                int newSum = sum + set[i];
                if (!possibleSums.containsKey(newSum)) {
                    newSums.put(newSum, set[i]);
                }
            }
            for (Map.Entry<Integer, Integer> newSum : newSums.entrySet()) {
                possibleSums.put(newSum.getKey(), newSum.getValue());
            }
        }

        return possibleSums;
    }
}
