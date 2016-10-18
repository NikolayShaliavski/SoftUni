package pr02_King_Gambit.contracts;

import pr02_King_Gambit.events.AttackEvent;

public interface Observer {

    String getName();
    void respondToAttack(AttackEvent attack);
    void wasHit();
}
