package pr06_ThreeBrothers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Solution from SoftUni GitHub
 */
public class ThreeBrothersDP {

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());

        for (int i = 0; i < n; i++) {
            nums = Arrays.stream(bf.readLine().split("[\\s]+")).
                    mapToInt(Integer::parseInt).toArray();

            if (dividePresents()) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean dividePresents() {
        int totalSum = Arrays.stream(nums).sum();
        if (totalSum % 3 != 0) {
            return false;
        }
        int targetSum = totalSum / 3;
        boolean[][] sumsReached = new boolean[targetSum + 1][targetSum + 1];

        sumsReached[0][0] = true;

        for (int gift : nums) {
            for (int s1 = targetSum; s1 >= 0; s1--) {
                for (int s2 = targetSum; s2 >= 0; s2--) {

                    if (sumsReached[s1][s2]) {
                        if (s1 + gift <= targetSum && !sumsReached[s1 + gift][s2]) {
                            sumsReached[s1 + gift][s2] = true;
                        }
                        if (s2 + gift <= targetSum && !sumsReached[s1][s2 + gift]) {
                            sumsReached[s1][s2 + gift] = true;
                        }
                    }
                }
            }
        }

        return sumsReached[targetSum][targetSum];
    }
}
