package recycleStation.core.commands;

import recycleStation.contracts.RecycleStation;

public class StatusCommand extends Command {

    public StatusCommand(RecycleStation recycleStation) {
        super(recycleStation);
    }

    @Override
    public String execute() {
        return super.getRecycleStation().printStatus();
    }
}
