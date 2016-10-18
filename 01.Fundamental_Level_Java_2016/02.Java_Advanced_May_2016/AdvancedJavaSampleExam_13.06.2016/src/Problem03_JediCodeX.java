import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem03_JediCodeX {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < lines; i++) {
            builder.append(reader.readLine());
        }

        String pattern1 = reader.readLine();
        int length01 = pattern1.length();
        String pattern2 = reader.readLine();
        int length02 = pattern2.length();

        String[] nums = reader.readLine().split("[\\s]+");
        Deque<Integer> indexes = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            indexes.add(Integer.valueOf(nums[i]));
        }

        Pattern pattern01 = Pattern.compile(String.format("%s([a-zA-Z]{%d})(?=[^A-Za-z])", pattern1, length01));
        Pattern pattern02 = Pattern.compile(String.format("%s([a-zA-Z0-9]{%d})(?=[^A-Za-z0-9])", pattern2, length02));
        Matcher matcher01 = pattern01.matcher(builder.toString());
        Matcher matcher02 = pattern02.matcher(builder.toString());

        List<String> jedi = new ArrayList<>();
        List<String> messages = new ArrayList<>();

        while (matcher01.find()) {
            jedi.add(matcher01.group(1));
        }

        while (matcher02.find()) {
            messages.add(matcher02.group(1));
        }

        for (int i = 0; i < jedi.size(); i++) {
            if (indexes.size() <= 0) {
                break;
            }
            int indexToPrint = indexes.removeFirst();
            while (indexToPrint > messages.size() && indexes.size() > 0) {
                indexToPrint = indexes.removeFirst();
            }
            if (indexToPrint <= messages.size()) {
                System.out.printf("%s - %s%n", jedi.get(i), messages.get(indexToPrint - 1));
            }
        }
    }
}

