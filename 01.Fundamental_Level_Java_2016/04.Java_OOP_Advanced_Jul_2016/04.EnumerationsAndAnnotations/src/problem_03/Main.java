package problem_03;

import problem_03.enums.CardRanks;
import problem_03.enums.CardSuits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String rank = reader.readLine();
        String suit = reader.readLine();
        CardRanks cardRank = CardRanks.valueOf(rank);
        CardSuits cardSuit = CardSuits.valueOf(suit);

        Card firstCard = new Card(cardRank, cardSuit);

        rank = reader.readLine();
        suit = reader.readLine();
        cardRank = CardRanks.valueOf(rank);
        cardSuit = CardSuits.valueOf(suit);

        Card secondCard = new Card(cardRank, cardSuit);
        int resultOfComparing = firstCard.compareTo(secondCard);

        if (resultOfComparing < 0) {
            System.out.println(secondCard);
        } else {
            System.out.println(firstCard);
        }
    }
}
