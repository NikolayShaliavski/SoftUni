import java.util.Scanner;

public class GhettoNumeralSystem02 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String num = input.nextLine();

        String[] numeral = new String[] {
             "Gee", "Bro", "Zuz",
             "Ma", "Duh", "Yo", "Dis",
             "Hood", "Jam", "Mack"
        };

        //num.charAt(i) -> returns ASCII code of current digit
        //ASCII code of digit - ASCII code of zero -> returns number
        for (int i = 0; i < num.length(); i++) {

            int digit = num.charAt(i) - '0';

            System.out.print(numeral[digit]);
        }
        System.out.println();
    }
}
