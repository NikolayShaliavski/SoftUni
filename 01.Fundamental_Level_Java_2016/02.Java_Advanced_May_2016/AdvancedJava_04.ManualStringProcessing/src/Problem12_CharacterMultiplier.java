import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem12_CharacterMultiplier {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        String first = line[0];
        String second = line[1];

        System.out.println(multiplyCharacters(first, second));
    }

    private static int multiplyCharacters(String first, String second) {
        int sum = 0;
        int shorter = 0;
        String remainedChars = null;
        if (first.length() > second.length()) {
            shorter = second.length();
            remainedChars = first.substring(shorter);
        } else {
            shorter = first.length();
            remainedChars = second.substring(shorter);
        }

        for (int i = 0; i < shorter; i++) {
             char firstChar = first.charAt(i);
             char secondChar = second.charAt(i);
            sum += (firstChar * secondChar);
        }
        for (int i = 0; i < remainedChars.length(); i++) {
             sum += remainedChars.charAt(i);
        }
        return sum;
    }
}
