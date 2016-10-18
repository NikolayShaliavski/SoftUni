package recycleStation.contracts;

import java.io.IOException;

/**
 * Custom <b>Reader</b> interface should be implemented by
 * classes which parse user input
 */
public interface Reader {

    /**
     * Reads user input and returns the String result
     *
     * @return user's input in a String
     * @throws IOException
     */
    String read() throws IOException;
}
