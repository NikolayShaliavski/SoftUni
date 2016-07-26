package pr0304Barracks.models.units;

/**
 * Created by Nikolay Shalyavski on 25.7.2016 г..
 */
public class Horseman extends AbstractUnit {

    private static final int HORSEMAN_HEALTH = 50;
    private static final int HORSEMAN_DAMAGE = 10;

    public Horseman() {
        super(HORSEMAN_HEALTH, HORSEMAN_DAMAGE);
    }
}
