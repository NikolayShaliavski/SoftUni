package bg.softuni.main.contracts.coreContracts;

import java.io.IOException;

/**
 * This custom <b>Runnable</b> interface should be implemented
 * by a class which is an engine of this application
 */
public interface Runnable {

    /**
     * Holds the main loop of the application
     *
     * @throws IOException
     * @throws ReflectiveOperationException
     */
    void run() throws IOException, ReflectiveOperationException;
}
