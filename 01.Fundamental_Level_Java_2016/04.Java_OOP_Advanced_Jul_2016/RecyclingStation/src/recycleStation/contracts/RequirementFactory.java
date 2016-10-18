package recycleStation.contracts;

public interface RequirementFactory {

    ManagementRequirement createRequirement(String[] requirementParams) throws ReflectiveOperationException;
}
