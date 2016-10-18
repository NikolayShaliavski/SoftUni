import java.util.Scanner;

public class Problem06_CountStringOccurrences {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        String word = scan.nextLine();

        int endOfIteration = word.length() - 1;
        int counter = 0;
        for (int i = 0; i < text.length() - endOfIteration; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = i; j < word.length() + i; j++) {
                builder.append(text.charAt(j));
            }
            if (builder.toString().equalsIgnoreCase(word)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
