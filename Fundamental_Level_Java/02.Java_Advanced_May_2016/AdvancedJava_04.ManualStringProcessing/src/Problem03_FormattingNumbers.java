import java.util.Scanner;

public class Problem03_FormattingNumbers {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] numbers = scan.nextLine().split("\\s+");
        int first = Integer.parseInt(numbers[0]);
        double second = Double.parseDouble(numbers[1]);
        double third = Double.parseDouble(numbers[2]);

        String hex = Integer.toHexString(first).toUpperCase();
        String binary = Integer.toBinaryString(first);
        if (binary.length() < 10) {
            binary = String.format("%10s", binary);
            binary = binary.replace(' ', '0');
        } else {
            binary = binary.substring(0, 10);
        }
        System.out.printf("|%-10s|%s|%10.2f|%-10.3f|", hex, binary, second, third);
    }
}
