import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem02_MatchPhoneNumber {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern01 = Pattern.compile("^(\\+359[ ]2[ ]\\d{3}[ ]\\d{4})$");
        Pattern pattern02 = Pattern.compile("^(\\+359[-]2[-]\\d{3}[-]\\d{4})$");
        Matcher matcher01;
        Matcher matcher02;
        String line = null;

        while (!(line = reader.readLine()).equals("end")) {
            matcher01 = pattern01.matcher(line);
            matcher02 = pattern02.matcher(line);
            if (matcher01.find()) {
                System.out.println(matcher01.group(1));
            } else if (matcher02.find()) {
                System.out.println(matcher02.group(1));
            }
        }
    }
}
