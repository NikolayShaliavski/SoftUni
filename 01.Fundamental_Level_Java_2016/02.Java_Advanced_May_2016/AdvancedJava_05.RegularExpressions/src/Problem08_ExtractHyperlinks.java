import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem08_ExtractHyperlinks {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();

        while (!line.equals("END")) {
            builder.append(line);
            line = reader.readLine();
        }

        Pattern pattern01 = Pattern.compile("(<a.*?>)");
        Pattern pattern02 = Pattern.compile("href\\s*=\\s*\\'(.*?)\\'");
        Pattern pattern03 = Pattern.compile("href\\s*=\\s*\\\"(.*?)\\\"");
        Pattern pattern04 = Pattern.compile("href\\s*=\\s*([^>\\s]+)\\s*");

        Matcher matcher = pattern01.matcher(builder);
        ArrayList<String> links = new ArrayList<>();
        while (matcher.find()) {
            links.add(matcher.group(1));
        }
        System.out.println();
        for (String link : links) {
            matcher = pattern02.matcher(link);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
                continue;
            }
            matcher = pattern03.matcher(link);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
                continue;
            }
            matcher = pattern04.matcher(link);
            if (matcher.find()) {
                String out = matcher.group(1).trim();
                System.out.println(out);
            }
        }
    }
}
