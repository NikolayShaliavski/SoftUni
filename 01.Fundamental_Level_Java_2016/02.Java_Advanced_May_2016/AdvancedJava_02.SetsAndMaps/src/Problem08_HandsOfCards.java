import java.util.*;

public class Problem08_HandsOfCards {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        LinkedHashMap<String, HashSet<String>> allHands = new LinkedHashMap<>();

        while (!line.equals("JOKER")) {

            String[] personHand = line.split(":");
            String person = personHand[0];
            String[] hand = personHand[1].trim().split("[\\s,]+");
            if (!allHands.containsKey(person)) {
                allHands.put(person, new HashSet<>());
            }
            for (String card : hand) {
                allHands.get(person).add(card);
            }

            line = scan.nextLine();
        }

        for (Map.Entry<String, HashSet<String>> personHand : allHands.entrySet()) {
            int sumOfCards = sumHand(personHand.getValue());
            System.out.printf("%s: %d%n", personHand.getKey(), sumOfCards);
        }
    }

    private static Integer sumHand(HashSet<String> currentHand) {
        int sum = 0;
        ArrayList<String> faces = new ArrayList(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));
        ArrayList<Character> suits = new ArrayList(Arrays.asList('C', 'D', 'H', 'S'));

        for (String card : currentHand) {
            String face = card.substring(0, card.length() - 1);
            char suit = card.charAt(card.length() - 1);
            int faceStrength = faces.indexOf(face) + 2;
            int suitStrength = suits.indexOf(suit) + 1;
            sum += (faceStrength * suitStrength);
        }
        return sum;
    }
}
