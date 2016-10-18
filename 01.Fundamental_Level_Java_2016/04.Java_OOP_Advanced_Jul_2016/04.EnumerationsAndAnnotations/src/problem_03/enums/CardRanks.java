package problem_03.enums;

import problem_06.annotations.CustomEnumAnnotation;

@CustomEnumAnnotation(type = "Enumeration",
        category = "Rank",
        description = "Provides rank constants for a Card class.")
public enum CardRanks {
    ACE(14),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private Integer rankPower;

    CardRanks(Integer rankPower) {
        this.rankPower = rankPower;
    }

    public Integer getRankPower() {
        return this.rankPower;
    }
}
