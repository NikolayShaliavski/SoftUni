import java.util.Scanner;

public class FromBaseSevenToDecimal {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String numerInBaseSeven = input.nextLine();

        int numberInDecimal = Integer.parseInt(numerInBaseSeven, 7);
        System.out.println(numberInDecimal);
    }
}
