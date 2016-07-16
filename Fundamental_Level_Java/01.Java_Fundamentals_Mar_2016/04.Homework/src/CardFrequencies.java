import java.util.ArrayList;
import java.util.Scanner;

public class CardFrequencies {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] faces = scn.nextLine().split("[^\\w\\d]+");
        ArrayList<String> cards = new ArrayList<>();
        double numberOfCards = faces.length;
        int appearance = 1;

        for (int i = 0; i < faces.length; i++) {
            for (int j = i + 1; j < faces.length; j++) {
                if (faces[i].equals(faces[j])) {
                    appearance++;
                }
            }
            if (!cards.contains(faces[i])) {
                cards.add(faces[i]);
                double frequence = (appearance / numberOfCards) * 100;
                System.out.printf("%s -> %.2f%s\r\n", faces[i], frequence, "%");
            }
            appearance = 1;
        }
    }
}
