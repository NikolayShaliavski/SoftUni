import java.io.IOException;
import java.util.Scanner;

public class Problem02_StringLength {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        int length = line.length();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            if (i < length) {
                builder.append(line.charAt(i));
            } else {
                builder.append('*');
            }
        }
        System.out.println(builder.toString());
    }
}
