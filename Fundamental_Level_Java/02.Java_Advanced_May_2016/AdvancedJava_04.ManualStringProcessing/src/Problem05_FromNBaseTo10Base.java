import java.math.BigInteger;
import java.util.Scanner;

public class Problem05_FromNBaseTo10Base {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int base = scan.nextInt();
        BigInteger number = scan.nextBigInteger();

        String numberToString = number.toString();
        BigInteger result = new BigInteger("0");
        BigInteger pow = new BigInteger("1");

        for (int i = numberToString.length() - 1; i >= 0; i--) {
            int current = Integer.parseInt(numberToString.charAt(i) +"");
            result = result.add(pow.multiply(BigInteger.valueOf(current)));
            pow = pow.multiply(BigInteger.valueOf(base));
        }
        System.out.println(result);
    }
}
