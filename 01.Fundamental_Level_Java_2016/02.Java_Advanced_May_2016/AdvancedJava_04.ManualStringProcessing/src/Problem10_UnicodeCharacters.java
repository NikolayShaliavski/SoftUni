import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem10_UnicodeCharacters {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        for (int i = 0; i < line.length(); i++) {
            Character currentChar = line.charAt(i);
            String unicode = "\\u00" + Integer.toHexString(currentChar);
            System.out.print(unicode);
        }
        System.out.println();
    }
}
