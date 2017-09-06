package pr01_GroupsOfEqualSums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupsOfEqualSums {

    private static boolean isFound;

    public static void main(String[] args) throws IOException {
        int[] nums = new int[4];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            int currNum = Integer.valueOf(bf.readLine().trim());
            nums[i] = currNum;
        }

        int sum = Arrays.stream(nums).sum();
        int targetSum = sum / 2;
        List<Integer> subset = new ArrayList<>();

        findSubsetSum(nums, 0, targetSum, subset);

        if (isFound && sum % 2 == 0) {
            System.out.println("Yes");
            System.out.println(targetSum);
        } else {
            System.out.println("No");
        }
    }

    private static void findSubsetSum(int[] nums, int index, int targetSum, List<Integer> subset) {

        if (index >= nums.length) {
            return;
        }
        int currSum = subset.stream().mapToInt(Integer::intValue).sum();
        if (currSum > targetSum) {
            return;
        }
        if (currSum == targetSum) {
            isFound = true;
            return;
        }

        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            findSubsetSum(nums, i + 1, targetSum, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
