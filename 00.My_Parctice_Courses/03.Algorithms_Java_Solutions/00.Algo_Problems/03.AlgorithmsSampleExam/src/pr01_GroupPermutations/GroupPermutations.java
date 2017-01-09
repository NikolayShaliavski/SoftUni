package pr01_GroupPermutations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupPermutations {

    static List<Character> letters;
    static Map<Character, Integer> lettersCount;
    static StringBuilder result;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();

        lettersCount = new LinkedHashMap<>();
        result = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            Character letter = line.charAt(i);
            if (!lettersCount.containsKey(letter)) {
                lettersCount.put(letter, 0);
            }
            int prevCount = lettersCount.get(letter);
            lettersCount.put(letter, prevCount + 1);
        }

        letters = lettersCount.entrySet().stream().
                map(letter -> letter.getKey()).
                collect(Collectors.toList());

        permuteLetters(0);

        System.out.println(result.toString().trim());
    }

    private static void permuteLetters(int index) {
        if (index >= letters.size()) {
            appendPermutation();
            return;
        }
        permuteLetters(index + 1);
        for (int i = index + 1; i < letters.size(); i++) {
            swapLetters(index, i);
            permuteLetters(index + 1);
            swapLetters(index, i);
        }
    }

    private static void swapLetters(int index1, int index2) {
        Character old = letters.get(index1);
        letters.set(index1, letters.get(index2));
        letters.set(index2, old);
    }

    private static void appendPermutation() {
        for (int i = 0; i < letters.size(); i++) {
            int count = lettersCount.get(letters.get(i));
            for (int j = 0; j < count; j++) {
                result.append(letters.get(i));
            }
        }
        result.append(System.lineSeparator());
    }
}
