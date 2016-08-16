package pr02_King_Gambit.models;

import pr02_King_Gambit.contracts.Observable;
import pr02_King_Gambit.contracts.Observer;
import pr02_King_Gambit.events.AttackEvent;

public class RoyalGuard extends Unit implements Observer {

    private static final int ROYAL_GUARD_LIVES = 3;

    public RoyalGuard(String name,
                      Observable king) {
        super(name, king, ROYAL_GUARD_LIVES);
    }

    @Override
    public void respondToAttack(AttackEvent attack) {
        System.out.printf("Royal Guard %s is defending!%n", super.getName());
    }
}
