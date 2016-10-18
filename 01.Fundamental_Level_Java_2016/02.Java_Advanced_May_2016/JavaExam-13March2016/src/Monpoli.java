import java.util.*;

public class Monpoli {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] dimention = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimention[0]);
        String[] lines = new String[rows];

        for (int i = 0; i < rows; i++) {
            lines[i] = scanner.nextLine();
        }

        int money = 50;
        int turns = 0;
        int hotels = 0;

        for (int i = 0; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            List<Character> row = new ArrayList<>();
            for (char character : line) {
                row.add(character);
            }
            if (i % 2 == 0) {

                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).equals('H')) {
                        hotels++;
                        turns++;
                        System.out.printf("Bought a hotel for %d. Total hotels: %d.\r\n", money, hotels);
                        money = 0;
                        money += (hotels * 10);
                    } else if (row.get(j).equals('J')) {
                        money += (hotels * 10) * 3;
                        System.out.printf("Gone to jail at turn %d.\r\n", turns);
                        turns += 3;
                    } else if (row.get(j).equals('S')) {
                        money += (hotels * 10);
                        int spent = (i + 1) * (j + 1);
                        if (money - spent >= 0) {
                            money -= spent;
                            System.out.printf("Spent %d money at the shop.\r\n", spent);
                        } else {
                            System.out.printf("Spent %d money at the shop.\r\n", money);
                            money = 0;
                        }
                        turns++;
                    } else if (row.get(j).equals('F')) {
                        money += (hotels * 10);
                        turns++;
                    }
                }
            } else {
                for (int j = row.size() - 1; j >= 0; j--) {
                    if (row.get(j).equals('H')) {
                        hotels++;
                        turns++;
                        System.out.printf("Bought a hotel for %d. Total hotels: %d.\r\n", money, hotels);
                        money = 0;
                        money += (hotels * 10);
                    } else if (row.get(j).equals('J')) {
                        money += (hotels * 10) * 3;
                        System.out.printf("Gone to jail at turn %d.\r\n", turns);
                        turns += 3;
                    } else if (row.get(j).equals('S')) {
                        money += (hotels * 10);
                        int spent = (i + 1) * (j + 1);
                        if (money - spent >= 0) {
                            money -= spent;
                            System.out.printf("Spent %d money at the shop.\r\n", spent);
                        } else {
                            System.out.printf("Spent %d money at the shop.\r\n", money);
                            money = 0;
                        }
                        turns++;
                    } else if (row.get(j).equals('F')) {
                        money += (hotels * 10);
                        turns++;
                    }
                }
            }
        }
        System.out.println("Turns " + turns);
        System.out.printf("Money %d\r\n", money);
    }
}
