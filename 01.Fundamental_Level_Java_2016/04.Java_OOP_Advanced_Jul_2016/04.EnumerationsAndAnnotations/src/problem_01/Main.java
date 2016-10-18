package problem_01;

public class Main {

    public static void main(String[] args) {

        System.out.println("Card Suits:");
        for (int i = 0; i < CardSuit.values().length; i++) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    CardSuit.values()[i].ordinal(),
                    CardSuit.values()[i]);
        }
    }
}
