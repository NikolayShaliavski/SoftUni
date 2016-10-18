import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem01_MatchFullName {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        Pattern pattern = Pattern.compile("([A-Z][a-z]+ [A-Z][a-z]+)");
        Matcher matcher;
        while (!(line = reader.readLine()).equals("end")) {
            matcher = pattern.matcher(line);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
            }
        }
    }
}
