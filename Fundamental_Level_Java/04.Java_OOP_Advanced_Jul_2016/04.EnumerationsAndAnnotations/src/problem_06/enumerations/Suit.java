package problem_06.enumerations;

import problem_06.annotations.CustomEnumAnnotation;

@CustomEnumAnnotation(type = "Enumeration",
        category = "Suit",
        description = "Provides suit constants for a Card class.")
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
