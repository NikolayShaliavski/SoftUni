import java.util.Scanner;

public class CharacterMultiplier {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] inputLine = scn.nextLine().split(" ");
        String firstLine = inputLine[0];
        String secondLine = inputLine[1];

        int result = multiplier(firstLine, secondLine);

        System.out.println(result);
    }

    private static int multiplier(String first, String second) {
        int result = 0;
        int lengthMax = Math.max(first.length(), second.length());
        int lengthMin = Math.min(first.length(), second.length());

        for (int i = 0; i < lengthMax; i++) {
             if (i < lengthMin) {
                 result += (first.charAt(i) * second.charAt(i));
             }else if (first.length() > second.length()) {
                 result += first.charAt(i);
             }else if (second.length() > first.length()) {
                 result += second.charAt(i);
             }
        }
        return result;
    }
}
