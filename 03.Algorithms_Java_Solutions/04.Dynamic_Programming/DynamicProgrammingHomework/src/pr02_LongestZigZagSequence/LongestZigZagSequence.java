package pr02_LongestZigZagSequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestZigZagSequence {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] sequence =
                Arrays.stream(bf.readLine().split("[\\,]")).mapToInt(Integer::parseInt).toArray();
        //{8, 3, 5, 7, 0, 8, 9, 10, 20, 20, 20, 12, 19, 11};

        List<Integer> lzs = findLzs(sequence);
        for (Integer num : lzs) {
            System.out.print(num + " ");
        }
    }

    private static List<Integer> findLzs(int[] sequence) {

        int[] evenLen = new int[sequence.length];
        int[] evenPrev = new int[sequence.length];
        int[] oddLen = new int[sequence.length];
        int[] oddPrev = new int[sequence.length];

        boolean evenOddApproach = true;

        int maxLen = 0;
        int lastIndex = -1;

        for (int i = 0; i < sequence.length; i++) {

            evenLen[i] = 1;
            evenPrev[i] = -1;
            oddLen[i] = 1;
            oddPrev[i] = -1;

            for (int j = 0; j < i; j++) {

                if (evenLen[j] + 1 > evenLen[i])
                {
                    if ((evenLen[j] % 2 == 0 && sequence[i] < sequence[j]) ||
                            (evenLen[j] % 2 == 1 && sequence[i] > sequence[j]))
                    {
                        evenLen[i] = evenLen[j] + 1;
                        evenPrev[i] = j;
                    }
                }

                if (oddLen[j] + 1 > oddLen[i])
                {
                    if ((oddLen[j] % 2 == 0 && sequence[i] > sequence[j]) ||
                            (oddLen[j] % 2 == 1 && sequence[i] < sequence[j]))
                    {
                        oddLen[i] = oddLen[j] + 1;
                        oddPrev[i] = j;
                    }
                }
            }

            if (evenLen[i] > maxLen) {
                maxLen = evenLen[i];
                lastIndex = i;
                evenOddApproach = true;
            }
            if (oddLen[i] > maxLen) {
                maxLen = oddLen[i];
                lastIndex = i;
                evenOddApproach = false;
            }
        }
        List<Integer> lzs = new ArrayList<>();

        if (evenOddApproach) {
            while (lastIndex != -1) {
                lzs.add(sequence[lastIndex]);
                lastIndex = evenPrev[lastIndex];
            }
        } else {
            while (lastIndex != -1) {
                lzs.add(sequence[lastIndex]);
                lastIndex = oddPrev[lastIndex];
            }
        }
        Collections.reverse(lzs);
        return lzs;
    }
}
