package pr02_King_Gambit.models;

import pr02_King_Gambit.contracts.Observable;
import pr02_King_Gambit.contracts.Observer;
import pr02_King_Gambit.events.AttackEvent;

public class Footman extends Unit implements Observer {

    private static final int FOOTMAN_LIVES = 2;

    public Footman(String name,
                   Observable king) {
        super(name, king, FOOTMAN_LIVES);
    }

    @Override
    public void respondToAttack(AttackEvent attack) {
        System.out.printf("Footman %s is panicking!%n", super.getName());
    }
}
