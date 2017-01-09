package subsetSums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given set of integers -> find all subsets == targetSum.
 * This generates all combinations and if current combination == targetSum
 * we print it (or save)
 *
 * This is solution for coins problems -> Unlimited && Limited amount of coins
 * We have a set of coins and given targetSum ->
 * find all combinations of coins to take to obtain targetSum
 * If coins are unlimited we can take one coin many times,
 * if they are limited we can take coin only ne time
 */
public class SubsetAllTargetSums {

    private static int subsetCounter;
    private static List<Integer> subset;

    public static void main(String[] args) {
        int[] set = {1,2,3,4,6};
        int targetSum = 6;
        subsetCounter = 0;
        subset = new ArrayList<>();

        findTargetSum(set, 0, targetSum);

        System.out.println("Total subsets: " + subsetCounter);
    }

    private static void findTargetSum(int[] set, int index, int targetSum) {
        int currSum = subset.stream().mapToInt(Integer::intValue).sum();
        if (currSum >= targetSum || index > set.length) {
            if (currSum == targetSum) {
                List<String> joined = subset.stream().map(num -> String.valueOf(num)).
                        collect(Collectors.toList());
                System.out.println(targetSum + " = " + String.join(" + ", joined));
                subsetCounter++;
            }
            return;
        }

        for (int i = index; i < set.length; i++) {
            subset.add(set[i]);
            /**
             * Recursion call with param index == i
             * generates subsets with repetition -> to obtain targetSum of 6
             * we can take 1 six times -> 1 1 1 1 1 1.
             * If we call recursion with i + 1
             * we generate all subsets without repetition
             */
            findTargetSum(set, i + 1, targetSum);
            subset.remove(subset.size() - 1);
        }
    }
}
