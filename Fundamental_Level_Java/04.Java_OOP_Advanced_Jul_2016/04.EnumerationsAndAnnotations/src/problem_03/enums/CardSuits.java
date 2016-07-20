package problem_03.enums;

public enum CardSuits {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private Integer cardPower;

    private CardSuits(Integer cardPower) {
        this.cardPower = cardPower;
    }

    public Integer getCardPower() {
        return this.cardPower;
    }
}
