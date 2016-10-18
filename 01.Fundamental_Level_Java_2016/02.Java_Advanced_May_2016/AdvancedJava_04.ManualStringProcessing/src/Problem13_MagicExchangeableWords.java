import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Problem13_MagicExchangeableWords {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] words = reader.readLine().split(" ");
        String first = words[0];
        String second = words[1];
        HashMap<Character, Character> mapped = new HashMap<>();
        boolean exchangeable = true;
        String remained = null;
        int length = 0;
        if (first.length() > second.length()) {
            length = second.length();
            remained = first.substring(length);
        } else if (second.length() >= first.length()) {
            length = first.length();
            remained = second.substring(length);
        }

        for (int i = 0; i < length; i++) {
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);
            if (!mapped.containsKey(firstChar) && !mapped.containsValue(secondChar)) {
                mapped.put(firstChar, secondChar);
                continue;
            }
            if (mapped.containsKey(firstChar) || mapped.containsValue(secondChar)) {
                if (!mapped.containsKey(firstChar)) {
                    exchangeable = false;
                    continue;
                }
                if (mapped.get(firstChar) != secondChar) {
                    exchangeable = false;
                }
            }
        }
        for (int i = 0; i < remained.length(); i++) {
            char remindedChar = remained.charAt(i);
            if (mapped.containsKey(remindedChar) || mapped.containsValue(remindedChar)) {
                continue;
            } else {
                exchangeable = false;
            }
        }
        System.out.println(exchangeable);
    }
}
