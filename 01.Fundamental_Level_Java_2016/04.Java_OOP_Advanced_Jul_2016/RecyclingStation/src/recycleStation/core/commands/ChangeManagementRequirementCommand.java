package recycleStation.core.commands;

import recycleStation.contracts.ManagementRequirement;
import recycleStation.contracts.RecycleStation;
import recycleStation.contracts.RequirementFactory;
import recycleStation.modelsAnnotations.Inject;

public class ChangeManagementRequirementCommand extends Command {

    private RequirementFactory managementFactory;
    @Inject
    private String requirementTokensLine;

    public ChangeManagementRequirementCommand(RecycleStation recycleStation) {
        super(recycleStation);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        String[] requirementParams = this.requirementTokensLine.split("\\|");
        ManagementRequirement managementRequirement =
                this.managementFactory.createRequirement(requirementParams);
        super.getRecycleStation().setManegementRequirement(managementRequirement);
        return "Management requirement changed!";
    }
}
