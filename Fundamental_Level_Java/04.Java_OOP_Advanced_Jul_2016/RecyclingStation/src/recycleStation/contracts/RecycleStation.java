package recycleStation.contracts;

import wasteDisposal.contracts.Waste;

public interface RecycleStation {

    String processWaste(Waste garbage) throws ReflectiveOperationException;

    String printStatus();

    void setManegementRequirement(ManagementRequirement manegementRequirement);
}
