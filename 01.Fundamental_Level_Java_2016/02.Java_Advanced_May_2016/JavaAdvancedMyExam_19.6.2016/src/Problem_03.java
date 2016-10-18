import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_03 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message = reader.readLine();
        Matcher matcher;
        while (!message.equals("Over!")) {
            int length = Integer.valueOf(reader.readLine());
            Pattern pattern = Pattern.compile(String.format("^(\\d+)([a-zA-Z]{%d})([^a-zA-Z]*)$", length));
            matcher = pattern.matcher(message);
            if (matcher.find()) {
                StringBuilder builder = new StringBuilder();
                String text = matcher.group(2);
                String indexes = matcher.group(1) + matcher.group(3);
                for (int i = 0; i < indexes.length(); i++) {
                    if (Character.isDigit(indexes.charAt(i))) {
                        int index = Integer.valueOf(indexes.charAt(i) + "");
                        boolean validIndex = checkIndex(index, text);
                        if (validIndex) {
                            builder.append(text.charAt(index));
                        } else {
                            builder.append(" ");
                        }
                    }
                }
                System.out.printf("%s == %s%n", text, builder.toString());
            }
            message = reader.readLine();
        }
    }

    private static boolean checkIndex(int index, String text) {
        if (index >= 0 && index < text.length()) {
            return true;
        }
        return false;
    }
}
