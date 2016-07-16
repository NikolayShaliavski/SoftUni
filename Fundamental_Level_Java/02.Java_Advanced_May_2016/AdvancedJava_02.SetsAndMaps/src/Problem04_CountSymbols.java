import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Problem04_CountSymbols {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        TreeMap<Character, Integer> symbols = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (!symbols.containsKey(currentChar)) {
                symbols.put(currentChar, 1);
            } else {
                symbols.put(currentChar, symbols.get(currentChar) + 1);
            }
        }
        for (Map.Entry<Character, Integer> character : symbols.entrySet()) {
            System.out.printf("%s: %d time/s%n", character.getKey(), character.getValue());
        }
    }
}
