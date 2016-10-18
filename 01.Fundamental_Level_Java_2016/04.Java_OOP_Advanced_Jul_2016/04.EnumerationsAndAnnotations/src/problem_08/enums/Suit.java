package problem_08.enums;

public enum Suit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private Integer suitPower;

    private Suit(Integer suitPower) {
        this.suitPower = suitPower;
    }

    public Integer getSuitPower() {
        return this.suitPower;
    }
}
