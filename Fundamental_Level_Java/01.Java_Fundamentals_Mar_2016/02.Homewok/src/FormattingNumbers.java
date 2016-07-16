import java.util.Locale;
import java.util.Scanner;

public class FormattingNumbers {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        scn.useLocale(Locale.ROOT);

        int firstNum = scn.nextInt();
        double secondNum = scn.nextDouble();
        double thirdNum = scn.nextDouble();
        String binary = Integer.toBinaryString(firstNum);

        System.out.printf("|%-10S|%010d|%10.2f|%-10.3f|", Integer.toHexString(firstNum), Integer.parseInt(binary), secondNum, thirdNum);
    }
}
