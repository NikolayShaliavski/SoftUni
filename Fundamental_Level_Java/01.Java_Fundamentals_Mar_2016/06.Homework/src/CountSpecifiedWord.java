import java.util.Scanner;

public class CountSpecifiedWord {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String[] text = console.nextLine().split("[\\W]+");
        String word = console.nextLine();
        int counter = 0;

        for (int i = 0; i < text.length; i++) {
            if (text[i].equalsIgnoreCase(word)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
