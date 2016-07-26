package problem_10.models.weapons;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class Knife extends WeaponImpl {

    private static final int DEFAULT_MIN_DAMAGE = 3;
    private static final int DEFAULT_MAX_DAMAGE = 4;
    private static final int SOCKETS = 2;

    public Knife(String name) {
        super(name,
                DEFAULT_MIN_DAMAGE,
                DEFAULT_MAX_DAMAGE,
                SOCKETS);
    }
}
