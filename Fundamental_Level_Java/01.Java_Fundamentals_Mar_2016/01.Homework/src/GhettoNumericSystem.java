import java.util.Scanner;
import java.util.TreeMap;

public class GhettoNumericSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String userNum = Integer.toString(input.nextInt());

        TreeMap<Character, String> numbers = new TreeMap<>(); //solution with TreeMap<Key, Value>

        numbers.put('0', "Gee");
        numbers.put('1', "Bro");
        numbers.put('2', "Zuz");
        numbers.put('3', "Ma");
        numbers.put('4', "Duh");
        numbers.put('5', "Yo");
        numbers.put('6', "Dis");
        numbers.put('7', "Hood");
        numbers.put('8', "Jam");
        numbers.put('9', "Mack");

        for (int i = 0; i < userNum.length(); i++) {

            System.out.print(numbers.get(userNum.charAt(i)));
        }
        System.out.println();
    }
}