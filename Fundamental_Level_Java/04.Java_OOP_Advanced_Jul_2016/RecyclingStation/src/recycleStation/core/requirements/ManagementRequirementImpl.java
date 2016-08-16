package recycleStation.core.requirements;

import recycleStation.contracts.ManagementRequirement;
import wasteDisposal.contracts.Waste;

public class ManagementRequirementImpl implements ManagementRequirement {

    private double minEnergy;
    private double minCapital;
    private Class deniedWaste;

    public ManagementRequirementImpl(double minEnergy,
                                     double minCapital,
                                     Class deniedWaste) {
        this.minEnergy = minEnergy;
        this.minCapital = minCapital;
        this.deniedWaste = deniedWaste;
    }

    @Override
    public boolean checkWasteForProcessing(
            double currentEnergy, double currentCapital, Waste waste) {
        if (currentEnergy < this.minEnergy || currentCapital < this.minCapital) {
            if (this.deniedWaste == waste.getClass()) {
                return false;
            }
        }
        return true;
    }
}
