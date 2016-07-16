import java.math.BigInteger;
import java.util.Scanner;

public class Problem04_FromBase10TobaseN {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int base = scan.nextInt();
        BigInteger number = scan.nextBigInteger();

        System.out.println(convertNumber(number, base));
    }

    private static String convertNumber(BigInteger number, int base) {
        StringBuilder builder = new StringBuilder();

        while (number.compareTo(BigInteger.valueOf(base)) > -1) {
            int reminder = number.remainder(BigInteger.valueOf(base)).intValue();
            builder.insert(0, reminder);
            number = number.divide(BigInteger.valueOf(base));
        }
        builder.insert(0 , number);
        return builder.toString();
    }
}
