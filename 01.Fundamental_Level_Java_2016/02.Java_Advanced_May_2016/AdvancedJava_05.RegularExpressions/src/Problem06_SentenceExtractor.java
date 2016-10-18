import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem06_SentenceExtractor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String word = reader.readLine();
        String line = reader.readLine();
        String[] text = line.split("[\\.|!|?]");
        String[] delimiters = line.split("[^\\.|!|?]+");

        Pattern pattern = Pattern.compile("[\\s+|^]" + word + "[\\s+|$]");
        Matcher matcher;

        for (int i = 0; i < text.length; i++) {
            matcher = pattern.matcher(text[i]);

            if (matcher.find()) {
                if (i < delimiters.length - 1) {
                    String delimiter = delimiters[i + 1];
                    System.out.println(text[i] + delimiter);
                }

            }
        }
    }
}
