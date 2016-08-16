package bg.softuni.main.contracts.coreContracts;

/**
 * Custom <b>Writer</b> interface should be implemented by classes
 * which write the output of the program
 */
public interface Writer {

    /**
     * Writes output of the program
     *
     * @param message the result which should be written
     */
    void write(String message);
}
