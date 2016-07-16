import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem08_MultiplyBigNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String number = reader.readLine();
        int multiplier = Integer.parseInt(reader.readLine());
        if (multiplier == 0) {
            System.out.println(0);
            return;
        }
        StringBuilder result = new StringBuilder();
        StringBuilder zeros = new StringBuilder();

        if (number.startsWith("0")) {
            zeros.append(number);
            while (zeros.charAt(0) == '0') {
                zeros.deleteCharAt(0);
            }
            number = zeros.toString();
        }
        int multiplication = 0;
        int reminder = 0;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(number.charAt(i) + "");
            multiplication = digit * multiplier + reminder;
            if (multiplication >= 10) {
                result.insert(0, multiplication % 10);
                reminder = multiplication / 10;
            } else {
                result.insert(0, multiplication);
                reminder = 0;
            }
        }
        if (reminder > 0) {
            result.insert(0, reminder);
        }
        System.out.println(result);
    }
}
