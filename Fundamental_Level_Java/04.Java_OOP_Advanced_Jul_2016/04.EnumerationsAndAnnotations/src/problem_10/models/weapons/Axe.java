package problem_10.models.weapons;

/**
 * Created by Nikolay Shalyavski on 23.7.2016 г..
 */
public class Axe extends WeaponImpl {

    private static final int DEFAULT_MIN_DAMAGE = 5;
    private static final int DEFAULT_MAX_DAMAGE = 10;
    private static final int SOCKETS = 4;

    public Axe(String name) {
        super(name,
                DEFAULT_MIN_DAMAGE,
                DEFAULT_MAX_DAMAGE,
                SOCKETS);
    }
}
