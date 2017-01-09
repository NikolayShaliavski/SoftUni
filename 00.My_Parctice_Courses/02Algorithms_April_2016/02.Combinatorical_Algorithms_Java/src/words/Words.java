package words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Words {

    private static char[] letters;
    private static int permutationsCounter;
    private static Set<Long> words;
    private static int[] keys;
    private static Map<Character, Integer> keyMap;
    private static int steps = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        letters = reader.readLine().toCharArray();
        permutationsCounter = 0;
        words = new HashSet<>();
        keys = new int[letters.length];
        keyMap = new HashMap<>();

        generateKeys();

        permute(0);

        System.out.println(permutationsCounter);
        System.out.println(steps);
    }

    private static void permute(int startIndex) {
        steps++;
        if (startIndex >= letters.length - 1) {
            Long newWord = buildNewKey();
            if (isPossible() && !words.contains(newWord)) {
                permutationsCounter++;
                words.add(newWord);
            }
            for (int i = 0; i < letters.length; i++) {
                System.out.print(letters[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = startIndex; i < letters.length; i++) {

            if (i < letters.length - 1 && letters[i] == letters[i + 1]) {
                continue;
            }
            swap(i, startIndex);
            permute(startIndex + 1);
            swap(i, startIndex);
//            if (i == letters.length - 1) {
//                swap(i, startIndex);
//                permute(startIndex + 1);
//                swap(i, startIndex);
//            }
        }
    }

    private static Long buildNewKey() {
        long newKey = keys[0];
        for (int i = 1; i < keys.length; i++) {
            if (keys[i] > 9) {
                newKey = newKey * 100 + keys[i];
            } else {
                newKey = newKey * 10 + keys[i];
            }
        }
        return newKey;
    }

    private static void generateKeys() {
        int counter = 1;
        for (char letter : letters) {
            if (!keyMap.containsKey(letter)) {
                keyMap.put(letter, counter);
                counter++;
            }
        }
        for (int i = 0; i < letters.length; i++) {
            keys[i] = keyMap.get(letters[i]);
        }
    }

    private static boolean isPossible() {
        for (int i = 0; i < letters.length - 1; i++) {
            if (letters[i] == letters[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(int i, int j) {
        if (i == j) {
            return;
        }
        char temp = letters[i];
        letters[i] = letters[j];
        letters[j] = temp;

        int temp2 = keys[i];
        keys[i] =keys[j];
        keys[j] = temp2;
    }
}
