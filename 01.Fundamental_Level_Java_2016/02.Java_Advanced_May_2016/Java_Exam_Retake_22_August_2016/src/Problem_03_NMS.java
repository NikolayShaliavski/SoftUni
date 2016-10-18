import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem_03_NMS {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        String line = reader.readLine();
        while (!line.equals("---NMS SEND---")) {
            builder.append(line);
            line = reader.readLine();
        }
        String input = builder.toString();
        builder.delete(0, builder.length());

        for (int i = 0; i < input.length(); i++) {
            builder.append(input.charAt(i));
            while (i < input.length() - 1 &&
                    Character.toLowerCase(input.charAt(i)) <= Character.toLowerCase(input.charAt(i + 1))) {
                builder.append(input.charAt(i + 1));
                i++;
            }
            words.add(builder.toString());
            builder.delete(0, builder.length());
        }
        String delimeter = reader.readLine();

        for (String word : words) {
            builder.append(word).append(delimeter);
        }
        int lastIndexOfDelimeter = builder.lastIndexOf(delimeter);
        builder.delete(lastIndexOfDelimeter, builder.length());
        System.out.println(builder);
    }
}
