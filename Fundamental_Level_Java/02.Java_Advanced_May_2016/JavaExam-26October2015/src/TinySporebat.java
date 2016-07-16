import java.util.Scanner;

public class TinySporebat {

    public static final int PRICE = 30;
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String line = scn.nextLine();

        int health = 5800;
        int money = 0;
        int glowcaps = 0;

        while (!line.equals("Sporeggar")) {
            glowcaps = line.charAt(line.length() - 1) - 48;

            for (int i = 0; i < line.length() - 1; i++) {
                if (line.charAt(i) == 'L') {
                    health += 200;
                }else  {
                    health -= line.charAt(i);
                }
            }

            if (health > 0) {
                money += glowcaps;
            }
            line = scn.nextLine();
        }

        if (health > 0 && money < PRICE) {
            System.out.printf("Health left: %d\n", health);
            System.out.printf("Safe in Sporeggar, but another %d Glowcaps needed.\n", PRICE - money);
        }else if (health > 0 && money >= PRICE) {
            System.out.printf("Health left: %d\n", health);
            System.out.printf("Bought the sporebat. Glowcaps left: %d\n", money - PRICE);
        }else {
            System.out.printf("Died. Glowcaps: %d", money
            );
        }
    }
}
