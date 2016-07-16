import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TheHeiganDance {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        scn.useLocale(Locale.ROOT);

        double playerBlood = 18_500;
        double heiganBlood = 3_000_000;

        ArrayList<Integer> playerPosition = new ArrayList<>();
        playerPosition.add(7);
        playerPosition.add(7);

        double damage = scn.nextDouble();
        scn.nextLine();

        String spell = "";
        boolean notMoved = true;
        ArrayList<ArrayList<Integer>> position = new ArrayList<>();

        while (playerBlood > 0 && heiganBlood > 0) {

            if (spell.equals("Cloud") && notMoved && position.contains(playerPosition)) {
                playerBlood -= 3500;
            }
            position.clear();
            notMoved = true;
            heiganBlood -= damage;
            if (heiganBlood <= 0 || playerBlood <= 0) {
                break;
            }
            String[] line = scn.nextLine().split(" ");

            spell = line[0];
            int spellRow = Integer.parseInt(line[1]);
            int spellCol = Integer.parseInt(line[2]);

            for (int i = 0; i < 9; i++) {
                position.add(i, new ArrayList<>());
            }
            position.get(0).add(spellRow);
            position.get(0).add(spellCol);
            position.get(1).add(spellRow - 1);
            position.get(1).add(spellCol - 1);
            position.get(2).add(spellRow - 1);
            position.get(2).add(spellCol);
            position.get(3).add(spellRow - 1);
            position.get(3).add(spellCol + 1);
            position.get(4).add(spellRow);
            position.get(4).add(spellCol + 1);
            position.get(5).add(spellRow + 1);
            position.get(5).add(spellCol + 1);
            position.get(6).add(spellRow + 1);
            position.get(6).add(spellCol);
            position.get(7).add(spellRow + 1);
            position.get(7).add(spellCol - 1);
            position.get(8).add(spellRow);
            position.get(8).add(spellCol - 1);

            if (position.contains(playerPosition)) {

                ArrayList<Integer> up = new ArrayList<>();
                up.add(playerPosition.get(0) - 1);
                up.add(playerPosition.get(1));
                ArrayList<Integer> right = new ArrayList<>();
                right.add(playerPosition.get(0));
                right.add(playerPosition.get(1) + 1);
                ArrayList<Integer> down = new ArrayList<>();
                down.add(playerPosition.get(0) + 1);
                down.add(playerPosition.get(1));
                ArrayList<Integer> left = new ArrayList<>();
                left.add(playerPosition.get(0));
                left.add(playerPosition.get(1) - 1);

                if (!position.contains(up) && up.get(0) >= 0) {
                    playerPosition = up;
                    notMoved = false;
                } else if (!position.contains(right) && right.get(1) <= 14) {
                    playerPosition = right;
                    notMoved = false;
                } else if (!position.contains(down) && down.get(0) <= 14) {
                    playerPosition = down;
                    notMoved = false;
                } else if (!position.contains(left) && left.get(1) >= 0) {
                    playerPosition = left;
                    notMoved = false;
                }
            }

            if (notMoved && heiganBlood > 0 && position.contains(playerPosition)) {
                if (spell.equals("Cloud")) {
                    playerBlood -= 3500;
                } else {
                    playerBlood -= 6000;
                }
            }
        }

        if (spell.equals("Cloud")) {
            spell = "Plague Cloud";
        }
        if (heiganBlood > 0) {
            System.out.printf("Heigan: %.2f\n", heiganBlood);

        } else {
            System.out.println("Heigan: Defeated!");
        }
        if (playerBlood > 0) {
            System.out.printf("Player: %d\n", (int) playerBlood);
        } else {
            System.out.println("Player: Killed by " + spell);
        }
        System.out.printf("Final position: %d, %d\n", playerPosition.get(0), playerPosition.get(1));
    }
}
