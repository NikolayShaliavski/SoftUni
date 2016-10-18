package recycleStation.core.commands;

import recycleStation.contracts.Executable;
import recycleStation.contracts.RecycleStation;

public abstract class Command implements Executable {

    private RecycleStation recycleStation;

    protected Command(RecycleStation recycleStation) {
        this.recycleStation = recycleStation;
    }

    protected RecycleStation getRecycleStation() {
        return this.recycleStation;
    }
}
