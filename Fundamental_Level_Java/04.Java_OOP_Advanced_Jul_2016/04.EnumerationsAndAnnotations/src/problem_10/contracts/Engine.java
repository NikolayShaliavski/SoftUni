package problem_10.contracts;

import java.io.IOException;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public interface Engine extends Runnable {

    void init();
    String readCommand() throws IOException;
    void executeCommand(String commandParams);
}
