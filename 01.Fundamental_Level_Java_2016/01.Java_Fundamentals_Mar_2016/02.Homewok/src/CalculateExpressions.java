import java.util.Scanner;
import java.util.Locale;

public class CalculateExpressions {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        scn.useLocale(Locale.ROOT);

        double first = scn.nextDouble();
        double second = scn.nextDouble();
        double third = scn.nextDouble();

        double formulae1 = ((Math.pow(first, 2) + Math.pow(second, 2)) / (Math.pow(first, 2) - Math.pow(second, 2)));
        double pow1 = (first + second + third) / Math.sqrt(third);

        double result1 = Math.pow(formulae1, pow1);

        double formulae2 = (Math.pow(first, 2) + Math.pow(second, 2) - Math.pow(third, 3));
        double pow2 = first - second;

        double result2 = Math.pow(formulae2, pow2);

        double average1 = (first + second + third) / 3;
        double average2 = (result1 + result2) / 2;
        double diff = Math.abs(average1 - average2);

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f", result1, result2, diff);
    }
}
