package problem_01_Logger.models.appenders;

import problem_01_Logger.contracs.Appender;
import problem_01_Logger.contracs.Layout;
import problem_01_Logger.models.annotations.InjectWriter;

@InjectWriter(name = "ConsoleWriter")
public class ConsoleAppender extends AbstractAppender implements Appender {

    public ConsoleAppender(Layout layoutArg) {
        super(layoutArg);
    }
}
