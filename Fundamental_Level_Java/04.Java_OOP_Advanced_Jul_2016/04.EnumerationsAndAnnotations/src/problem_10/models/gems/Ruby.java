package problem_10.models.gems;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class Ruby extends GemImpl {

    private static final int DEFAULT_STRENGTH = 7;
    private static final int DEFAULT_AGILITY = 2;
    private static final int DEFAULT_VITALITY = 5;

    public Ruby() {
        super(DEFAULT_STRENGTH,
                DEFAULT_AGILITY,
                DEFAULT_VITALITY);
    }
}
