import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem10_UseYourChainsBuddy {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        Pattern pattern = Pattern.compile("<p>(.+?)<\\/p>");
        Matcher matcher = pattern.matcher(text);
        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            String partOfText = decryptText(matcher.group(1));
            builder.append(partOfText);
        }
        System.out.println(builder.toString());
    }

    private static String decryptText(String message) {
        StringBuilder result = new StringBuilder();
        message = message.replaceAll("[A-Z]", " ").replaceAll("\\W", " ");

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) >= 97 && message.charAt(i) <= 109) {
                result.append((char) (message.charAt(i) + 13));
            } else if (message.charAt(i) >= 110 && message.charAt(i) <= 122) {
                result.append((char) (message.charAt(i) - 13));
            } else {
                result.append(message.charAt(i));
            }
        }
        String decrypted = result.toString().replaceAll("[\\s]+", " ");
        return decrypted;
    }
}
