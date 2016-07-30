package problem_01_Logger.models.appenders;

import problem_01_Logger.contracs.Appender;
import problem_01_Logger.contracs.Layout;
import problem_01_Logger.contracs.Message;
import problem_01_Logger.contracs.Writer;
import problem_01_Logger.enums.ReportLevel;

public abstract class AbstractAppender implements Appender {

    private Layout layout;
    private ReportLevel reportLevel;
    private int countOfMessages;
    private Writer writer;

    protected AbstractAppender(Layout layoutArg) {
        this.layout = layoutArg;
        this.countOfMessages = 0;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    protected Writer getWriter() {
        return this.writer;
    }

    @Override
    public void appendMessage(Message message) {
        ReportLevel messageReportLevel = message.getLevelType();
        if (messageReportLevel.ordinal() >= this.reportLevel.ordinal()) {
            String messageText = String.format(this.layout.getLayout(),
                    message.getDate(),
                    message.getLevelType().name(),
                    message.getText());
            this.writer.write(messageText);
            this.countOfMessages++;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d",
                    this.getClass().getSimpleName(),
                        this.layout.getClass().getSimpleName(),
                            this.reportLevel.name(),
                                this.countOfMessages);
    }
}
