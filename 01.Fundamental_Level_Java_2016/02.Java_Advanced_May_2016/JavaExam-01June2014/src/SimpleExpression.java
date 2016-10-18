import java.math.BigDecimal;
import java.util.Scanner;

public class SimpleExpression {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String expression = scn.nextLine();

        String[] signs = expression.trim().split("[\\s.\\d]+");
        String[] digits = expression.trim().split("[ +-]+");

        BigDecimal sum = new BigDecimal(0);
        sum = sum.add(new BigDecimal(Double.parseDouble(digits[0])));

        for (int i = 1; i < digits.length; i++) {
            BigDecimal current = new BigDecimal(Double.parseDouble(digits[i]));

            String sign = signs[i];

            if (sign.equals("+")) {
                sum = sum.add(current);
            } else {
                sum = sum.subtract(current);
            }
        }
        System.out.printf("%.10f", sum);
    }
}
