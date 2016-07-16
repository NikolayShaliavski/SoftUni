import java.util.Scanner;

public class FromDecimalToBaseSeven {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int numberInDecimal = input.nextInt();

        String numberInBaseSeven = Integer.toString(numberInDecimal, 7);
        System.out.println(numberInBaseSeven);
    }
}
