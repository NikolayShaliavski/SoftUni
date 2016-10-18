import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem04_ReplaceATag {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();
        String line = null;

        while (!(line = reader.readLine()).equals("end")) {
            builder.append(line);
        }

        String input = builder.toString();
        input = input.replaceAll("<a", "[URL");
        input = input.replaceAll("</a>", "[/URL]");
        System.out.println(input);
    }
}
