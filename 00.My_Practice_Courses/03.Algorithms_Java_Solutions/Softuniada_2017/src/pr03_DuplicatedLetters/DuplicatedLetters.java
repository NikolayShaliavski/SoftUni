package pr03_DuplicatedLetters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DuplicatedLetters {

    static String line;
    static char[] letters;

    public static void main(String[] args) throws IOException {
        readInput();

        letters = line.toCharArray();
        boolean removed = true;
        int counter = 0;

        while (removed) {
            removed = false;
            boolean[] removedLet = new boolean[letters.length];
            for (int i = 0; i < letters.length - 1; i++) {
                if (letters[i] == letters[i + 1]) {
                    removedLet[i] = true;
                    removedLet[i + 1] = true;
                    removed = true;
                    counter++;
                    i++;
                }
            }
            removeLetters(removedLet);
        }

        if (letters.length == 0) {
            System.out.println("Empty String");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < letters.length; i++) {
                sb.append(letters[i]);
            }
            System.out.println(sb);
        }
        System.out.println(counter + " operations");
    }

    private static void removeLetters(boolean[] removedLet) {
        List<Character> newLetters = new ArrayList<>();
        for (int i = 0; i < letters.length; i++) {
            if (!removedLet[i]) {
                newLetters.add(letters[i]);
            }
        }
        letters = new char[newLetters.size()];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = newLetters.get(i);
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        line = bf.readLine();
    }
}
