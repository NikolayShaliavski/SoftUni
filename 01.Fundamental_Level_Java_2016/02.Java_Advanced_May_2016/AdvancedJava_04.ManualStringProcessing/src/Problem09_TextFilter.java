import java.util.Scanner;

public class Problem09_TextFilter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] banned = scan.nextLine().split(", ");
        String text = scan.nextLine();

        for (int i = 0; i < banned.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < banned[i].length(); j++) {
                builder.append('*');
            }
            text = text.replace(banned[i], builder.toString());
        }
        System.out.println(text);
    }
}
