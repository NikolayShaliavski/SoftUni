import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicMarkupLanguage {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int counter = 1;

        while (!line.replaceAll(" ", "").equals("<stop/>")) {
            Pattern pattern = Pattern.compile("(<[\\w\\s]+=)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String command = matcher.group().replace("<", "");
            command = command.replace("=", "");
            command = command.replaceAll("\\s", "").replaceAll("\\n", "");

            switch (command) {
                case "inversecontent":
                    pattern = Pattern.compile("(\".+\")");
                    matcher = pattern.matcher(line);
                    if (matcher.find()) {

                        char[] text = matcher.group().replaceAll("\"", "").toCharArray();
                        StringBuilder builder1 = new StringBuilder();

                        for (int i = 0; i < text.length; i++) {
                            if (Character.isLowerCase(text[i])) {
                                builder1.append(Character.toUpperCase(text[i]));
                            } else {
                                builder1.append(Character.toLowerCase(text[i]));
                            }
                        }
                        if (builder1.length() > 0) {
                            System.out.printf("%d. %s\n", counter, builder1.toString());
                            counter++;
                        }
                    }
                    break;
                case "reversecontent":
                    pattern = Pattern.compile("(\".+\")");
                    matcher = pattern.matcher(line);

                    if (matcher.find()) {
                        char[] text1 = matcher.group().replaceAll("\"", "").toCharArray();
                        StringBuilder builder2 = new StringBuilder();

                        for (int i = text1.length - 1; i >= 0; i--) {
                            builder2.append(text1[i]);
                        }
                        if (builder2.length() > 0) {
                            System.out.printf("%d. %s\n", counter, builder2.toString());
                            counter++;
                        }
                    }
                    break;
                case "repeatvalue":
                    pattern = Pattern.compile("(\"[\\d\\s]+\")|(\".+\")");
                    matcher = pattern.matcher(line);
                    matcher.find();
                    int repeats = Integer.parseInt(matcher.group().replaceAll("\"", "").replaceAll("\\s", ""));
                    if (matcher.find()) {
                        String toRepeat = matcher.group().replaceAll("\"", "");
                        if (toRepeat.length() > 0) {
                            for (int i = 0; i < repeats; i++) {
                                System.out.printf("%d. %s\n", counter, toRepeat);
                                counter++;
                            }
                        }
                    }
                    break;
            }
            line = scanner.nextLine();
        }
    }
}
