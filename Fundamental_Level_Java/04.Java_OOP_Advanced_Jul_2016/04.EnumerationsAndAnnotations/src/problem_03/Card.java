package problem_03;

import problem_03.enums.CardRanks;
import problem_03.enums.CardSuits;

public class Card {

    private CardRanks cardRank;
    private CardSuits cardSuit;
    private int cardPower;

    public Card(CardRanks cardRank,
                CardSuits cardSuit) {
        this.setCardRank(cardRank);
        this.setCardSuit(cardSuit);
        this.setCardPower();
    }

    private void setCardRank(CardRanks cardRank) {
        this.cardRank = cardRank;
    }

    private void setCardSuit(CardSuits cardSuit) {
        this.cardSuit = cardSuit;
    }

    private void setCardPower() {
        this.cardPower = this.cardRank.getRankPower() +
                this.cardSuit.getCardPower();
    }

    @Override
    public String toString() {
        return String.format(
                "Card name: %s of %s; Card power: %d",
                this.cardRank.name(),
                this.cardSuit.name(),
                this.cardPower);
    }
}
