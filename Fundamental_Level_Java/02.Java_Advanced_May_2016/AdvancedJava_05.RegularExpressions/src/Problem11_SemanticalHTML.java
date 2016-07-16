import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem11_SemanticalHTML {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        Pattern patternOpen = Pattern.compile("(\\s*)<div(.*)\\s*(id|class)\\s*=\\s*\\\"(header|nav|main|article|section|aside|footer)\\\"\\s*(.*)");
        Pattern patternClose = Pattern.compile("(\\s*)<\\/div>.*<!--\\s*(header|nav|main|article|section|aside|footer)\\s*-->");
        Matcher matcher;
        List<String> values = new ArrayList<>();
        while (!line.equals("END")) {
            matcher = patternOpen.matcher(line);
            if (matcher.find()) {
                StringBuilder builder = new StringBuilder();
                String startOfLine = "";
                if (matcher.group(1) != null) {
                   startOfLine  = matcher.group(1);
                }
                String text = matcher.group(2);
                String value = matcher.group(4);
                String end = matcher.group(5).substring(0, matcher.group(5).length() - 1).replaceAll("[\\s]+", " ");
                text = text.replaceAll("[\\s]+", " ");
                builder.append("<").append(value).append(text).append(end);
                String toTrim = builder.toString().trim();
                builder.delete(0, builder.length());
                builder.append(startOfLine).append(toTrim).append(">");
                values.add(value);
                System.out.println(builder.toString());
            } else {
                matcher = patternClose.matcher(line);
                if (matcher.find() && values.contains(matcher.group(2))) {
                    StringBuilder builder = new StringBuilder();
                    String startOfLine = matcher.group(1);
                    builder.append(startOfLine).append("</").append(matcher.group(2)).append(">");
                    System.out.println(builder.toString());
                } else {
                    System.out.println(line);
                }
            }
            line = reader.readLine();
        }
    }
}
