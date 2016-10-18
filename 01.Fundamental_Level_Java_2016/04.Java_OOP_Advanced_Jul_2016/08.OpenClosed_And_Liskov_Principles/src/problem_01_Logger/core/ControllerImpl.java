package problem_01_Logger.core;

import problem_01_Logger.contracs.*;
import problem_01_Logger.enums.ReportLevel;
import problem_01_Logger.models.annotations.InjectWriter;
import problem_01_Logger.models.messages.MessageImpl;

import java.lang.reflect.Constructor;

public class ControllerImpl implements Controller {

    private static final String APPENDER_PACKAGE = "problem_01_Logger.models.appenders.";
    private static final String LAYOUT_PACKAGE = "problem_01_Logger.models.layouts.";
    private static final String WRITER_PACKAGE = "problem_01_Logger.core.IO.";

    @Override
    public Appender readAppender(String appendersArgs) throws ReflectiveOperationException {

        String[] appenderParams = appendersArgs.split("[\\s]+");
        String appenderName = appenderParams[0];
        String layoutType = appenderParams[1];

        Layout layout = createLayout(layoutType);

        Class appenderClass = Class.forName(APPENDER_PACKAGE + appenderName);
        Constructor ctor = appenderClass.getConstructor(Layout.class);
        Appender appender = (Appender) ctor.newInstance((Object) layout);

        ReportLevel reportLevel = this.setAppenderReportLevel(appenderParams);

        appender.setReportLevel(reportLevel);

        InjectWriter writerAnot = (InjectWriter) appenderClass.getAnnotation(InjectWriter.class);
        String writerClassName = writerAnot.name();
        Writer writer = (Writer) Class.forName(WRITER_PACKAGE + writerClassName).newInstance();

        appender.setWriter(writer);
        return appender;
    }

    @Override
    public Message readMessage(String messageArgs) {
        String[] messageParams = messageArgs.split("\\|");
        ReportLevel messageLevel = ReportLevel.valueOf(messageParams[0]);
        String date = messageParams[1];
        String messageText = messageParams[2];

        Message message = new MessageImpl(date, messageLevel, messageText);
        return message;
    }

    private Layout createLayout(String layoutType) throws ReflectiveOperationException {

        Layout layout = (Layout) Class.forName(LAYOUT_PACKAGE + layoutType).newInstance();
        return layout;
    }

    private ReportLevel setAppenderReportLevel(String[] appenderParams) {
        ReportLevel reportLevel = null;
        if (appenderParams.length == 2) {
            reportLevel = ReportLevel.INFO;
        } else if (appenderParams.length == 3) {
            reportLevel = ReportLevel.valueOf(appenderParams[2]);
        }
        return reportLevel;
    }
}
