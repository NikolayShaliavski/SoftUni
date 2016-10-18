package problem_01_Logger.core.loggers;

import problem_01_Logger.contracs.Appender;
import problem_01_Logger.contracs.Logger;
import problem_01_Logger.contracs.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageLogger implements Logger {

    private List<Appender> appenders;

    public MessageLogger(List<Appender> appendersArgs) {
        this.appenders = appendersArgs;
    }

    @Override
    public void logMessage(Message message) {
        for (Appender appender : appenders) {
            appender.appendMessage(message);
        }
    }

    @Override
    public List<Appender> getAppenders() {
        List<Appender> copyCollection = new ArrayList<Appender>(this.appenders);
        return copyCollection;
    }
}
