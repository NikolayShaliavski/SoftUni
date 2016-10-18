package bg.softuni.main.contracts.modelContracts;

/**
 * This custom <b>Reportable</b> interface should be implemented by classes
 * which instances have to report about their current states
 */
public interface Reportable {

    /**
     * @return report about current state
     */
    String reportStatus();
}
