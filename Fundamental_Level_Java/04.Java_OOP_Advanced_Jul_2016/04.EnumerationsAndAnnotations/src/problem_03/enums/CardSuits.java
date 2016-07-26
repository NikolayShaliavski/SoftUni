package problem_03.enums;

import problem_06.annotations.CustomEnumAnnotation;

@CustomEnumAnnotation(type = "Enumeration",
        category = "Suit",
        description = "Provides suit constants for a Card class.")
public enum CardSuits {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private Integer suitPower;

    private CardSuits(Integer suitPower) {
        this.suitPower = suitPower;
    }

    public Integer getSuitPower() {
        return this.suitPower;
    }
}
