import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem05_ExtractEmails {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String text = console.nextLine();

        Pattern pattern = Pattern.compile("\\s+([A-Za-z0-9][\\w\\.-]*?[A-Za-z0-9]\\b)@(\\b[A-Za-z][A-Za-z-.]*[A-Za-z]\\b)\\.(\\b[A-Za-z]{2,}\\b)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
