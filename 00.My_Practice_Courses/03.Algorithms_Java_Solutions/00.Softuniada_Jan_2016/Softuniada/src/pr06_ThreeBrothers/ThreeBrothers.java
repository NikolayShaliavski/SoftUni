package pr06_ThreeBrothers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * My solution like subset sums problem -> very slow
 */
public class ThreeBrothers {

    static boolean isFound;
    static boolean[] taken;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());

        for (int i = 0; i < n; i++) {
            int[] gifts = Arrays.stream(bf.readLine().split("[\\s]+")).
                    mapToInt(Integer::parseInt).toArray();
            taken = new boolean[gifts.length];

            int sum = Arrays.stream(gifts).sum();

            if (gifts.length < 3 || sum % 3 != 0) {
                System.out.println("No");
                continue;
            }
            int targetSum = sum / 3;
            List<Integer> firstBroSubset = new ArrayList<>();
            isFound = false;

            findGifts(gifts, 0, targetSum, 1, firstBroSubset);

            if (isFound) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static void findGifts(int[] gifts, int index, int targetSum, int brother, List<Integer> subset) {
        if (isFound) {
            return;
        }
        if (index > gifts.length) {
            return;
        }
        int currSum = subset.stream().mapToInt(Integer::intValue).sum();
        if (currSum > targetSum) {
            return;
        }
        if (currSum == targetSum) {
            if (brother == 3) {
                isFound = true;
                return;
            }
            List<Integer> newSubset = new ArrayList<>();
            findGifts(gifts, 0, targetSum, brother + 1, newSubset);
            return;
        }

        for (int i = index; i < gifts.length; i++) {
            if (!taken[i]) {
                taken[i] = true;
                subset.add(gifts[i]);
                findGifts(gifts, i + 1, targetSum, brother, subset);
                subset.remove(subset.size() - 1);
                taken[i] = false;
            }
        }
    }
}
