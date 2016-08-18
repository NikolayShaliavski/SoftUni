package models.attacks;

import interfaces.Attack;
import interfaces.Blob;

public class Blobplode implements Attack {

    @Override
    public void execute(Blob source, Blob target) {
        source.setHealth(source.getHealth() - (source.getHealth() / 2));
        target.setHealth(target.getHealth() - (source.getDamage() * 2));
    }
}
