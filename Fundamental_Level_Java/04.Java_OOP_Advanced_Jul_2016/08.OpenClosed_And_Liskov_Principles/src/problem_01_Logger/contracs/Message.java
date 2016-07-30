package problem_01_Logger.contracs;

import problem_01_Logger.enums.ReportLevel;

/**
 * Created by Nikolay Shalyavski on 29.7.2016 г..
 */
public interface Message {

    String getDate();
    ReportLevel getLevelType();
    String getText();
}
