package subsetSums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given set of integers -> find one sum == targetSum.
 * If there are several combinations of numbers we recover only one.
 * Repeats of numbers are allowed here
 * Uses boolean matrix to mark which sum (from 0 to targetSum + 1) is possible
 */
public class SubsetTargetSumRepeats {

    public static void main(String[] args) {
        int[] set = {3, 5, 2, 1};
        int targetSum = 6;

        boolean[] possibleSums = calcPossibleSums(set, targetSum);

        List<Integer> subset = recoverSubset(set, targetSum, possibleSums);

        if (subset.size() > 0) {
            List<String> joined = subset.stream().map(num -> String.valueOf(num)).
                    collect(Collectors.toList());
            System.out.println(targetSum + " = " + String.join(" + ", joined));
        }
    }

    private static boolean[] calcPossibleSums(int[] set, int targetSum) {
        boolean[] possible = new boolean[targetSum + 1];

        possible[0] = true;

        for (int sum = 0; sum < possible.length; sum++) {

            if (possible[sum]) {
                for (int i = 0; i < set.length; i++) {
                    int newSum = sum + set[i];
                    if (newSum <= targetSum) {
                        possible[newSum] = true;
                    }
                }
            }
        }
        return possible;
    }

    private static List<Integer> recoverSubset(
            int[] set, int targetSum, boolean[] possibleSums) {
        List<Integer> subset = new ArrayList<>();

        while (targetSum > 0) {
            for (int i = 0; i < set.length; i++) {
                int newSum = targetSum - set[i];
                if (newSum >= 0 && possibleSums[newSum]) {
                    subset.add(set[i]);
                    targetSum = newSum;
                }
            }
        }
        return subset;
    }
}
