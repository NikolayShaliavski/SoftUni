package words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Words {

    private static int counter = 0;
    private static char[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sequence = bf.readLine().toCharArray();
        permute(0);
        System.out.println(counter);
    }

    private static void permute(int index) {

        if (index >= sequence.length) {
            if (isValid()) {
                counter++;
            }
            return;
        }
        Set<Character> memo = new HashSet<>();
        for (int i = index; i < sequence.length; i++) {
            if (!memo.contains(sequence[i])) {
                memo.add(sequence[i]);
                swap(i, index);

                permute(index + 1);

                swap(i, index);
            }
        }
    }

    private static void swap(int i, int j) {
        char old = sequence[i];
        sequence[i] = sequence[j];
        sequence[j] = old;
    }

    private static boolean isValid() {
        if (sequence.length == 0) {
            return false;
        }
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i - 1] == sequence[i]) {
                return false;
            }
        }
        return true;
    }
}
