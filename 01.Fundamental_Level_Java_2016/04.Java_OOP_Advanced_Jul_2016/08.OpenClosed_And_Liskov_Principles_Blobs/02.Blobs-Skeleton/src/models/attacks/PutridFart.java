package models.attacks;

import interfaces.Attack;
import interfaces.Blob;

public class PutridFart implements Attack {

    @Override
    public void execute(Blob source, Blob target) {
        target.setHealth(target.getHealth() - source.getDamage());
    }
}
