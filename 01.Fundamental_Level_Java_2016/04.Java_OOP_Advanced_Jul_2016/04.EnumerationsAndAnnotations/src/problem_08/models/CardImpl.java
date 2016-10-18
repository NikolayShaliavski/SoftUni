package problem_08.models;

import problem_08.contracts.Card;
import problem_08.enums.Rank;
import problem_08.enums.Suit;

public class CardImpl implements Card {

    private Rank rank;
    private Suit suit;
    private int power;

    public CardImpl(Rank rank,
                    Suit suit) {
        this.setRank(rank);
        this.setSuit(suit);
    }

    private void setSuit(Suit suit) {
        this.suit = suit;
    }

    private void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public int getPower() {
        return this.rank.getRankPower() + this.suit.getSuitPower();
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.getPower(), otherCard.getPower());
    }

    @Override
    public boolean equals(Object card) {
        Card cardToCompare = (Card) card;
        return this.getPower() == cardToCompare.getPower();
    }

    @Override
    public String toString() {
        return String.format("%s of %s",
                this.rank.name(),
                this.suit.name());
    }
}
