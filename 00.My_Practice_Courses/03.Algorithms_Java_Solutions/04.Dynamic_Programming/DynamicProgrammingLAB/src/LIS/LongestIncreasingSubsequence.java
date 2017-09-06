package LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int[] sequence = Arrays.stream(bf.readLine().split(" ")).
//                mapToInt(Integer::parseInt).toArray();

        int[] sequence = {9,100,20,30,40};
        List<Integer> lis = findLIS(sequence);

        for (Integer num : lis) {
            System.out.print(num + " ");
        }
    }

    private static List<Integer> findLIS(int[] sequence) {
        int[] len = new int[sequence.length];
        int[] prev = new int[sequence.length];

        int lastIndex = -1;
        int maxLen = 0;

        for (int i = 0; i < sequence.length; i++) {

            int currentNum = sequence[i];
            len[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (currentNum > sequence[j] && len[j] + 1 > len[i]) {
                    len[i] = len[j] + 1;
                    prev[i] = j;
                }
            }
            if (len[i] > maxLen) {
                maxLen = len[i];
                lastIndex = i;
            }
        }

        List<Integer> lis = recoverSubsequence(lastIndex, prev, sequence);
        return lis;
    }

    private static List<Integer> recoverSubsequence(int lastIndex, int[] prev, int[] sequence) {
        List<Integer> lis = new ArrayList<>();

        while (lastIndex != -1) {
            lis.add(sequence[lastIndex]);
            lastIndex = prev[lastIndex];
        }
        Collections.reverse(lis);
        return lis;
    }
}
