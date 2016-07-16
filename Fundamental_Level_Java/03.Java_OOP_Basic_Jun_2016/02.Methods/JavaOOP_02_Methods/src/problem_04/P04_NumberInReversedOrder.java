package problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P04_NumberInReversedOrder {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String num = reader.readLine();
        DecimalNumber number = new DecimalNumber(num);
        System.out.println(number.reverseNumber());
    }

}
class DecimalNumber {
    private String number;

    public DecimalNumber(String number) {
        this.number = number;
    }

    public String reverseNumber() {
        StringBuilder builder = new StringBuilder();
        for (int i = this.number.length() - 1; i >= 0 ; i--) {
            builder.append(this.number.charAt(i));
        }
        return builder.toString();
    }
}
