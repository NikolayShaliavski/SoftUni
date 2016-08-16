package bg.softuni.main.contracts.coreContracts;

/**
 * Executable interface should be implemented by all command classes
 */
public interface Executable {

    /**
     * Every command class which implements this interface
     * must override and define his own behaviour in this method
     * this behaviour depends on user input
     */
    void execute();
}
