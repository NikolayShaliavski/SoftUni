import java.util.Scanner;

public class CalculateFactorial {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int number = scn.nextInt();

        long result = calculateFactorial(number);
        System.out.println(result);
    }

    private static long calculateFactorial(int number) {
        if (number <= 1) {
            return  1;
        }
        return number * calculateFactorial(number - 1);
    }
}
