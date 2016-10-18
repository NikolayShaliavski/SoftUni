import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WeirdScript {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int letter = scn.nextInt() - 1;

        if ((letter / 26) % 2 == 0) {
            letter = (letter % 26) + 97;
        }else {
            letter = (letter % 26) + 65;
        }
        char character = (char)letter;
        String key = "" + character + character;

        StringBuilder builder = new StringBuilder();
        String line = scn.nextLine();

        while (!line.toLowerCase().equals("end")) {
            for (int i = 0; i < line.length(); i++) {
                builder.append(line.charAt(i));
            }
            line = scn.nextLine();
        }

        String message = builder.toString();

        String regex = key + "(.*?)" + key;
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(message);

        while (m.find()) {
            System.out.println(m.group(1));
        }
    }
}
