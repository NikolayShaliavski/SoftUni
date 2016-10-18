import java.util.Scanner;

public class GetAverage {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();

        double result = getAverage(a, b, c);
        System.out.println(String.format("%.2f", result));
    }

    private static double getAverage (double a, double b, double c ){

        double average = (a + b + c) / 3;
        return average;
    }
}
