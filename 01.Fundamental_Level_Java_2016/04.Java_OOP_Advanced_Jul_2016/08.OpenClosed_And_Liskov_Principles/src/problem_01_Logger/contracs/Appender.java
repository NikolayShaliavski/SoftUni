package problem_01_Logger.contracs;

import problem_01_Logger.enums.ReportLevel;

public interface Appender {

    void setReportLevel(ReportLevel reportLevel);
    void setWriter(Writer writer);
    void appendMessage(Message message);
}
