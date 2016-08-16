package recycleStation.core.factories;

import recycleStation.contracts.ManagementRequirement;
import recycleStation.contracts.RequirementFactory;
import recycleStation.core.requirements.ManagementRequirementImpl;
import wasteDisposal.contracts.Waste;

public class ManagementRequirementFactory implements RequirementFactory {

    private static final String WASTE_CLASS_PACKAGE = "recycleStation.models.garbage.";
    private static final String WASTE_CLASS_SUFFIX = "Garbage";

    @Override
    public ManagementRequirement createRequirement(String[] requirementParams)
            throws ReflectiveOperationException {
        ManagementRequirement managementRequirement = null;

        double minEnergy = Double.parseDouble(requirementParams[0]);
        double minCapital = Double.parseDouble(requirementParams[1]);
        String deniedWasteType = requirementParams[2];

        Class<Waste> deniedWasteClass =
                (Class<Waste>) Class.forName(WASTE_CLASS_PACKAGE + deniedWasteType + WASTE_CLASS_SUFFIX);

        managementRequirement = new ManagementRequirementImpl(minEnergy, minCapital, deniedWasteClass);
        return managementRequirement;
    }
}
