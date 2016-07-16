import java.util.Scanner;

public class CountSpecifiedWord {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] text = scn.nextLine().split("[^\\w\\d]+");
        String word = scn.nextLine();
        int countWords = 0;

        for (int i = 0; i < text.length; i++) {
            if (text[i].equalsIgnoreCase(word)) {
                countWords++;
            }
        }
        System.out.println(countWords);
    }
}
