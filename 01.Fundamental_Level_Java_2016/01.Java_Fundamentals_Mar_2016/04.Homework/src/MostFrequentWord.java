import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MostFrequentWord {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] text = scn.nextLine().split("[^\\w\\d]+");
        ArrayList<String> words = new ArrayList<>();
        int count = 1;
        int maxCount = 0;

        for (int i = 0; i < text.length; i++) {
            for (int j = i + 1; j < text.length; j++) {
                if (text[i].equalsIgnoreCase(text[j])) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                words.clear();
                words.add(text[i].toLowerCase());
            } else if (count == maxCount) {
                words.add(text[i].toLowerCase());
            }
            count = 1;
        }
        Collections.sort(words);
        for (int i = 0; i < words.size(); i++) {
            System.out.printf("%s -> %d times\r\n", words.get(i), maxCount);
        }
    }
}
