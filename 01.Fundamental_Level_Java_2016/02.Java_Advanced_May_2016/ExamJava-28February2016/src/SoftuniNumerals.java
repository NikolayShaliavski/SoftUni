import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftuniNumerals {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String softuniNumber = scan.nextLine();

        HashMap<String, String> converter = new HashMap<>();
        converter.put("aa", "0");
        converter.put("aba", "1");
        converter.put("bcc", "2");
        converter.put("cc", "3");
        converter.put("cdc", "4");

        Pattern pattern = Pattern.compile("(aa|aba|bcc|cc|cdc)");
        Matcher matcher = pattern.matcher(softuniNumber);
        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            builder.append(converter.get(matcher.group()));
        }
        String result = builder.toString();
        System.out.println(new BigInteger(result, 5));
    }
}
