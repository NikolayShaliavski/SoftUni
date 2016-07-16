import java.util.Scanner;

public class CountSubstringOccurences {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String text = console.nextLine().toLowerCase();
        String toFind = console.nextLine().toLowerCase();

        int counter = 0;
        int hasFind = 0;
        while ((hasFind = text.indexOf(toFind, hasFind)) != -1) {
            counter++;
            hasFind++;
        }
        System.out.println(counter);
    }
}
