import java.util.Scanner;

public class SumCards {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] hand = scn.nextLine().split("[SHDC ]+");

        int strength = 0;
        int previous = 0;
        int count = 1;
        for (int i = 0; i < hand.length; i++) {
            int value = 0;
            switch (hand[i]) {
                case "J":
                    value = 12;
                    break;
                case "Q":
                    value = 13;
                    break;
                case "K":
                    value = 14;
                    break;
                case "A":
                    value = 15;
                    break;
                default:
                    value = Integer.parseInt(hand[i]);
                    break;
            }
            if (value == previous) {
                count++;
            } else {
                count = 1;
            }
            if (count == 2) {
                strength += value * 2;
            }
            if (count > 2) {
                strength += value;
            }
            previous = value;
            strength += value;
        }
        System.out.println(strength);
    }
}
