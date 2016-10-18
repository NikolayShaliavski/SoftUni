import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;

public class Problem11_Palindromes {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] text = reader.readLine().split("\\W+");
        List<String> palindromes = new ArrayList<>();

        for (int i = 0; i < text.length; i++) {
            String word = text[i];
            StringBuilder left = new StringBuilder();
            StringBuilder right = new StringBuilder();
            int length = word.length();
            if (length % 2 == 0) {
                left.append(word.substring(0, length / 2));
                right.append(word.substring(length / 2));
            } else {
                left.append(word.substring(0, (length / 2) + 1));
                right.append(word.substring(length / 2));
            }
            right.reverse();
            if (left.toString().equals(right.toString()) && !palindromes.contains(word)) {
                palindromes.add(word);
            }
        }
        Collections.sort(palindromes, Collator.getInstance());
        System.out.println(palindromes);
    }
}
