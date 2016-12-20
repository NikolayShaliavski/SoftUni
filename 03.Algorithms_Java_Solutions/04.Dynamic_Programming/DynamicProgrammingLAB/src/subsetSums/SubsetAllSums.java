package subsetSums;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a set of integers -> find all possible sums
 * can be generated with these numbers(only sums without recovery).
 * We store sums in a HashSett
 */
public class SubsetAllSums {

    public static void main(String[] args) {
        int[] set = {1, 2, 5};

        Set<Integer> allSums = clacPossibleSums(set);

        for (Integer sum : allSums) {
            System.out.print(sum + " ");
        }
    }

    private static Set<Integer> clacPossibleSums(int[] set) {
        Set<Integer> possibleSums = new HashSet<>();
        possibleSums.add(0);

        for (int i = 0; i < set.length; i++) {
            Set<Integer> newSums = new HashSet<>();

            for (Integer sum : possibleSums) {
                int newSum = set[i] + sum;
                newSums.add(newSum);
            }
            possibleSums.addAll(newSums);
        }
        return possibleSums;
    }
}
