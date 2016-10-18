import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem16_ExtractHyperlinks {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        List<String> aTags = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        while (!line.equals("END")) {
            builder.append(line);
            line = reader.readLine();
        }
        int startIndex = builder.indexOf("<a");
        int lastIndex = builder.indexOf(">", startIndex);

        while (startIndex != -1 && lastIndex != -1) {
            String aTag = builder.substring(startIndex + 2, lastIndex);
            aTags.add(aTag);

            startIndex = builder.indexOf("<a", lastIndex);
            lastIndex = builder.indexOf(">", startIndex);
        }

        for (String aTag : aTags) {
            boolean hrefFound = false;
            int hrefIndex = aTag.indexOf("href");
            while (hrefIndex != -1) {
                hrefIndex += 4;
                int checker = hrefIndex;
                for (int a = hrefIndex; a < aTag.length(); a++) {
                    if (aTag.charAt(a) == ' ' || aTag.charAt(a) == '\t') {
                        continue;
                    } else if (aTag.charAt(a) == '=') {
                        hrefIndex = a + 1;
                        hrefFound = true;
                        break;
                    } else {
                        hrefFound = false;
                        break;
                    }
                }

                if (hrefFound) {
                    char delimiter = '@';
                    int start = 0;
                    boolean linkFound = false;
                    for (int a = hrefIndex; a < aTag.length(); a++) {
                        if (aTag.charAt(a) == ' ' || aTag.charAt(a) == '\t') {
                            continue;
                        } else if (aTag.charAt(a) == '\'') {
                            delimiter = '\'';
                            start = a + 1;
                            linkFound = true;
                            break;
                        } else if (aTag.charAt(a) == '\"') {
                            delimiter = '\"';
                            start = a + 1;
                            linkFound = true;
                            break;
                        } else {
                            delimiter = ' ';
                            start = a;
                            linkFound = true;
                            break;
                        }
                    }
                    StringBuilder link = new StringBuilder();
                    if (linkFound) {
                        for (int i = start; i < aTag.length(); i++) {
                            if (aTag.charAt(i) == delimiter) {
                                break;
                            } else {
                                link.append(aTag.charAt(i));
                            }
                        }
                        System.out.println(link.toString());
                        hrefIndex = -1;
                    }
                } else {
                    hrefIndex = aTag.indexOf("href", checker);
                }
            }

        }
    }
}

