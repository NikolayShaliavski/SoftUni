package pr06_ThreeBrothers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Greedy choice here doesn't work
 * This solution is not correct
 */
public class ThreeBrothersGreedy {

    static List<Integer> gifts;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());

        for (int i = 0; i < n; i++) {
            gifts = Arrays.stream(bf.readLine().split("[\\s]+")).
                    mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

            if (dividePresents()) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean dividePresents() {
        int totalSum = gifts.stream().mapToInt(Integer::intValue).sum();
        
        if (totalSum % 3 != 0) {
            return false;
        }
        int targetSum = totalSum / 3;

        for (int i = 0; i < 2; i++) {
             int sum = targetSum;

            while (sum > 0) {
                List<Integer> possible = new ArrayList<>();

                for (Integer gift : gifts) {
                    if (gift <= sum) {
                        possible.add(gift);
                    }
                }

                if (possible.size() == 0) {
                    return false;
                }
                Integer max = possible.stream().
                        collect(Collectors.summarizingInt(Integer::intValue)).getMax();
                sum -= max;
                gifts.remove(max);
            }
        }
        return true;
    }
}
