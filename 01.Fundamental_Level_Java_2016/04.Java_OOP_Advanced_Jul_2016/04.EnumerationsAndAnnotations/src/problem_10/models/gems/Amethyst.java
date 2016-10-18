package problem_10.models.gems;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class Amethyst extends GemImpl {

    private static final int DEFAULT_STRENGTH = 2;
    private static final int DEFAULT_AGILITY = 8;
    private static final int DEFAULT_VITALITY = 4;

    public Amethyst() {
        super(DEFAULT_STRENGTH,
                DEFAULT_AGILITY,
                DEFAULT_VITALITY);
    }
}
