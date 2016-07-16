import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem07_ValidUsernames {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] usernames = reader.readLine().split("[\\s\\\\/()]+");
        List<String> names = new LinkedList<>();
        names.addAll(Arrays.asList(usernames));
        ArrayList<String> collection = new ArrayList<String>(Arrays.asList(""));
        names.removeAll(collection);

        Pattern pattern = Pattern.compile("^([A-Za-z][A-Za-z0-9_]{2,24})$");
        Matcher matcher01;
        Matcher matcher02;
        int length = 0;
        String leftUser = "";
        String rightUser = "";
        for (int i = 0; i < names.size() - 1; i++) {
            matcher01 = pattern.matcher(names.get(i));
            if (matcher01.find()) {
                for (int j = i + 1; j < names.size(); j++) {
                    matcher02 = pattern.matcher(names.get(j));

                    if (matcher02.find()) {
                        int tempLength = names.get(i).length() + names.get(j).length();
                        if (tempLength > length) {
                            length = tempLength;
                            leftUser = names.get(i);
                            rightUser = names.get(j);
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(leftUser);
        System.out.println(rightUser);
    }
}
