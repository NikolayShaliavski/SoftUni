package pr03Barracks.models.units;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
public class Gunner extends AbstractUnit {

    private static final int GUNNER_HEALTH = 20;
    private static final int GUNNER_DAMAGE = 20;

    public Gunner() {
        super(GUNNER_HEALTH, GUNNER_DAMAGE);
    }
}
