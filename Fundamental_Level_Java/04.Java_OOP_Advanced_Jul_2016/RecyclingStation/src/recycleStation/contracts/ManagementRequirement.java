package recycleStation.contracts;

import wasteDisposal.contracts.Waste;

public interface ManagementRequirement {

    boolean checkWasteForProcessing(
            double currentEnergy, double currentCapital, Waste waste);
}
