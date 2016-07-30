package problem_01_Logger.models.appenders;

import problem_01_Logger.contracs.Appender;
import problem_01_Logger.contracs.Layout;
import problem_01_Logger.core.IO.FileWriter;
import problem_01_Logger.models.annotations.InjectWriter;

@InjectWriter(name = "FileWriter")
public class FileAppender extends AbstractAppender implements Appender {

    public FileAppender(Layout layoutArg) {
        super(layoutArg);
    }

    @Override
    public String toString() {
        return String.format("%s, File size: %d",
                super.toString(),
                    ((FileWriter) this.getWriter()).getSize());
    }
}
