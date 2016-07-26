package problem_10.models.weapons;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class Sword extends WeaponImpl {

    private static final int DEFAULT_MIN_DAMAGE = 4;
    private static final int DEFAULT_MAX_DAMAGE = 6;
    private static final int SOCKETS = 3;

    public Sword(String name) {
        super(name,
                DEFAULT_MIN_DAMAGE,
                DEFAULT_MAX_DAMAGE,
                SOCKETS);
    }
}
