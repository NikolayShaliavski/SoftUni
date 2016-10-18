package problem_10.models.gems;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class Emerald extends GemImpl {

    private static final int DEFAULT_STRENGTH = 1;
    private static final int DEFAULT_AGILITY = 4;
    private static final int DEFAULT_VITALITY = 9;

    public Emerald() {
        super(DEFAULT_STRENGTH,
                DEFAULT_AGILITY,
                DEFAULT_VITALITY);
    }
}
