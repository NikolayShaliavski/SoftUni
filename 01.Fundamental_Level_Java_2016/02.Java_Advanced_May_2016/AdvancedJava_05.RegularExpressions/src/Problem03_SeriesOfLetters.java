import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem03_SeriesOfLetters {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        StringBuilder builder = new StringBuilder();
        char letter = line.charAt(0);
        builder.append(letter);

        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) != letter) {
                letter = line.charAt(i);
                builder.append(letter);
            }
        }
        System.out.println(builder.toString());
    }

}
