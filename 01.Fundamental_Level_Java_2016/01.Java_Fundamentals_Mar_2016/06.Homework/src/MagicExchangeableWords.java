import java.util.HashSet;
import java.util.Scanner;

public class MagicExchangeableWords {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        String[] words = console.nextLine().split(" ");

        if (words[0].length() != words[1].length()) {
            System.out.println("Strings are with different length.");
        } else {
            System.out.println(exchangeable(words[0], words[1]));
        }
    }
    
    static boolean exchangeable(String left, String right) {
        HashSet<Character> leftSet = new HashSet();
        HashSet<Character> rightSet = new HashSet<>();

        for (int i = 0; i < left.length(); i++) {
            leftSet.add(left.charAt(i));
            rightSet.add(right.charAt(i));
        }
        if (leftSet.size() == rightSet.size()) {
            return true;
        } else {
            return false;
        }
    }
}
