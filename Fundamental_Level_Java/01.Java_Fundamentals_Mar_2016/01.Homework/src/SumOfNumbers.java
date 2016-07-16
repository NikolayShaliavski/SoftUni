import java.util.Scanner;

public class SumOfNumbers {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int number = input.nextInt();
        long sum = 0;

        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        System.out.println(sum);
        input.close();
    }
}
