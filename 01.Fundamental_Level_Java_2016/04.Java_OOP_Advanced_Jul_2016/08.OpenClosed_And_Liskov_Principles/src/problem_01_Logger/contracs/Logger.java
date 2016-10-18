package problem_01_Logger.contracs;

import java.util.List;

public interface Logger {

    void logMessage(Message message);
    List<Appender> getAppenders();
}
