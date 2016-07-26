package problem_08.models;

import problem_08.contracts.Card;
import problem_08.contracts.CardDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CardDeckImpl implements CardDeck {

    private List<Card> cardDeck;

    public CardDeckImpl() {
        this.cardDeck = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        this.cardDeck.add(card);
    }

    @Override
    public Card tryPullCard(Card card) {
        Card cardToPull = null;

        for (Card currentCard : this.cardDeck) {
            if (currentCard.equals(card)) {
                cardToPull = currentCard;
                break;
            }
        }
        if (cardToPull != null) {
            this.cardDeck.remove(cardToPull);
            return cardToPull;
        } else {
            throw new NoSuchElementException("Card is not in the deck.");
        }
    }
}
