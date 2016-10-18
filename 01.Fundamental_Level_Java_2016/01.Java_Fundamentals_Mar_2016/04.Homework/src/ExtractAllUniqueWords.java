
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ExtractAllUniqueWords {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] text = scn.nextLine().split("[^\\w\\d]+");
        ArrayList<String> uniqueWords = new ArrayList<>();

        for (int i = 0; i < text.length; i++) {
            if (!uniqueWords.contains(text[i].toLowerCase())) {
                uniqueWords.add(text[i].toLowerCase());
            }
        }
        Collections.sort(uniqueWords);
        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.print(uniqueWords.get(i) + " ");
        }
    }
}
