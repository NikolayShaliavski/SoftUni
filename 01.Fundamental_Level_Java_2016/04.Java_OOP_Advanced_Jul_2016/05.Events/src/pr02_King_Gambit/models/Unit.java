package pr02_King_Gambit.models;

import pr02_King_Gambit.contracts.Observable;
import pr02_King_Gambit.contracts.Observer;
import pr02_King_Gambit.events.DieEvent;

public abstract class Unit implements Observer {

    private String name;
    private int lives;
    private Observable king;

    protected Unit(String name,
                   Observable king,
                   int lives) {
        this.name = name;
        this.king = king;
        this.lives = lives;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void wasHit() {
        this.lives--;
        if (this.lives == 0) {
            DieEvent dieEvent = new DieEvent(this);
            this.king.handleDeadObserver(dieEvent);
        }
    }
}
