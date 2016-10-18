package recycleStation.core.commands;

import recycleStation.contracts.GarbageFactory;
import recycleStation.contracts.RecycleStation;
import recycleStation.modelsAnnotations.Inject;
import wasteDisposal.contracts.Waste;

public class ProcessGarbageCommand extends Command {

    private GarbageFactory garbageFactory;
    @Inject
    private String garbageTokensLine;

    public ProcessGarbageCommand(RecycleStation recycleStation) {
        super(recycleStation);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        String[] garbageParams = this.garbageTokensLine.split("\\|");
        Waste garbage = this.garbageFactory.createGarbage(garbageParams);
        String result = super.getRecycleStation().processWaste(garbage);
        return result;
    }
}
