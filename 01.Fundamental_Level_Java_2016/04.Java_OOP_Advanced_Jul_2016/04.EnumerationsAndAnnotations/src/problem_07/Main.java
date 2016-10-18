package problem_07;

import problem_07.enums.Rank;
import problem_07.enums.Suit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                System.out.printf("%s of %s%n", rank.name(), suit.name());
            }
        }
    }
}
