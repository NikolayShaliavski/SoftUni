package problem_08;

import problem_08.contracts.Card;
import problem_08.contracts.CardDeck;
import problem_08.contracts.Player;
import problem_08.enums.Rank;
import problem_08.enums.Suit;
import problem_08.models.CardDeckImpl;
import problem_08.models.CardImpl;
import problem_08.models.PlayerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) throws IOException {

        CardDeck cardDeck = new CardDeckImpl();
        initCarDeck(cardDeck);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstPlayerName = reader.readLine();
        String secondPlayerName = reader.readLine();

        Player firstPlayer = new PlayerImpl(firstPlayerName);
        Player secondPlayer = new PlayerImpl(secondPlayerName);

        while (firstPlayer.canReceiveCard()) {
            playersReceiveCards(firstPlayer, cardDeck, reader);
        }

        while (secondPlayer.canReceiveCard()) {
            playersReceiveCards(secondPlayer, cardDeck, reader);
        }

        String winner = findWinner(firstPlayer, secondPlayer);
        System.out.println(winner);
    }

    private static String findWinner(Player firstPlayer, Player secondPlayer) {

        Card firstPlayerStrongestCard = firstPlayer.showStrongestCard();
        Card secondPlayerStrongestCard = secondPlayer.showStrongestCard();

        if (firstPlayerStrongestCard.compareTo(secondPlayerStrongestCard) > 0) {
            return String.format("%s wins with %s.",
                    firstPlayer.getName(),
                    firstPlayerStrongestCard.toString());
        } else {
            return String.format("%s wins with %s.",
                    secondPlayer.getName(),
                    secondPlayerStrongestCard.toString());
        }
    }

    private static void playersReceiveCards(Player player, CardDeck cardDeck, BufferedReader reader) throws IOException {
        String[] cardParams = reader.readLine().split("[\\s]+");
        try {
            Card card = tryCreateCard(cardParams);
            player.receiveCard(cardDeck.tryPullCard(card));
        } catch (IllegalArgumentException iaex) {
            System.out.println("No such card exists.");
        } catch (NoSuchElementException nsex) {
            System.out.println(nsex.getMessage());
        }
    }

    private static Card tryCreateCard(String[] cardParams) {
        String cardRank = cardParams[0];
        String cardSuit = cardParams[2];

        Rank rank = Rank.valueOf(cardRank);
        Suit suit = Suit.valueOf(cardSuit);

        return new CardImpl(rank, suit);
    }

    private static void initCarDeck(CardDeck cardDeck) {

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new CardImpl(rank, suit);
                cardDeck.addCard(card);
            }
        }
    }
}
